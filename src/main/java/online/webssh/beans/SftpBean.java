package online.webssh.beans;

import java.util.List;

public class SftpBean {

	private String currentCatalog;
	private List<SftpFileBean> files;
	
	public SftpBean() {
		super();
	}
	public SftpBean(String currentCatalog, List<SftpFileBean> files) {
		super();
		this.currentCatalog = currentCatalog;
		this.files = files;
	}
	public String getCurrentCatalog() {
		return currentCatalog;
	}
	public void setCurrentCatalog(String currentCatalog) {
		this.currentCatalog = currentCatalog;
	}
	public List<SftpFileBean> getFiles() {
		return files;
	}
	public void setFiles(List<SftpFileBean> files) {
		this.files = files;
	}
	
	
}
