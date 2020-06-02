package com.acme.services;

import java.io.Serializable;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.acme.model.Issue;
import com.acme.model.IssueHistory;
import com.acme.repositories.IssuesHistoryRepository;
import com.acme.repositories.IssuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IssuesService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IssuesRepository repository;
    
    @Autowired
    private IssuesHistoryRepository historyRepository;

    public List<Issue> findAll() {
        Spliterator<Issue> spliterator = repository.findAll().spliterator();
        return StreamSupport.stream(spliterator, false).collect(Collectors.toList());
    }

    public Issue find(Long issueId) {
        return repository.findOne(issueId);
    }
    
    public Issue findOneWithHistory(Long issueId) {
        return repository.findOneWithHistory(issueId);
    }

    public Issue save(final Issue issue) {
        Issue savedIssue = repository.save(issue);
        IssueHistory history = new IssueHistory();
        history.setIssue(savedIssue);
        history.setState(savedIssue.getState());
        historyRepository.save(history);
        return savedIssue;
    }

}