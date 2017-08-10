package bernhardZeidler.projekt.chat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class ChatMessages {

	@Id
	@GeneratedValue
	private Long id;

	private Long author_Id;

	private Long match_Id;

	@Column
	private Date created_At;

	@Column
	private String message;

	public ChatMessages() {
		this.id = -1L; 
		this.author_Id = -1L;
		this.match_Id = -1L;
		this.created_At = new Date();
		this.message = new String();
	}

	public ChatMessages(Long author_Id, Long match_Id, Date created_At, String message) { 
		this.author_Id = author_Id;
		this.match_Id = match_Id;
		this.created_At = created_At;
		this.message = message;
	}

	public ChatMessages(Long author_Id, Long match_Id, String message) {
		this.author_Id = author_Id;
		this.match_Id = match_Id;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthor_Id() {
		return author_Id;
	}

	public void setAuthor_Id(Long author_Id) {
		this.author_Id = author_Id;
	}

	public Long getMatch_Id() {
		return match_Id;
	}

	public void setMatch_Id(Long match_Id) {
		this.match_Id = match_Id;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@PrePersist
	public void prePersist() {
		this.created_At = new Date();
	}
	
	@Override
	public String toString() {
		String ret = new String();
		ret +="{\"id\":" + id 
				+ ",\"author_Id\":" + author_Id
				+ ",\"match_Id\":" + match_Id
				+ ",\"created_At\":" + created_At 
				+ ",\"message\":" + message
				+ "}";
		return ret;
	}
}
