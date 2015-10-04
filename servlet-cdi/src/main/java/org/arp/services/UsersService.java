package org.arp.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.arp.domain.User;
import org.slf4j.Logger;

@ApplicationScoped
public class UsersService implements Serializable {

	private static final long serialVersionUID = 1L;

	private final AtomicInteger counter = new AtomicInteger(0);
	private final Map<Integer, User> users = new ConcurrentHashMap<>();
	
	@Inject
	private Logger logger;

	public User addUser(String username) {
		if(username == null || "".equals(username.trim())) {
			throw new ServiceException("Username cannot be empty");
		}
		Integer id = counter.incrementAndGet();
		User user = new User();
		user.setId(id);
		user.setUsername(username.trim());
		
		users.put(id, user);
		logger.info("Stored a new user: {}", user);
		return user;
	}

	public Collection<User> findAllUsers() {
		return Collections.unmodifiableCollection(users.values());
	}

}
