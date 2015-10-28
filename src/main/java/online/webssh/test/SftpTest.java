package online.webssh.test;

import java.io.IOException;

import online.webssh.pojos.Machine;
import online.webssh.sftp.SftpClient;

public class SftpTest {
	
	public static void main(String[] args) throws IOException {
		Machine m = new Machine();
		m.setHostname("210.13.106.85");
		m.setUsername("root");
		m.setPassword("tuQkZjFB0au0rivr2DLLHtJReP60RJiS");
		SftpClient sftp = new SftpClient(m, 1+"");
		//sftp.changeDirectory("..");
		//System.out.println(sftp.getCurrentCatalog());
		System.out.println(sftp.ls());
		sftp.createFile("Hello.txt");
		System.out.println("end");
	}
}
