package com.acme.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.acme.assemblers.IssueResourceAssembler;
import com.acme.model.Issue;
import com.acme.services.IssuesService;
import com.acme.workflow.IssuesWorkflow;

@RunWith(MockitoJUnitRunner.class)
public class IssuesControllerTest {

    private static final String HREF_SELF_1 = "http://localhost/issues/1";

    @Mock
    private IssuesService service;

    @Mock
    private IssuesWorkflow workflow;

    @Spy
    private IssueResourceAssembler assembler;

    @InjectMocks
    private IssuesController controller;

    @Before
    public void setUp() {
        // Simulate Spring MVC context
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void testFindAllIssues() {
        Issue issue = new Issue();
        issue.setId(1L);
        when(service.findAll()).thenReturn(Arrays.asList(issue));

        List<IssueDto> issuesDto = controller.findAllIssues();

        assertFalse(issuesDto.isEmpty());
        assertEquals(HREF_SELF_1, issuesDto.get(0).getId().getHref());
    }

    @Test
    public void testFindIssue() {
        Issue issue = new Issue();
        issue.setId(1L);
        when(service.find(eq(1L))).thenReturn(issue);

        IssueDto issueDto = controller.findIssue(1L);

        assertNotNull(issueDto);
        assertEquals(HREF_SELF_1, issueDto.getId().getHref());
    }

}