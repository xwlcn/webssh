package online.webssh.websocket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.springframework.web.socket.WebSocketSession;

import ch.ethz.ssh2.Connection;
import online.webssh.pojos.Machine;
import online.webssh.utils.EndecryptUtil;

public class SshClient {
	
	private Connection conn;
	private ch.ethz.ssh2.Session sess;
	private InputStream in;
	private OutputStream out;
	private BufferedWriter inToShell;
	
	public boolean connect(Machine machine, Integer spkey) {
		try {
			conn = new Connection(machine.getHostname(), machine.getPort());
			conn.connect();
			if (!conn.authenticateWithPassword(machine.getUsername(), 
					EndecryptUtil.get3DESDecrypt(machine.getPassword(), spkey + "")))
				return false;
			sess = conn.openSession();
			sess.requestPTY("xterm", 90, 30, 0, 0, null);
			sess.startShell();
			in = sess.getStdout();
			out = sess.getStdin();
			inToShell = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void write(String text) throws IOException {
		if (inToShell != null) {
			inToShell.write(text);
			inToShell.flush();
		}
	}
	
	public void startShellOutPutTask(WebSocketSession session) {
		new ShellOutPutTask(session, in).start();
	}
	
	public void disconnect() {
		if (conn != null)
			conn.close();
		if (sess != null)
			sess.close();
		conn = null;
		sess = null;
	}
}
