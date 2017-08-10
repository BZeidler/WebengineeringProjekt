package bernhardZeidler.projekt.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bernhardZeidler.projekt.user.UserService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	public static class NewChatMessage
	{
		public Long matchId;
		public String message;
	}
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public ResponseEntity<Object> newMessage(@RequestBody NewChatMessage message)
	{
		chatService.receiveMessage(message);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/load/{matchId}", method = RequestMethod.GET)
	public List<ChatMessages> requestMessages(@PathVariable Long matchId)
	{
		//need to be logged in
		if(userService.isAnonymous())
			return null;
		return chatService.getMessages(matchId);
	}
}
