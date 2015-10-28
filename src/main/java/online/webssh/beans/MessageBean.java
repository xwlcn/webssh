package online.webssh.beans;

public class MessageBean {
	
	//message code
	private Integer code;
	//message content
	private String msg;
	
	public MessageBean() {
		super();
	}
	public MessageBean(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
