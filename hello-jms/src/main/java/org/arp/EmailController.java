package org.arp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private JmsTemplate jmsTemplate;

	@PostMapping("/{mailbox}")
	public Email postEmail(@PathVariable("mailbox") String mailbox, Email email) {
		jmsTemplate.convertAndSend(mailbox, email);
		return email;
	}

}