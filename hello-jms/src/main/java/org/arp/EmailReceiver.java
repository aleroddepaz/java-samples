package org.arp;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailReceiver {

	@JmsListener(destination = "spam")
    public void receiveMessage(Email email) {
		System.out.println("Received <" + email + ">");
    }

}