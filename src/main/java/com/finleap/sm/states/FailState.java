package com.finleap.sm.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.sm.Context;
import com.finleap.sm.StateExecutionException;

/**
 * Created by anlcan on 20/01/2017.
 *
 * http://docs.aws.amazon.com/step-functions/latest/dg/awl-ref-states-fail.html
 */
public class FailState extends State {

    @JsonProperty("Cause")
    public String cause;

    public FailState() {
        this.type = StateType.FAIL;
    }

    @Override
    public void run(Context context) {
        throw new StateExecutionException(cause);
    }
}
