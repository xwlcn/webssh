package online.webssh.sftp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import ch.ethz.ssh2.SFTPv3FileAttributes;
import ch.ethz.ssh2.SFTPv3FileHandle;
import online.webssh.beans.SftpBean;
import online.webssh.beans.SftpFileBean;
import online.webssh.pojos.Machine;
import online.webssh.utils.EndecryptUtil;

public class SftpClient {
	
	private Connection conn;
	private SFTPv3Client client;
	private Stack<String> catalogs = new Stack<>();
	private String cutrrentCatalog;
	
	public SftpClient() {
	}
	
	public SftpClient(Machine m, String spkey) {
		super();
		connect(m, spkey);
	}

	public boolean connect(Machine m, String spkey) {
		
		try {
			conn = new Connection(m.getHostname());
			conn.connect();
			if (!conn.authenticateWithPassword(m.getUsername(), 
					EndecryptUtil.get3DESDecrypt(m.getPassword(), spkey)))
				return false;
			client = new SFTPv3Client(conn);
			
			//init the current catalogs
			initCatalogs(client.canonicalPath("."));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean isConnected() {
		if (client != null)
			return client.isConnected();
		return false;
	}
	
	private void initCatalogs(String str) {
		
		cutrrentCatalog = str;
		catalogs.push("/");
		
		String[] ss = str.split("/");
		
		for (String s : ss) {
			if (!"".equals(s))
				catalogs.push(s);
		}
	}
	
	public String getCurrentCatalog() {
		return cutrrentCatalog;
	}
	
	public void changeDirectory(String dirName) {
		
		//if param is a absolute path
		if ("/".equals(dirName.substring(0, 1))) {
			catalogs.clear();
			initCatalogs(dirName);
			cutrrentCatalog = dirName;
			return ;
		}
		
		//parent directory
		if ("..".equals(dirName) && catalogs.size() > 1) {
			//update the catalogs stack
			catalogs.pop();
		} 
		//child directory
		else {
			catalogs.push(dirName);
		}
		updateCurrentCatalog();
	}
	
	private void updateCurrentCatalog() {
		String path = "";
		for (String s : catalogs) {
			if (!"/".equals(s))
				path += "/" + s;
		}
		if (catalogs.size() == 1)
			path = "/";
		cutrrentCatalog = path;
	}
	
	public SftpBean ls() throws IOException {
		
		List<SFTPv3DirectoryEntry> list =  client.ls(cutrrentCatalog);
		List<SftpFileBean> sftpFiles = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		for (SFTPv3DirectoryEntry entry : list) {
			
			if (!entry.filename.equals("..") && !entry.filename.equals(".")) {
				SftpFileBean sfile = new SftpFileBean();
				sfile.setFilename(entry.filename);
				sfile.setDirectory(entry.attributes.isDirectory());
				sfile.setIntPermissions(entry.attributes.permissions);
				sfile.setStrPermissions(getStringPermission(entry.attributes.permissions));
				sfile.setOctalPermissions(entry.attributes.getOctalPermissions());
				sfile.setMtime(sdf.format(new  Date((long)entry.attributes.mtime * 1000)));
				sfile.setSize(entry.attributes.size);
				sftpFiles.add(sfile);
			}
		}
		
		Collections.sort(sftpFiles, new Comparator<SftpFileBean>() {
			@Override
			public int compare(SftpFileBean o1, SftpFileBean o2) {
				return o1.compareTo(o2);
			}
		});
		
		return new SftpBean(getCurrentCatalog(), sftpFiles);
	}
	
	private String getStringPermission(Integer p) {
		String temp[] = new String[] {"---", "--x", "-w-", "-wx", "r--", "r-x", "rw-", "rwx"};
		//is a directory ?
		return (p / 8 / 8 / 8 / 8 % 8 == 04 ? "d" : "-") + 
				temp[p / 8 / 8 % 8] + temp[p / 8 % 8] + temp[p % 8];
	}
	
	//upload file to current catalog
	public void uploadFile(File file, String fileName, Map<String, Object> session) throws IOException {
		
		if (file == null)
			return ;
		FileInputStream fis = new FileInputStream(file);
		
		long totalSize = file.length();
		byte[] b = new byte[1024*8];
		long count = 0;
		
		SFTPv3FileHandle handle = client.createFile(getCurrentCatalog() + "/" + fileName);
		DecimalFormat df = new DecimalFormat("#.00");
		
		while (true) {
			
			int len = fis.read(b);
			if (len == -1)
				break;
			client.write(handle, count, b, 0, len);
			count += len;
			
			session.put("progress", "{\"percent\":\""+df.format((double)count / totalSize * 100)+"%\",\"num\":\""+(int)((double)count / totalSize)+"\"}");
		}
		client.closeFile(handle);
		fis.close();
	}
	
	//download file from current catalog
	public InputStream downloadFile(String fileName) throws IOException {
		
		System.out.println(getCurrentCatalog() + "/" + fileName);
		SFTPv3FileHandle handle = client.openFileRO(getCurrentCatalog() + "/" + fileName);
		
		long count = 0;
		byte[] buff = new byte[1024*8];
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		while (true) {
			int len = client.read(handle, count, buff, 0, 1024 * 8);
			if (len == -1)
				break;
			baos.write(buff, 0, len);
			count += len;
		}
		
		return new ByteArrayInputStream(baos.toByteArray());
	}
	
	public void deleteFile(String fileName) throws IOException {
		client.rm(getCurrentCatalog() + "/" + fileName);
	}
	
	public void mkDir(String dirName) throws IOException {
		client.mkdir(getCurrentCatalog() + "/" + dirName, 0755);
	}
	
	public void createFile(String fileName) throws IOException {
		client.createFileTruncate(getCurrentCatalog() + "/" + fileName);
	}
	
	public void setAttributes(String fileName, Integer permissions) throws IOException {
		
		SFTPv3FileAttributes attr = client.stat(getCurrentCatalog() + "/" + fileName);
		
		SFTPv3FileAttributes attr1 = new SFTPv3FileAttributes();
		attr1.permissions = attr.permissions / 8 / 8 / 8 * 8 * 8 * 8 + permissions;
		client.setstat(getCurrentCatalog() + "/" + fileName, attr1);
	}
}
