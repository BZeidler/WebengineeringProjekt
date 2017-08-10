package bernhardZeidler.projekt.chat;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

import bernhardZeidler.projekt.chat.ChatController.NewChatMessage;
import bernhardZeidler.projekt.matchMaking.MatchMakingRepository;
import bernhardZeidler.projekt.matchMaking.MatchStatus;
import bernhardZeidler.projekt.user.UserService;

@Service
public class ChatService {

	private static Logger LOG = LoggerFactory.getLogger(ChatService.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private MatchMakingRepository matchRepository;
	
	public void receiveMessage(NewChatMessage message) {
		MatchStatus status = matchRepository.findByID(message.matchId);
		Long author_Id = userService.getCurrentUser().getId();
		
		//forbid chat in foreign match
		if(status.getInitiator() != author_Id && status.getTarget() != author_Id)
			return;
		
		//forbid chat between unmatched users
		if(status.getState() != 'M')
			return;

		ChatMessages msg = new ChatMessages(author_Id, message.matchId, message.message);
		
		chatRepository.save(msg);
		LOG.info("Storing new ChatMessage={}", msg);
	}

	public List<ChatMessages> getMessages(Long matchId) {
		List<ChatMessages> tmpList = chatRepository.getMessages(matchId);
		return filterInvalidMessages(tmpList);
	}
	
	/**
	 * Filters a list so it only contains chat messages a user should be able to see.
	 * Should be superfluous if entries are stored correctly in DB, but you never know.
	 * @param filterList
	 * @return the filtered list
	 */
	private List<ChatMessages> filterInvalidMessages(List<ChatMessages> filterList)
	{
		List<ChatMessages> invalidMessages = new ArrayList<>();
		Long self = userService.getCurrentUser().getId();
		
		for (ChatMessages chatMessages : filterList) 
		{
			Long searchId = chatMessages.getMatch_Id();
			MatchStatus state = matchRepository.findByID(searchId);
			if( state.getInitiator() != self && state.getTarget() != self )
				invalidMessages.add(chatMessages);
		}
		
		for (ChatMessages chatMessages : invalidMessages) 
		{
			filterList.remove(chatMessages);
		}
		return filterList;
	}
}
