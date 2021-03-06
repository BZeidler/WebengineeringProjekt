package bernhardZeidler.projekt.matchMaking;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bernhardZeidler.projekt.chat.ChatMessages;
import bernhardZeidler.projekt.chat.ChatRepository;
import bernhardZeidler.projekt.matchMaking.MatchMakingController.Match;
import bernhardZeidler.projekt.user.User;
import bernhardZeidler.projekt.user.UserRepository;
import bernhardZeidler.projekt.user.UserService;

@Service
public class MatchMakingService {
	private static final Logger LOG = LoggerFactory.getLogger( MatchMakingService.class );
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MatchMakingRepository matchRepository;
	
	@Autowired
	private ChatRepository chatRepository;
	
	public static class Suggestion
	{
		public Long id;
		public String message;
		
		@Override
		public String toString() {
			String ret = new String();
			ret += "{\"id\":" + id + ", \"message\":" + message + "}";
			return ret;
		}
	}
	/**
	 * Find a suggestion for a match
	 * @return the suggested match
	 */
	public Suggestion findMatch() {
		LOG.info("Finding match");
		Long self = userService.getCurrentUser().getId();
		List<Long> ids = matchRepository.findPrioritySuggestion(self);
		LOG.info("Priority List ={}", ids);
		
		//nobody liked self, find new people
		if(ids.size() == 0)
			ids = findNewSuggestion(self);

		
		//get random user id
		Suggestion suggestion = null;
		if( ids.size() > 0)
		{
			int index = (int)(Math.random() * ids.size());
			Long id = ids.get(index);
			
			suggestion = new Suggestion();
			suggestion.id = id;
			suggestion.message = userRepository.getTextByID(id);
		}
		LOG.info("Suggested sugestion={}", suggestion);
		return suggestion;
	}
	
	private List<Long> findNewSuggestion(Long self) {
		List<Long> ret = new ArrayList<>();
		List<MatchStatus> matches = matchRepository.findMatchStatesByUser(self);
		HashMap<Long, User> users = getUsers();
		
		for (MatchStatus match : matches) {
			if( users.containsKey(match.getInitiator()) )
				users.remove(match.getInitiator());
			if(users.containsKey(match.getTarget()) )
				users.remove(match.getTarget());
		}
		
		for (Long userId : users.keySet()) {
			ret.add(userId);
		}
		
		if(ret.contains(self))
			ret.remove(self);
		return ret;
	}

	private HashMap<Long, User> getUsers() {
		HashMap<Long, User> ret = new HashMap<>();
		Iterable<User> users = userRepository.findAll();
		for (User user : users) {
			ret.put(user.getId(), user);
		}
		return ret;
	}

	/**
	 * likes a user by setting the apropriate state in the DB
	 * @param targetId the user id of the "liked" user
	 * 
	 * @return true if successful
	 */
	public boolean like(Long target)
	{
		Long initiator = userService.getCurrentUser().getId();
		//is there a "like" from current target to current initiator?
		MatchStatus status = matchRepository.findExistingState(target, initiator);
		
		if( status != null)
		{
			//just to be save
			if(status.getState() == 'D')
				return false; 
			
			matchRepository.delete(status.getId());
			status.setState('M');//both like each other now
			matchRepository.save(status);
			return true;
		}
		status = new MatchStatus(initiator, target, 'L');
		matchRepository.save(status);
		LOG.info("Stored 'Like' state={}", status);
		return true;
	}

	/**
	 * dislikes a user by setting the apropriate state in the DB
	 * @param targetId the user id of the "liked" user
	 * 
	 * @return true if successful
	 */
	public boolean dislike(Long target)
	{
		Long initiator = userService.getCurrentUser().getId();
		MatchStatus status = new MatchStatus(initiator, target, 'D');
		matchRepository.save(status);
		LOG.info("Stored 'Like' state={}", status);
		return true;//TODO: return benötigt?
	}

	public List<Match> getMatches() {
		Long self = userService.getCurrentUser().getId();
		List<MatchStatus> matchList = matchRepository.findMatchesByUser(self);
		List<Match> matches = new ArrayList<>();
		for (MatchStatus match : matchList) 
		{
			Match m = new Match();
			m.id = match.getId();
			if(match.getInitiator() == self)
				m.name = userRepository.findById( match.getTarget() ).getName();
			else
				m.name = userRepository.findById( match.getInitiator() ).getName();
			m.lastMessage = getLastMessage(m.id);
			matches.add(m);
		}
		return matches;
	}
	
	private ChatMessages getLastMessage(Long matchId)
	{
		List<ChatMessages> messages = chatRepository.getMessages(matchId);
		if(messages.size() > 0 )
			return messages.get( messages.size() - 1);
		else 
			return null;
	}
	
}
