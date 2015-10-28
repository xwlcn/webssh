package online.webssh.websocket;

import java.io.InputStream;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import online.webssh.utils.WebSshUtil;

public class ShellOutPutTask extends Thread{
	
	private final WebSocketSession session;
	private final InputStream out;

	public ShellOutPutTask(WebSocketSession session, InputStream out) {
		super();
		this.session = session;
		this.out = out;
	}

	@Override
	public void run() {
		super.run();
		
		byte[] buff = new byte[8192];
		StringBuilder sb = new StringBuilder();
		try
		{
			while (session !=null && session.isOpen())
			{
				sb.setLength(0);
				int len = out.read(buff);
				if (len == -1)
					return;
				for (int i = 0; i < len; i++)
				{
					char c = (char) (buff[i] & 0xff);
					sb.append(c);
				}
				if (WebSshUtil.getEncoding(sb.toString()).equals("ISO-8859-1"))
					session.sendMessage(new TextMessage(new String(sb.toString().getBytes("ISO-8859-1"),"UTF-8")));
				else
					session.sendMessage(new TextMessage(new String(sb.toString().getBytes("gb2312"),"UTF-8")));
			}
		}
		catch (Exception e)
		{
		}
	}
	
	
}
