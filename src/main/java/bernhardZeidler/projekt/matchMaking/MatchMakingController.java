package bernhardZeidler.projekt.matchMaking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bernhardZeidler.projekt.chat.ChatMessages;
import bernhardZeidler.projekt.matchMaking.MatchMakingService.Suggestion;
import bernhardZeidler.projekt.user.UserService;

@RestController
@RequestMapping("/api/matching")
public class MatchMakingController 
{
	@Autowired
	private MatchMakingService matchService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public Suggestion findMatch()
	{
		if(userService.isAnonymous())
			return null;
		
		return matchService.findMatch();	
	}
	
	public static class IdContainer
	{
		public long id;
	}
	
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public ResponseEntity<Object> like(@RequestBody IdContainer id)
	{
		if(userService.isAnonymous())
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		if( matchService.like( id.id) )
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@RequestMapping(value = "/dislike", method = RequestMethod.POST)
	public ResponseEntity<Object> dislike(@RequestBody IdContainer id)
	{
		if(userService.isAnonymous())
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		if( matchService.dislike( id.id) )
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	public static class Match
	{
		public Long id;
		public String name;
		public ChatMessages lastMessage;
	}
	@RequestMapping(value ="/list", method = RequestMethod.GET )
	public List<Match> list()
	{
		if( !userService.isAnonymous() )
			return matchService.getMatches();
		return null;
	}
}
