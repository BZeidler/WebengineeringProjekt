package bernhardZeidler.projekt.matchMaking;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bernhardZeidler.projekt.matchMaking.MatchMakingService.Suggestion;

@RestController
public class MatchMakingController 
{
	@Autowired
	private MatchMakingService matchService;
	
	@RequestMapping(value = "/matching/find", method = RequestMethod.GET)
	public Suggestion findMatch()
	{
		return matchService.findMatch();	
	}
}
