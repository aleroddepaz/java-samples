package org.arp.chat.websocket;

import java.io.Serializable;
import java.util.Date;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ServerEndpoint(value = "/chat", encoders = JsonEncoder.class)
public class ChatEndpoint implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatEndpoint.class);

    @OnError
    public void onError(Throwable t) {
        LOGGER.error("Error on chat endpoint", t);
    }

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