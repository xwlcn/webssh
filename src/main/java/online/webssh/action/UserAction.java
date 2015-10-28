package online.webssh.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;

import com.alibaba.fastjson.JSON;

import online.webssh.beans.CodeMsg;
import online.webssh.beans.MessageBean;
import online.webssh.pojos.Note;
import online.webssh.pojos.User;
import online.webssh.service.UserService;
import online.webssh.utils.DeepCopyUtil;
import online.webssh.utils.EndecryptUtil;
import online.webssh.utils.ValidateUtil;
import online.webssh.utils.WebSshUtil;

public class UserAction extends BaseAction<User>{
	
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	private InputStream inputStream;
	private String verify;   //verify code
	private Integer remember;  //remember me ?
	private String oldPassword;
	private String newPassword;
	private String repeatPassword;
	private List<Note> notes;
	private BigInteger noteCount = new BigInteger("0");
	private final Integer LAST_NOTE_NUM = 12;
	
	public String logout(){
		if(session!=null) {
			session.remove("user");
			session.clear();
		}
		Cookie emailCookie = new Cookie("email", "");
		emailCookie.setPath("/");
		emailCookie.setMaxAge(0);
		Cookie pwdCookie = new Cookie("pwd", "");
		pwdCookie.setPath("/");
		pwdCookie.setMaxAge(0);
		servletResponse.addCookie(emailCookie);
		servletResponse.addCookie(pwdCookie);
		return "logout";
	}
	
	public String login() {

		//get verify code in the session
		String verifyCode = (String) session.get("securityCode");
		
		//check verify code
		if(verifyCode!=null&&verifyCode.equals(verify)){
			
			//check email and password
			if(ValidateUtil.validStrings(model.getEmail(),model.getPassword())){
				
				User u = userService.findOneByHql("from User u where u.email=?", model.getEmail());
				
				if(u!=null&&(u.getPassword().equals(EndecryptUtil.MD5(model.getPassword())))){
					
					//put user into session
					User tempUser = DeepCopyUtil.deepCopy(u);
					session.put("user", tempUser);	
					
					//update the lastLoginTime and lastLoginIP
					u.setLastLoginIP(servletRequest.getRemoteAddr());
					u.setLastLoginTime(new Timestamp(new Date().getTime()));
					userService.saveOrUpdate(u);
					
					//success
					message = new MessageBean(CodeMsg.USERACTION_LOGIN_SUCCESS_CODE, 
							getText(CodeMsg.USERACTION_LOGIN_SUCCESS_MSG));
					
					//remember me ?
					if(remember!=null&&remember==1){
						Cookie emailCookie = new Cookie("email", model.getEmail());
						emailCookie.setPath("/");
						emailCookie.setMaxAge(3600*24*30);
						Cookie pwdCookie = new Cookie("pwd", u.getPassword());
						pwdCookie.setPath("/");
						pwdCookie.setMaxAge(3600*24*30);
						servletResponse.addCookie(emailCookie);
						servletResponse.addCookie(pwdCookie);
					}
				}else{
					//failed
					message = new MessageBean(CodeMsg.USERACTION_LOGIN_FAILED_CODE, 
							getText(CodeMsg.USERACTION_LOGIN_FAILED_MSG));
				}
			}
		}else{
			message = new MessageBean(CodeMsg.USERACTION_LOGIN_VERIFYCODE_ERROR_CODE,
					getText(CodeMsg.USERACTION_LOGIN_VERIFYCODE_ERROR_MSG));
		}
		inputStream = WebSshUtil.toJsonMessage(message);
		return "login";
	}
	
	public String register() throws UnsupportedEncodingException{
		//get verify code in the session
		String verifyCode = (String) session.get("securityCode");
		
		//check verify code
		if(ValidateUtil.validStrings(model.getNick())
				&& ValidateUtil.validStrings(model.getEmail())
				&& ValidateUtil.validStrings(model.getPassword())
				&& !(model.getPassword().length() < 6)
				&& !(model.getPassword().length() > 16)
				&& !(model.getNick().length()>16)
				&&verifyCode!=null
				&&verifyCode.equals(verify)){
			
			//validate email
			boolean isMatched = ValidateUtil.validEmail(model.getEmail());

			if (isMatched) {
				
				//check email in database
				if (userService.findOneByHql("From User u where u.email=?", model.getEmail())!=null) {
					message = new MessageBean(CodeMsg.USERACTION_REGISTER_EMAIL_EXIST_CODE,
							getText(CodeMsg.USERACTION_REGISTER_EMAIL_EXIST_MSG));
					return "register";
				}
				//check nick in database
				if (userService.findOneByHql("from User u where u.nick=?", model.getNick().trim()) != null) {
					//exist
					message = new MessageBean(CodeMsg.USERACTION_REGISTER_NICK_EXIST_CODE,
							getText(CodeMsg.USERACTION_REGISTER_NICK_EXIST_MSG));
					return "register";
				}
				
				model.setPassword(EndecryptUtil.MD5(model.getPassword()));
				Timestamp time = new Timestamp(new Date().getTime());
				model.setRegTime(time);
				model.setLastLoginTime(time);
				model.setFacePath("/images/faces/user.png");
				String lastLoginIP = servletRequest.getRemoteAddr();
				model.setLastLoginIP(lastLoginIP);
				model.setLevel(0);
				userService.save(model);
				
				//regist success
				message = new MessageBean(CodeMsg.USERACTION_REGISTER_SUCCESS_CODE,
						getText(CodeMsg.USERACTION_REGISTER_SUCCESS_MSG));
				session.remove("user");
				
				//clear the session
				session.clear();
			}else{
				message = new MessageBean(CodeMsg.USERACTION_REGISTER_INPUT_ERROR_CODE,
						getText(CodeMsg.USERACTION_REGISTER_INPUT_ERROR_MSG));
			}
		}else{
			message = new MessageBean(CodeMsg.USERACTION_REGISTER_VERIFYCODE_ERROR_CODE,
					getText(CodeMsg.USERACTION_REGISTER_VERIFYCODE_ERROR_MSG));
		}
		inputStream = WebSshUtil.toJsonMessage(message);
		return "register";
	}
	
	public String checkEmail() throws UnsupportedEncodingException{

		if(ValidateUtil.validStrings(model.getEmail())){
			User user = userService.findOneByHql("from User u where u.email=?", model.getEmail().trim());
			if(user==null){
				//ok
				message = new MessageBean(CodeMsg.USERACTION_REGISTER_EMAIL_OK_CODE,
						getText(CodeMsg.USERACTION_REGISTER_EMAIL_OK_MSG));
			}else{
				//exist
				message = new MessageBean(CodeMsg.USERACTION_REGISTER_EMAIL_EXIST_CODE,
						getText(CodeMsg.USERACTION_REGISTER_EMAIL_EXIST_MSG));
			}
		}else{
			//null
			message = new MessageBean(CodeMsg.USERACTION_REGISTER_EMAIL_NULL_CODE,
					getText(CodeMsg.USERACTION_REGISTER_EMAIL_NULL_MSG));
		}
		inputStream = WebSshUtil.toJsonMessage(message);
		return "checkEmail";
	}
	
	public String checkNick() throws UnsupportedEncodingException {
		
		if(ValidateUtil.validStrings(model.getNick())){
			
			User user = userService.findOneByHql("from User u where u.nick=?", model.getNick().trim());
			if(user==null){
				//ok
				message = new MessageBean(CodeMsg.USERACTION_REGISTER_NICK_OK_CODE,
						getText(CodeMsg.USERACTION_REGISTER_NICK_OK_MSG));
			}else{
				//exist
				message = new MessageBean(CodeMsg.USERACTION_REGISTER_NICK_EXIST_CODE,
						getText(CodeMsg.USERACTION_REGISTER_NICK_EXIST_MSG));
			}
		}else{
			//null
			message = new MessageBean(CodeMsg.USERACTION_REGISTER_NICK_NULL_CODE,
					getText(CodeMsg.USERACTION_REGISTER_NICK_NULL_MSG));
		}
		inputStream = WebSshUtil.toJsonMessage(message);
		return "checkNick";
	}
	
	public String updatePassword() throws UnsupportedEncodingException {
		
		User user = userService.get(((User) session.get("user")).getId());
		
		if (user!=null && ValidateUtil.validStrings(oldPassword) && ValidateUtil.validStrings(newPassword) 
				&& ValidateUtil.validStrings(repeatPassword) && newPassword.equals(repeatPassword)) {
			
			if (EndecryptUtil.MD5(oldPassword).equals(user.getPassword())) {
				user.setPassword(EndecryptUtil.MD5(newPassword));
				userService.saveOrUpdate(user);
				
				//clear the session, then go to login.
				session.remove("user");
				session.clear();
				
				//update success
				message = new MessageBean(CodeMsg.USERACTION_UPDATEPASSWORD_SUCCESS_CODE,
						getText(CodeMsg.USERACTION_UPDATEPASSWORD_SUCCESS_MSG));
			} else {
				//old password incorrect
				message = new MessageBean(CodeMsg.USERACTION_UPDATEPASSWORD_OLDPWD_ERROR_CODE,
						getText(CodeMsg.USERACTION_UPDATEPASSWORD_OLDPWD_ERROR_MSG));
			}
		} else {
			//update failed
			message = new MessageBean(CodeMsg.USERACTION_UPDATEPASSWORD_FAILED_CODE,
					getText(CodeMsg.USERACTION_UPDATEPASSWORD_FAILED_MSG));
		}
		inputStream = new ByteArrayInputStream(JSON.toJSONString(message).getBytes("UTF-8"));
		return "updatePassword";
	}
	/********************************UI page*******************************************/
	public String updatePasswordPage() {
		return "updatePasswordPage";
	}
	
	public String uploadFacePage() {
		return "uploadFacePage";
	}
	
	public String registPage() {
		return "registPage";
	}
	
	public String loginPage() {
		return "loginPage";
	}
	
	public String usercenterPage() {
		User user = (User) session.get("user");
		if (user == null) {
			return "notlogin";
		} else {
			//get the last 12 record
			notes = userService.getLastNotes(user.getId(), LAST_NOTE_NUM);
			noteCount = userService.getNoteCount(user.getId());
		}
		return "usercenterPage";
	}
	
	/****************************getter and setter function***************************/
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public Integer getRemember() {
		return remember;
	}

	public void setRemember(Integer remember) {
		this.remember = remember;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public BigInteger getNoteCount() {
		return noteCount;
	}

	public void setNoteCount(BigInteger noteCount) {
		this.noteCount = noteCount;
	}

	
}
