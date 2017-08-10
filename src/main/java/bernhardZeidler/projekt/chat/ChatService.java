package bernhardZeidler.projekt.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
