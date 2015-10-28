package online.webssh.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.webssh.utils.EndecryptUtil;
import com.webssh.utils.ValidateUtil;
import com.webssh.utils.WebSshUtil;

import online.webssh.beans.CodeMsg;
import online.webssh.beans.MessageBean;
import online.webssh.beans.PageBean;
import online.webssh.pojos.Machine;
import online.webssh.pojos.User;
import online.webssh.service.MachineService;
import online.webssh.sftp.SftpClient;

public class MachineAction extends BaseAction<Machine>{

	private static final long serialVersionUID = 1L;
	private MachineService machineService;
	private InputStream inputStream;
	private Integer pageNum = 1;
	private String hostKeyWord = "";	//the search keyword
	private String nameKeyWord = "";
	private Integer portKeyWord;
	private Integer keytype;
	private PageBean<Machine> page;
	private Integer[] ids;
	private String error;
	private String success = "success";
	private String cmd;
	private String cmdParam;
	private File file;
	private String fileFileName;
	private Integer permissions;
		
	public String downloadFile() throws UnsupportedEncodingException {
		User user = (User) session.get("user");
		SftpClient sftp = (SftpClient) session.get("sftp");
		
		if(user==null || sftp == null){
			message = new MessageBean(-1, "error, not login.");
			inputStream = WebSshUtil.toJsonMessage(message);
		} else {
			ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;fileName="
			                + java.net.URLEncoder.encode(fileFileName, "UTF-8"));
			try {
				
				inputStream = sftp.downloadFile(fileFileName);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String execCommand() throws IOException{
		User user = (User) session.get("user");
		if(user==null){
			message = new MessageBean(-1, "error, not login.");
			inputStream = WebSshUtil.toJsonMessage(message);
		} else {
			//get the sftp client from session
			SftpClient sftp = (SftpClient) session.get("sftp");
			
			if (sftp != null && sftp.isConnected()) {
				
				try {
					
					switch (cmd) {
					case "cd": sftp.changeDirectory(cmdParam); break;
					case "rm": sftp.deleteFile(cmdParam); break;
					case "mkdir": sftp.mkDir(cmdParam); break;
					case "upload": sftp.uploadFile(file, fileFileName , session); break;
					case "attr": sftp.setAttributes(fileFileName, Integer.valueOf(""+permissions, 8));break;
					}
					
					String json = JSON.toJSONString(sftp.ls());
					json = new String(json.getBytes("GBK"), "UTF-8");
					inputStream = new ByteArrayInputStream(json.getBytes("UTF-8"));
				} catch (IOException e) {
					e.printStackTrace();
					message = new MessageBean(CodeMsg.MACHINEACTION_EXECCOMMAND_ERROR_CODE, 
							CodeMsg.MACHINEACTION_EXECCOMMAND_ERROR_MSG);
				}
				
			} else {
				message = new MessageBean(CodeMsg.MACHINEACTION_CONNECTSFTP_FAILED_CODE, 
						CodeMsg.MACHINEACTION_CONNECTSFTP_FAILED_MSG);
				inputStream = WebSshUtil.toJsonMessage(message);
			}
		}
		return "execCommand";
	}
	
	public String connectSftp() throws UnsupportedEncodingException {
		
		User user = (User) session.get("user");
		Machine m = (Machine) session.get("currentMachine");
		SftpClient sftp = (SftpClient) session.get("sftp");
		
		if (user == null) {
			message = new MessageBean(-1, "error, not login.");
			inputStream = WebSshUtil.toJsonMessage(message);
		} else {
			
			//param machine.id != null, then open the new sftp
			if (model.getId() != null) {
				String hql = "from Machine where id=? and user.id=?";
				m = machineService.findOneByHql(hql, model.getId(), user.getId());
				m.setUser(null);
				sftp = new SftpClient(m, user.getId() + "");
				session.put("currentMachine", m);
			} 
			//else if param machine.id == null and have no machine in session, then send error
			else if (m == null) {
				message = new MessageBean(-1, "no opened machine in session.");
				inputStream = WebSshUtil.toJsonMessage(message);
				return "connectSftp";
			}
			//else have machine in session ,but it is put in when open shell terminal, so should create new sftp client
			else if (sftp == null){
				sftp = new SftpClient(m, user.getId() + "");
			}
			

			if (sftp != null && !sftp.isConnected()) {
				message = new MessageBean(CodeMsg.MACHINEACTION_CONNECTSFTP_FAILED_CODE, 
						CodeMsg.MACHINEACTION_CONNECTSFTP_FAILED_MSG);
				inputStream = WebSshUtil.toJsonMessage(message);
				session.remove("sftp");
			} else {
				try {
					String json = JSON.toJSONString(sftp.ls());
					json = new String(json.getBytes("GBK"), "UTF-8");
					inputStream = new ByteArrayInputStream(json.getBytes("UTF-8"));
					
					//put the sftp client into session
					session.put("sftp", sftp);
				
				} catch (IOException e) {
					e.printStackTrace();
					message = new MessageBean(CodeMsg.MACHINEACTION_CONNECTSFTP_SYS_ERROR_CODE, 
							CodeMsg.MACHINEACTION_CONNECTSFTP_SYS_ERROR_MSG);
					inputStream = WebSshUtil.toJsonMessage(message);
				}
			}
		}
		return "connectSftp";
	}
	
	public String stopConnection() {
		session.remove("currentMachine");
		return SUCCESS;
	}
	
	//get upload percent state
	public String uploadState() throws UnsupportedEncodingException {
		String state = (String) session.get("progress");
		if (state != null) {
			inputStream = new ByteArrayInputStream(state.getBytes("UTF-8"));
			if (state.indexOf("100") != -1) {
				session.remove("progress"); 
			}
		} else {
			inputStream = new ByteArrayInputStream("{\"percent\":\"0.00%\",\"num\":\"0\"}".getBytes("UTF-8"));
		}
		return "uploadState";
	}
	
	public String addMachine() {
		System.out.println(model);
		User user = (User) session.get("user");
		if(user==null){
			//not login
			return "notlogin";
		}else if(ValidateUtil.validStrings(model.getName()) && ValidateUtil.validStrings(model.getHostname()) &&
				ValidateUtil.validStrings(model.getUsername()) && ValidateUtil.validStrings(model.getPassword()) && 
				model.getPort()!=null) {
			model.setUser(user);
			
			//encrypt the password, use its hostname as the key
			model.setPassword(EndecryptUtil.get3DESEncrypt(model.getPassword(), user.getId()+""));
			machineService.save(model);
			
			//add success
			message = new MessageBean(CodeMsg.MACHINEACTION_ADDMACHINE_SUCCESS_CODE,
					getText(CodeMsg.MACHINEACTION_ADDMACHINE_SUCCESS_MSG));
		}else{
			//input error
			message = new MessageBean(CodeMsg.MACHINEACTION_ADDMACHINE_INPUT_ERROR_CODE,
					getText(CodeMsg.MACHINEACTION_ADDMACHINE_INPUT_ERROR_MSG));
		}
		inputStream = WebSshUtil.toJsonMessage(message);
		return "addMachine";
	}
	
	public String machinelist() {
		System.out.println(hostKeyWord+ "|" + nameKeyWord +"|" +portKeyWord);
		User user = (User) session.get("user");
		if (user == null) {
			return "notlogin";
		} else {
			page = new PageBean<Machine>();
			page.setPageNum(pageNum);
			if (portKeyWord != null) {
				page.setQuery("from Machine where user.id=" + user.getId() + 
						" and name like ? and hostname like ? and port=?");
				machineService.findOnePage(page, "%" + nameKeyWord + "%", "%" + hostKeyWord + "%", portKeyWord);
			} else {
				page.setQuery("from Machine where user.id=" + user.getId() + 
						" and name like ? and hostname like ?");
				machineService.findOnePage(page, "%" + nameKeyWord + "%", "%" + hostKeyWord + "%");
			}
		}
		return "machinelist";
	}
	
	public String deleteMachines() {
		User user = (User) session.get("user");
		if (user != null && ids != null && ids.length > 0) {
			String sql = "delete from machine where uid=" + user.getId() + " and id in(";
			for (int i = 0; i < ids.length - 1; i++) {
				sql += ids[i] + ",";
			}
			sql += ids[ids.length - 1] + ")";
			machineService.executeSql(sql);
			
			//success
			message = new MessageBean(CodeMsg.MACHINEACTION_DELETEMACHINES_SUCCESS_CODE,
					getText(CodeMsg.MACHINEACTION_DELETEMACHINES_SUCCESS_MSG));
		} else {
			//failed
			message = new MessageBean(CodeMsg.MACHINEACTION_DELETEMACHINES_FAILED_CODE,
					getText(CodeMsg.MACHINEACTION_DELETEMACHINES_FAILED_MSG));
		}
		inputStream = WebSshUtil.toJsonMessage(message);
		return "deleteMachines";
	}
	
	//update the machine
	public String updateMachine() {
		User user = (User) session.get("user");
		if (user != null && model.getId() != null) {
			machineService.updatemachine(user.getId(), model);
			//success
			message = new MessageBean(CodeMsg.MACHINEACTION_UPDATEMACHINE_SUCCESS_CODE,
					getText(CodeMsg.MACHINEACTION_UPDATEMACHINE_SUCCESS_MSG));
		} else {
			//failed
			message = new MessageBean(CodeMsg.MACHINEACTION_UPDATEMACHINE_FAILED_CODE,
					getText(CodeMsg.MACHINEACTION_UPDATEMACHINE_FAILED_MSG));
		}
		inputStream = WebSshUtil.toJsonMessage(message);
		return "updateMachine";
	}
	/******************************go to UI function*********************************/
	public String updateMachinePage() {
		if (model != null && model.getId() != null) {
			model = machineService.get(model.getId());
		}
		return "updateMachinePage";
	}

	public String addMachinePage() {
		return "addMachinePage";
	}
	
	public String openShellTerminalPage() {
		User user = (User) session.get("user");
		if (user == null) {
			return "notlogin";
		} else {
			if (model.getId() != null) {
				
				//get the machine and put into the session
				String hql = "from Machine where id=? and user.id=?";
				Machine m = machineService.findOneByHql(hql, model.getId(), user.getId());
				m.setUser(null);
				session.put("currentMachine", m);
			}
		}
		return "openShellTerminalPage";
	}
	
	public String openSftpWindowPage() {
		User user = (User) session.get("user");
		if (user == null) {
			return "notlogin";
		}
		return "openSftpWindowPage";
	}
	
	/**********************getter and setter function********************************/
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public MachineService getMachineService() {
		return machineService;
	}

	public void setMachineService(MachineService machineService) {
		this.machineService = machineService;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public PageBean<Machine> getPage() {
		return page;
	}

	public void setPage(PageBean<Machine> page) {
		this.page = page;
	}

	public String getHostKeyWord() {
		return hostKeyWord;
	}

	public void setHostKeyWord(String hostKeyWord) {
		this.hostKeyWord = hostKeyWord;
	}

	public String getNameKeyWord() {
		return nameKeyWord;
	}

	public void setNameKeyWord(String nameKeyWord) {
		this.nameKeyWord = nameKeyWord;
	}

	public Integer getPortKeyWord() {
		return portKeyWord;
	}

	public void setPortKeyWord(Integer portKeyWord) {
		this.portKeyWord = portKeyWord;
	}

	public Integer getKeytype() {
		return keytype;
	}

	public void setKeytype(Integer keytype) {
		this.keytype = keytype;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getCmdParam() {
		return cmdParam;
	}

	public void setCmdParam(String cmdParam) {
		this.cmdParam = cmdParam;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) throws UnsupportedEncodingException {
		this.fileFileName = fileFileName;
	}

	public Integer getPermissions() {
		return permissions;
	}

	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}
	
}
