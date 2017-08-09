package bernhardZeidler.projekt.matchMaking;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bernhardZeidler.projekt.user.UserRepository;

@Service
public class MatchMakingService {
	private static final Logger LOG = LoggerFactory.getLogger( MatchMakingService.class );
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	public Suggestion findMatch() {
		LOG.info("Finding match");
		List<Long> ids = userRepository.findAllIds();
		//get random user id
		int index = (int)(Math.random() * ids.size());
		Long id = ids.get(index);
		
		Suggestion suggestion = new Suggestion();
		suggestion.id = id;
		suggestion.message = userRepository.getTextByID(id);
		
		LOG.info("Suggested sugestion={}", suggestion);
		return suggestion;
	}
}
