package org.arp.services;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.arp.domain.User;

public class UsersService {
	
	private static final AtomicInteger ID = new AtomicInteger(0);
	private static final Map<Integer,User> USERS = new ConcurrentHashMap<>();
	
	public User addUser(String username) {
		Integer id = ID.incrementAndGet();
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		USERS.put(id, user);
		return user;
	}
	
	public Collection<User> findAllUsers() {
		return Collections.unmodifiableCollection(USERS.values());
	}

}
