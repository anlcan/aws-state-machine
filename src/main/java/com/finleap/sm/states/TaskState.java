package com.finleap.sm.states;

import com.finleap.sm.Context;

/**
 * Created by anlcan on 20/01/2017.
 *
 * http://docs.aws.amazon.com/step-functions/latest/dg/awl-ref-states-task.html
 */
public class TaskState extends State {

    public TaskState(){
        this.type = StateType.TASK;
    }

    @Override
    public void run(Context context) {
        System.out.println("running  " + resource);
    }

}
