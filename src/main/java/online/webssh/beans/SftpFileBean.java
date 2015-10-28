package online.webssh.beans;

public class SftpFileBean implements Comparable<SftpFileBean>{
	
	private String filename = null;
	private Long size = null;
	private Integer intPermissions = null;
	private String strPermissions = null;
	private String octalPermissions = null;
	private String mtime = null;
	private boolean directory;
	
	public SftpFileBean() {
		super();
	}
	

	public SftpFileBean(String filename, Long size, Integer intPermissions, String strPermissions,
			String octalPermissions, String mtime, boolean directory) {
		super();
		this.filename = filename;
		this.size = size;
		this.intPermissions = intPermissions;
		this.strPermissions = strPermissions;
		this.octalPermissions = octalPermissions;
		this.mtime = mtime;
		this.directory = directory;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public Long getSize() {
		return size;
	}


	public void setSize(Long size) {
		this.size = size;
	}


	public Integer getIntPermissions() {
		return intPermissions;
	}


	public void setIntPermissions(Integer intPermissions) {
		this.intPermissions = intPermissions;
	}


	public String getStrPermissions() {
		return strPermissions;
	}


	public void setStrPermissions(String strPermissions) {
		this.strPermissions = strPermissions;
	}


	public String getOctalPermissions() {
		return octalPermissions;
	}


	public void setOctalPermissions(String octalPermissions) {
		this.octalPermissions = octalPermissions;
	}


	public String getMtime() {
		return mtime;
	}


	public void setMtime(String mtime) {
		this.mtime = mtime;
	}


	public boolean isDirectory() {
		return directory;
	}


	public void setDirectory(boolean directory) {
		this.directory = directory;
	}


	@Override
	public int compareTo(SftpFileBean o) {
		if (this.directory && !o.directory) {
			return -1;
		} else if (!(this.directory ^ o.directory)) {
			return this.filename.compareTo(o.filename);
		} else if (!this.directory && o.directory) {
			return 1;
		}
		return 0;
	}
}
