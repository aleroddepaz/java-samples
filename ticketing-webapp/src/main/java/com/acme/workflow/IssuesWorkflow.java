package com.acme.workflow;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.scxml.SCXMLExecutor;
import org.apache.commons.scxml.TriggerEvent;
import org.apache.commons.scxml.env.AbstractStateMachine;
import org.apache.commons.scxml.model.ModelException;
import org.apache.commons.scxml.model.SCXML;
import org.apache.commons.scxml.model.State;
import org.apache.commons.scxml.model.Transition;
import org.apache.commons.scxml.model.TransitionTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acme.model.Issue;

@Component
public class IssuesWorkflow implements Serializable {

    private static final long serialVersionUID = 1L;

    private final SCXML scxml;

    @Autowired
    public IssuesWorkflow(SCXML scxml) {
        this.scxml = scxml;
    }

    public String getInitialState() {
        return getExecutor().getStateMachine().getInitial();
    }

    public Issue changeState(Issue issue, String event) {
        SCXMLExecutor executor = getExecutor(issue);
        try {
            executor.triggerEvent(new TriggerEvent(event, TriggerEvent.SIGNAL_EVENT));
            issue.setState(getCurrentState(executor).getId());
            issue.setClosed(executor.getCurrentStatus().isFinal());
            return issue;
        } catch (ModelException e) {
            throw new WorkflowException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getTransitions(Issue issue) {
        SCXMLExecutor executor = getExecutor(issue);
        List<Transition> list = (List<Transition>) getCurrentState(executor).getTransitionsList();
        return list.stream().map(t -> t.getEvent()).collect(Collectors.toList());
    }

    private SCXMLExecutor getExecutor() {
        return new IssuesStateMachine(scxml).getEngine();
    }

    private SCXMLExecutor getExecutor(Issue issue) {
        SCXMLExecutor executor = getExecutor();
        SCXML stateMachine = executor.getStateMachine();
        TransitionTarget target = (TransitionTarget) stateMachine.getTargets().get(issue.getState());
        stateMachine.setInitialTarget(target);
        try {
            executor.go();
        } catch (ModelException e) {
            throw new WorkflowException(e);
        }
        return executor;
    }

    private State getCurrentState(SCXMLExecutor executor) {
        return (State) executor.getCurrentStatus().getStates().iterator().next();
    }

    private final class IssuesStateMachine extends AbstractStateMachine {

        public IssuesStateMachine(final SCXML stateMachine) {
            super(stateMachine);
        }

        @Override
        public boolean invoke(String method) {
            return true;
        }

    }

}