package org.arp.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.arp.model.Task;
import org.arp.services.TaskService;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
@Transactional
public class TaskServiceImpl implements TaskService {

    private static final long serialVersionUID = 1L;

    @Inject
    private Provider<EntityManager> em;

    private EntityManager getEntityManager() {
        return em.get();
    }

    @Override
    public Task create(String text) {
        Task todo = new Task();
        todo.setText(text);
        getEntityManager().persist(todo);
        return todo;
    }

    @Override
    public List<Task> findAll() {
        TypedQuery<Task> query = getEntityManager().createQuery("SELECT t FROM Task t", Task.class);
        return query.getResultList();
    }

}