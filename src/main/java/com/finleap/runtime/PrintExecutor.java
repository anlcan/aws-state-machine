package com.finleap.runtime;

import com.finleap.statemachine.StateMachineContext;
import com.finleap.statemachine.states.State;

/**
 * Created by anlcan on 26/01/2017.
 *
 * a naive implementation fo StateExecutor mostly for debugging
 */
public class PrintExecutor implements StateExecutor {

    @Override
    public String execute(State t, StateMachineContext context) {
        System.out.println(t.resource +"("+ context.getOutput() + ")");
        return context.getOutput();
    }
}
