package com.finleap.sm;

/**
 * Created by anlcan on 25/01/2017.
 */
public class StateExecutionException extends RuntimeException{


    public StateExecutionException(String cause) {
        super(cause);
    }

    public StateExecutionException(InterruptedException e) {
        super(e);
    }
}
