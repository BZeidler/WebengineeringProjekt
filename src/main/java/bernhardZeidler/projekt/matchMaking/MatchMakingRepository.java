package bernhardZeidler.projekt.matchMaking;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import bernhardZeidler.projekt.user.User;

public interface MatchMakingRepository extends CrudRepository<MatchStatus, Long>
{
	@Query("SELECT m FROM MatchStatus m "
			+ "WHERE (m.initiator_Id = :self "
			+ "OR m.target_Id = :self) "
			+ "AND m.state = 'M'")
	List<MatchStatus> findMatchesByUser(@Param("self") Long self);
	
	@Query("SELECT m FROM MatchStatus m WHERE m.id = :id")
	MatchStatus findByID(@Param("id") Long id);
	
	@Query("SELECT u.id FROM User_ u "
			+ "WHERE u.id IN "
			+ "(SELECT m.initiator_Id FROM MatchStatus m "
			+ "WHERE m.target_Id = :self "
			+ "AND m.state != 'D'"
			+ "AND m.state != 'M'"
			+ ")")
	List<Long> findPrioritySuggestion(@Param("self") Long self);
	
	@Query("SELECT u.id FROM User_ u "
			+ "WHERE u.id NOT IN "
			+ "(SELECT m.initiator_Id FROM MatchStatus m "
			+ "WHERE m.target_Id = :self "
			+ "AND m.state != 'D')"
			+ "AND u.id != :self")
	List<Long> findNewSuggestion(@Param("self") Long self);
	
	@Query("SELECT s FROM MatchStatus s WHERE s.initiator_Id = :initiator AND s.target_Id = :target")
	MatchStatus findExistingState(@Param("initiator") Long initiator, @Param("target") Long target );
}