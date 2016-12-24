package org.arp.chat.websocket;

import java.io.Serializable;
import java.util.Date;

public class ChatMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String username;
	private final String text;
	private final Date date;

	public ChatMessage(String username, String text, Date date) {
		this.username = username;
		this.text = text;
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public String getText() {
		return text;
	}

	public Date getDate() {
		return date;
	}

}
