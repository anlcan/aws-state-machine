package com.finleap.sm.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.sm.StateMachineContext;
import com.finleap.sm.StateMachineError;

/**
 * Created by anlcan on 20/01/2017.
 *
 * http://docs.aws.amazon.com/step-functions/latest/dg/awl-ref-states-fail.html
 *
 * A Fail state ("Type": "Fail") stops the execution of the state machine
 * and marks it as a failure.
 */
public class FailState extends State {

    /**
     * Provides a custom failure string that can be used for operational or diagnostic purposes. [Optional]
     */
    @JsonProperty("Cause")
    public String cause;

    /**
     * Provides an error name that can be used for error handling (Retry/Catch),
     * operational or diagnostic purposes. [Optional]
     */
    @JsonProperty("Error")
    public String error = null;

    public FailState() {
        this.type = StateType.FAIL;
        this.end  = true;
    }

    @Override
    public void run(StateMachineContext context) {
        if (error != null)
            throw new StateMachineError(cause, error);
        else
            throw new StateMachineError(cause);

    }
}
