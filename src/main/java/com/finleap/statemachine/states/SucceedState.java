package com.finleap.statemachine.states;

import com.finleap.statemachine.StateMachineContext;

/**
 * Created by anlcan on 25/01/2017.
 *
 * http://docs.aws.amazon.com/step-functions/latest/dg/awl-ref-states-succeed.html
 */
public class SucceedState extends State {

    public SucceedState() {
        this.type = StateType.SUCCEED;
        this.end = true;
    }

    @Override
    public void run(StateMachineContext context) {

    }


}
