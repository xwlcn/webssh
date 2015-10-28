package online.webssh.pojos;

import java.io.Serializable;

public class Machine implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String hostname;
	private String username;
	private String password;
	private Integer port;
	
	private User user;
	
	public Machine() {
		super();
	}
	
	public Machine(String name, String hostname, String username, String password, Integer port) {
		super();
		this.name = name;
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		this.port = port;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Machine [id=" + id + ", name=" + name + ", hostname=" + hostname + ", username=" + username
				+ ", password=" + password + ", port=" + port + ", user=" + user + "]";
	}

}
