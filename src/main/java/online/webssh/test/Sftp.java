package online.webssh.test;

import java.io.IOException;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3FileAttributes;

public class Sftp {
	
	public static void main(String[] args) throws IOException {
		Connection conn = new Connection("210.13.106.85");
		conn.connect();
		conn.authenticateWithPassword("root", "mima123&*(");
		
		SFTPv3Client client = new SFTPv3Client(conn);
		
		SFTPv3FileAttributes attr = client.stat("spawn-fcgi-1.6.3");
		
		SFTPv3FileAttributes attr1 = new SFTPv3FileAttributes();
		attr1.permissions = attr.permissions / 8 / 8 / 8 * 8 * 8 * 8 + 0757;
		client.setstat("spawn-fcgi-1.6.3", attr1);
		//System.out.println(attr.getOctalPermissions());
		//client = client.openDirectory("..").getClient();

		//System.out.println(client.canonicalPath("."));
		
		//SFTPv3FileHandle handle = client.openFileRW("Hello.txt");
		
		/*SFTPv3FileHandle handle = client.createFile("fastjson1.2.2.rar");
		
		File file = new File("F:\\BaiduYunDownload\\SSH 框架\\fastjson1.2.2.rar");
		
		FileInputStream fis = new FileInputStream(file);
		
		long totalSize = file.length();
		
		byte[] buff = new byte[1024*8];
		
		long count = 0;
		
		DecimalFormat df = new DecimalFormat("#.00");
		
		System.out.println("current upload %0.00");
		while (true) {
			int len = fis.read(buff);
			if (len < 0)
				break;
			client.write(handle,count, buff, 0, len);
			count += len;
			System.out.println("current upload %" + df.format((double)count / totalSize * 100) );
		}
		client.closeFile(handle);
		fis.close();
		System.out.println("upload finished.");

		List<SFTPv3DirectoryEntry> list = client.ls("/root");
		for (SFTPv3DirectoryEntry entry : list) {
			
			System.out.println(entry.filename + "\t" + 
					new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new  Date((long)entry.attributes.mtime * 1000)) + "\t" + 
					getStringPermission(entry.attributes.permissions) + "\t" 
					);
		}*/
	}
	
	public static String getStringPermission(Integer p) {
		String temp[] = new String[] {"---", "--x", "-w-", "-wx", "r--", "r-x", "rw-", "rwx"};
		return temp[p / 8 / 8 % 8] + temp[p / 8 % 8] + temp[p % 8];
	}
}
