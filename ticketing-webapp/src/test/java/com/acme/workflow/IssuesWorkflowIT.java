package com.acme.workflow;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acme.Application;
import com.acme.model.Issue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class IssuesWorkflowIT {

    @Autowired
    IssuesWorkflow workflow;

    @Test
    public void testGetInitialState() {
        assertEquals("open", workflow.getInitialState());
    }

    @Test
    public void testChangeState() {
        Issue issue = new Issue();
        issue.setState("pending");
        Issue changedIssue = workflow.changeState(issue, "addInfo");
        assertEquals("assigned", changedIssue.getState());
    }

    @Test
    public void testGetTransitions() {
        Issue issue = new Issue();
        issue.setState("assigned");
        List<String> transitions = workflow.getTransitions(issue);
        assertEquals(Arrays.asList("reassign", "requestInfo", "close"), transitions);
    }

}