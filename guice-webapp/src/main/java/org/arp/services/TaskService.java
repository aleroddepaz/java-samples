package org.arp.services;

import java.io.Serializable;
import java.util.List;

import org.arp.model.Task;

public interface TaskService extends Serializable {

    Task create(String text) throws ServiceException;

    List<Task> findAll();

}
