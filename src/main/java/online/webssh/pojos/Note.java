package online.webssh.pojos;

import java.io.Serializable;
import java.sql.Timestamp;

public class Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private Timestamp writeTime;
	private String content;
	private Boolean finished = false;
	
	private User user;
	
	public Note() {
	}
	
	public Note(String title, Timestamp writeTime, String content) {
		super();
		this.title = title;
		this.writeTime = writeTime;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Timestamp writeTime) {
		this.writeTime = writeTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
