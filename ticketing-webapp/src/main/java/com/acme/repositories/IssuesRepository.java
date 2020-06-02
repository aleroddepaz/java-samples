package com.acme.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acme.model.Issue;

@Repository
public interface IssuesRepository extends CrudRepository<Issue, Long> {

    @Query(value = "SELECT i FROM Issue i LEFT JOIN FETCH i.history where i.id = :id")
    Issue findOneWithHistory(@Param("id") Long id);

}