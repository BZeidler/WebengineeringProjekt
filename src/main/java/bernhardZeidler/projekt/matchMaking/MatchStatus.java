package bernhardZeidler.projekt.matchMaking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import bernhardZeidler.projekt.user.User;

/* statt like / dislike / match
soll alle Status (til.: pl von Status ist Status) darstellen
warum? sonst gäbe es 3 Tabellen mit jeweils Paaren ID, ID wobei nur der Name
der Tabelle die tatsächliche Bedeutung des Paares hergibt. Darin sehe ich keinen Sinn/Vorteil
orientiert an Post.java  
*/
@Entity
public class MatchStatus
{
	@Id
	@JsonIgnore
	@GeneratedValue
	private Long id;
	
	
	private Long initiator_Id;
	
	private Long target_Id;
	
	//aktuell geplant: 3 Status: L, D und M, (+1 Invalid in Default cTor)daher Länge 1
	@Column(length = 1)
	private char state;
	
	public MatchStatus() 
	{
		this.initiator_Id = -1L;
		this.target_Id = -1L;
		this.state = 'I';
	}
	
	public MatchStatus(Long initiator, Long target, char state)
	{
		this.initiator_Id = initiator;
		this.target_Id = target;
		this.state = state;
	}
	
	
	public MatchStatus(Long id, Long initiator, Long target, char state)
	{
		this.id = id;
		this.initiator_Id = initiator;
		this.target_Id = target;
		this.state = state;
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	@JsonIgnore
	public void setId(Long id) {
		this.id = id;
	}

	public Long getInitiator() {
		return initiator_Id;
	}

	public void setInitiator(Long initiator) {
		this.initiator_Id = initiator;
	}

	public Long getTarget() {
		return target_Id;
	}

	public void setTarget(Long target) {
		this.target_Id = target;
	}

	public char getState() {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		String ret = new String();
		ret += "{\"id\":" + id
				+ "\"initiator\":" + initiator_Id
				+ "\"target\":" + target_Id
				+ "\"state\":" + state
				+ "}";
		return ret;
	}
} 
