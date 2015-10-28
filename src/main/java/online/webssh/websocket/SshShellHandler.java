package online.webssh.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import online.webssh.pojos.Machine;
import online.webssh.pojos.User;

@RequestMapping("sshShellHandler")
public class SshShellHandler extends TextWebSocketHandler{
	
	public static final Map<Integer, List<WebSocketSession>> userSocketSessionMap;
	private User user;
	private Machine currentMachine = null;
	private SshClient sshClient = null;
	private List<WebSocketSession> sessionList = null;
	
	static {
		userSocketSessionMap = new HashMap<Integer, List<WebSocketSession>>();
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);

		try {
			if (sshClient != null) {
				
				//receive a close cmd ?
				if (Arrays.equals("exit".getBytes(), message.asBytes())) {
					
					if (sshClient != null) {
						sshClient.disconnect();
					}
					
					session.close();
					return ;
				}
				sshClient.write(new String(message.asBytes(), "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.sendMessage(new TextMessage("An error occured, websocket is closed."));
			session.close();
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		if (sshClient != null) {
			sshClient.disconnect();
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		
		currentMachine = (Machine) session.getAttributes().get("currentMachine");
		user = (User) session.getAttributes().get("user");
		
		//first close other shell
		Iterator<Entry<Integer, List<WebSocketSession>>> it = userSocketSessionMap.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<Integer, List<WebSocketSession>> entry = it.next();
			//close self other terminal connection.
			if (entry.getKey().equals(user.getId())) {
				sessionList = entry.getValue();
				for (int i = sessionList.size() - 1; i >= 0; i--) {
					WebSocketSession wss = sessionList.get(i);
					if (wss.isOpen()) {
						wss.sendMessage(new TextMessage("Another shell terminal is open, so this terminal changed to closed."));
						wss.close();
					}
					sessionList.remove(i);
				}
			}
		}
		//update current in using machine
		if (currentMachine != null) {
			
			if (sessionList == null)
				sessionList = new ArrayList<>();
			
			sessionList.add(session);
			userSocketSessionMap.put(user.getId(), sessionList);
			System.out.println("connect");
			sshConnect(session, currentMachine, user.getId());
		}
	}
	
	private void sshConnect(WebSocketSession session, Machine machine, Integer spkey) {
		
		sshClient = new SshClient();
		
		try {
			session.sendMessage(new TextMessage("Try to connect...\r"));
			if (sshClient.connect(machine, spkey)) {
				sshClient.startShellOutPutTask(session);
			}
			else {
				sshClient.disconnect();
				session.sendMessage(new TextMessage("Connect failed, please confirm the username or password try agin."));
				session.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
