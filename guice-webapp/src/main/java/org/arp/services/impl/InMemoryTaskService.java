package org.arp.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.arp.model.Task;
import org.arp.services.ServiceException;
import org.arp.services.TaskService;

public class InMemoryTaskService implements TaskService {

    private static final long serialVersionUID = 1L;

    private static final int MAX_LENGTH = 50;

    private final Map<Long, Task> todos = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @Override
    public Task create(String text) throws ServiceException {
        final String todoText = text == null ? null : text.trim();

        if (todoText == null || "".equals(todoText)) {
            throw new ServiceException("The to-do text must not be empty");
        } else if (todoText.length() > MAX_LENGTH) {
            throw new ServiceException("The to-do text must not have more than " + MAX_LENGTH + " characters");
        }

        long id = counter.incrementAndGet();
        Task todo = new Task();
        todo.setId(id);
        todo.setText(todoText);
        todo.setCreationDate(new Date());
        todos.put(id, todo);

        return todo;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(todos.values());
    }

}
