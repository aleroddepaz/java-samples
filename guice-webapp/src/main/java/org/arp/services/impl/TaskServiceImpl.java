package org.arp.services.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.arp.model.Task;
import org.arp.services.ServiceException;
import org.arp.services.TaskService;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
@Transactional
public class TaskServiceImpl implements TaskService {

    private static final long serialVersionUID = 1L;
    private static final int MAX_LENGTH = 50;

    @Inject
    Provider<EntityManager> provider;

    private EntityManager getEntityManager() {
        return provider.get();
    }

    @Override
    public Task create(String text) throws ServiceException {
        provider.get();
        final String todoText = text == null ? null : text.trim();

        if (todoText == null || "".equals(todoText)) {
            throw new ServiceException("The to-do text must not be empty");
        } else if (todoText.length() > MAX_LENGTH) {
            throw new ServiceException("The to-do text must not have more than " + MAX_LENGTH + " characters");
        }

        Task todo = new Task();
        todo.setText(todoText);
        todo.setCreationDate(new Date());
        getEntityManager().persist(todo);
        return todo;
    }

    @Override
    public List<Task> findAll() {
        TypedQuery<Task> query = getEntityManager().createQuery("SELECT t FROM Task t", Task.class);
        return query.getResultList();
    }

}
