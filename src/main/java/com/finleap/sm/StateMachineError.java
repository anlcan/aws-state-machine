package com.finleap.sm;

/**
 * Created by anlcan on 26/01/2017.
 *
 * http://docs.aws.amazon.com/step-functions/latest/dg/awl-ref-errors.html
 *
 * Any state can encounter runtime errors.
 * Errors can arise because of state machine definition issues
 * (e.g. no matching rule in a Choice state),
 * task failures (e.g. an exception thrown by a Lambda function)
 * or because of transient issues, such as network partition events.
 *
 * When a state reports an error, the default course of action for AWS Step
 * Functions is to fail the execution entirely.
 */
public class StateMachineError extends RuntimeException {

    private String error;

    public StateMachineError(String message) {
        super(message);
    }

    public StateMachineError(String message,String error) {
        super(message);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
