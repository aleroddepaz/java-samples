package com.acme.workflow;

public class WorkflowException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WorkflowException(Exception e) {
        super(e);
    }

}
