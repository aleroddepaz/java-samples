package org.arp.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.arp.model.Todo;

@Stateless
@LocalBean
@Transactional(value=TxType.SUPPORTS)
public class TodoService {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Todo> findAllTodos() {
		TypedQuery<Todo> query = em.createNamedQuery("Todo.findAll", Todo.class);
		return query.getResultList();
	}
	
	@Transactional(value=TxType.REQUIRED)
	public Todo createTodo(final Todo todo) {
		em.persist(todo);
		return todo;
	}
	
	@Transactional(value=TxType.REQUIRED)
	public boolean removeTodo(Long id) {
		Todo todo = em.find(Todo.class, id);
		if(todo != null) {
			em.remove(todo);
			return true;
		}
		return false;
	}
}
