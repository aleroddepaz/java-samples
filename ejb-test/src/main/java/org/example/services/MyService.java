package org.example.services;

import org.example.rest.MyEntity;

public interface MyService {

    public MyEntity find(Long id);

    public MyEntity insert(MyEntity myEntity);

    public MyEntity update(MyEntity myEntity);

}
