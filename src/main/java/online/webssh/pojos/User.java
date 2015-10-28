package online.webssh.pojos;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nick;
	private String email;
	private String password;
	private String facePath;
	private Timestamp regTime;
	private Timestamp lastLoginTime;
	private String lastLoginIP;
	private Integer level;
	
	public User() {
	}

	public User(String nick, String email, String password, String facePath, Timestamp regTime, Timestamp lastLoginTime,
			String lastLoginIP, Integer level) {
		super();
		this.nick = nick;
		this.email = email;
		this.password = password;
		this.facePath = facePath;
		this.regTime = regTime;
		this.lastLoginTime = lastLoginTime;
		this.lastLoginIP = lastLoginIP;
		this.level = level;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFacePath() {
		return facePath;
	}

	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}

	public Timestamp getRegTime() {
		return regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}

	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nick=" + nick + ", email=" + email + ", password=" + password + ", facePath="
				+ facePath + ", regTime=" + regTime + ", lastLoginTime=" + lastLoginTime + ", lastLoginIP="
				+ lastLoginIP + ", level=" + level + "]";
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}


}
