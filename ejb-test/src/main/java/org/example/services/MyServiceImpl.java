package org.example.services;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.example.rest.MyEntity;

@Named
public class MyServiceImpl implements MyService {

    @PersistenceContext
    EntityManager em;

    @Override
    public MyEntity find(Long id) {
        return em.find(MyEntity.class, id);
    }

    @Override
    public MyEntity insert(MyEntity myEntity) {
        try {
            em.persist(myEntity);
            return myEntity;
        } catch (Exception e) {
            System.out.println("Exception in JPA persist: ");
            System.out.println(e.getClass().getName());
            throw e;
        }
    }

    @Override
    public MyEntity update(MyEntity myEntity) {
        try {
            MyEntity merge = em.merge(myEntity);
            em.flush();
            return merge;
        } catch (Exception e) {
            System.out.println("Exception in JPA merge: ");
            System.out.println(e.getClass().getName());
            throw e;
        }
    }

}
