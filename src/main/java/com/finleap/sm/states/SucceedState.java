package com.finleap.sm.states;

import com.finleap.sm.Context;

/**
 * Created by anlcan on 25/01/2017.
 *
 * http://docs.aws.amazon.com/step-functions/latest/dg/awl-ref-states-succeed.html
 */
public class SucceedState extends State {

    public SucceedState() {
        this.type = StateType.SUCCEED;
    }

    @Override
    public void run(Context context) {

    }


}
