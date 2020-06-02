package com.acme.repositories;

import com.acme.model.IssueHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuesHistoryRepository extends CrudRepository<IssueHistory, Long> {
}