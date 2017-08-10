package bernhardZeidler.projekt.chat;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChatRepository extends CrudRepository<ChatMessages, Long>{

	@Query("SELECT msg FROM ChatMessages msg "
			+ "WHERE msg.match_Id = :matchID "
			+ "ORDER BY CREATED_AT ASC")
	List<ChatMessages> getMessages(@Param("matchID")Long matchId);

	@Query("SELECT msg "
			+ "FROM ChatMessages msg "
			+ "WHERE msg.match_Id = :matchId "
			+ "AND ROWNUM <= 1 "
			+ "ORDER BY created_At DESC ")
	ChatMessages getLastMessageFromMatch(@Param("matchId") Long id);

}
