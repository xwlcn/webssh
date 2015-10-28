package online.webssh.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import online.webssh.utils.WebSshUtil;

public class LanguageAction extends ActionSupport{

	/**
	 * 
	 */
	private String lastUrl;
	
	private static final long serialVersionUID = 1267553608754627232L;

	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		lastUrl = WebSshUtil.getLastUrl(request);
		return SUCCESS;
	}

	public String getLastUrl() {
		return lastUrl;
	}

	public void setLastUrl(String lastUrl) {
		this.lastUrl = lastUrl;
	}
}
