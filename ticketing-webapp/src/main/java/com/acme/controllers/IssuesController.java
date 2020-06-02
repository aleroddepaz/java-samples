package com.acme.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acme.assemblers.IssueResourceAssembler;
import com.acme.model.Issue;
import com.acme.services.IssuesService;
import com.acme.workflow.IssuesWorkflow;

@RestController
@RequestMapping("/issues")
public class IssuesController {

    @Autowired
    private IssuesService service;

    @Autowired
    private IssuesWorkflow workflow;

    @Autowired
    private IssueResourceAssembler assembler;

    @RequestMapping(method = RequestMethod.GET)
    public List<IssueDto> findAllIssues() {
        return assembler.toResources(service.findAll());
    }

    @RequestMapping(path = "/{issueId}", method = RequestMethod.GET)
    public IssueDto findIssue(@PathVariable("issueId") Long issueId) {
        Issue issue = service.findOneWithHistory(issueId);
        IssueDto dto = assembler.toResource(issue);
        for (String action : workflow.getTransitions(issue)) {
            dto.add(linkTo(methodOn(IssuesController.class).updateIssue(issueId, action)).withRel(action));
        }
        return dto;
    }

    @RequestMapping(method = RequestMethod.POST)
    public IssueDto createIssue(@RequestBody IssueDto dto) {
        Issue issue = toEntity(dto);
        issue.setState(workflow.getInitialState());
        issue.setClosed(false);
        issue = service.save(issue);
        return assembler.toResource(issue);
    }

    @RequestMapping(path = "{issueId}/{event}", method = RequestMethod.PUT)
    public IssueDto updateIssue(@PathVariable("issueId") Long issueId, @PathVariable("event") String event) {
        Issue issue = workflow.changeState(service.find(issueId), event);
        issue = service.save(issue);
        return assembler.toResource(issue);
    }

    private Issue toEntity(IssueDto dto) {
        Issue entity = new Issue();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setUsername(dto.getUsername());
        entity.setState(dto.getState());
        String attachment = dto.getAttachment();
        if (attachment != null) {
            entity.setAttachment(Base64.getDecoder().decode(attachment));
        }
        return entity;
    }

}
