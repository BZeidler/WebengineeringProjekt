package bernhardZeidler.projekt.matchMaking;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			ids = matchRepository.findNewSuggestion(self);
		
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
	
	/**
	 * likes a user by setting the apropriate state in the DB
	 * @param targetId the user id of the "liked" user
	 * 
	 * @return true if successful
	 */
	public boolean like(Long target)
	{
		Long initiator = userService.getCurrentUser().getId();
		MatchStatus status = new MatchStatus(initiator, target, 'L');
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
		return true;
	}
}
