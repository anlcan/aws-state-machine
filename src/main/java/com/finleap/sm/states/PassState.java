package com.finleap.sm.states;

import com.finleap.sm.Context;

/**
 * Created by anlcan on 20/01/2017.
 *
 * http://docs.aws.amazon.com/step-functions/latest/dg/awl-ref-states-pass.html
 */
public class PassState extends State {

    public PassState() {
        this.type = StateType.PASS;
    }

    @Override
    public void run(Context context) {

    }
}
