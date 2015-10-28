package online.webssh.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import online.webssh.pojos.User;
import online.webssh.service.UserService;

@SuppressWarnings("rawtypes")
public class FileUploadAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private Integer id;
	private File file;
	private String fileFileName;
	private static final String saveDir = "/images/faces/";
	
	public String uploadFace() throws IOException {
		User user = (User) session.get("user");
		if(user != null && file != null && fileFileName != null) {
			String realpath = ServletActionContext.getServletContext().getRealPath("/");
			System.out.println(realpath);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
	        String newName = sdf.format(new Date()) + getExtention(fileFileName);
	        //delete the old image
	        String old = user.getFacePath();
	        File oldFile = new File(realpath + old);
	        if(oldFile != null)
	        	oldFile.delete();
			File savefile = new File(new File(realpath + saveDir), newName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(file, savefile);
            ActionContext.getContext().put("message", "头像上传成功！");
			user.setFacePath(saveDir  + newName);
			userService.saveOrUpdate(user);
		}
		return "uploadFace";
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	private static String getExtention(String fileName) {  
        int pos = fileName.lastIndexOf(".");  
        return fileName.substring(pos);  
    } 
}
