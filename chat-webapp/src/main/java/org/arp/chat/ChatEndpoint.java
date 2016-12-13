package org.arp.chat;

import java.util.Date;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat", encoders = ChatMessageEncoder.class)
public class ChatEndpoint {

	@OnMessage
	public void onMessage(final String text, final Session session) {
		String username = session.getUserPrincipal().getName();
		Date now = new Date();
		ChatMessage message = new ChatMessage(username, text, now);
		for (Session s : session.getOpenSessions()) {
			s.getAsyncRemote().sendObject(message);
		}
	}

}