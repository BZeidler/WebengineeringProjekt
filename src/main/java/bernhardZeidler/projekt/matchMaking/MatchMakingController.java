package bernhardZeidler.projekt.matchMaking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bernhardZeidler.projekt.matchMaking.MatchMakingService.Suggestion;
import bernhardZeidler.projekt.user.UserService;

@RestController
public class MatchMakingController 
{
	@Autowired
	private MatchMakingService matchService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/matching/find", method = RequestMethod.GET)
	public Suggestion findMatch()
	{
		return matchService.findMatch();	
	}
	
	public static class IdContainer
	{
		public long id;
	}
	
	@RequestMapping(value = "/matching/like", method = RequestMethod.POST)
	public ResponseEntity<Object> like(@RequestBody IdContainer id)
	{
		if(userService.isAnonymous())
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		if( matchService.like( id.id) )
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}
