package com.finleap.runtime;

import com.finleap.sm.StateMachineContext;
import com.finleap.sm.states.State;

/**
 * Created by anlcan on 26/01/2017.
 */
public class PrintExectutor implements StateExecutor {

    @Override
    public String execute(State t, StateMachineContext context) {
        System.out.println(t.resource +"("+ context.getOutput() + ")");
        return context.getOutput();
    }
}
