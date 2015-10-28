package online.webssh.action;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import online.webssh.utils.SecurityCode;
import online.webssh.utils.SecurityImage;


public class SecurityCodeImageAction  extends ActionSupport  implements SessionAware{

	private static final long serialVersionUID = 1L;

    private ByteArrayInputStream imageStream;

    private Map<String, Object> session ;
    
    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }
    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String execute() throws Exception {

        String securityCode = SecurityCode.getSecurityCode();
        imageStream = SecurityImage.getImageAsInputStream(securityCode);

        session.put("securityCode", securityCode);
        return SUCCESS;
    }
}
