package com.finleap.statemachine.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.statemachine.StateMachineContext;

/**
 * Created by anlcan on 20/01/2017.
 *
 * http://docs.aws.amazon.com/step-functions/latest/dg/awl-ref-states-pass.html
 */
public class PassState extends State {

    /**
     * Treated as the output of a virtual task to be passed on to the next state,
     * and filtered as prescribed by the ResultPath field (if present). [Optional]
     */
    @JsonProperty("Result")
    public String result;

    public PassState() {
        this.type = StateType.PASS;
    }

    @Override
    public void run(StateMachineContext context) {
        // parse input
        // add result

    }
}
