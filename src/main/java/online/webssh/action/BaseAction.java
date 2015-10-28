package online.webssh.action;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import online.webssh.beans.MessageBean;
import online.webssh.utils.WebSshUtil;

public class BaseAction<T> extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware, ModelDriven<T>{

	private static final long serialVersionUID = 1L;
	
	protected T model;
	protected Map<String, Object> session;
	protected HttpServletResponse servletResponse;
	protected HttpServletRequest servletRequest;
	protected MessageBean message;
	private String lastUrl = WebSshUtil.getLastUrl(ServletActionContext.getRequest());
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T getModel() {
		if (model == null) {
			try {
				ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
				Class clazz = (Class) type.getActualTypeArguments()[0];
				model = (T) clazz.newInstance();
			} catch (Exception e) {
				
			}
		}
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}


	public MessageBean getMessage() {
		return message;
	}

	public void setMessage(MessageBean message) {
		this.message = message;
	}

	public String getLastUrl() {
		return lastUrl;
	}

	public void setLastUrl(String lastUrl) {
		this.lastUrl = lastUrl;
	}
	
}
