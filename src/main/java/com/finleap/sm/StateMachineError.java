package com.finleap.sm;

/**
 * Created by anlcan on 26/01/2017.
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
}
