package com.finleap.runtime;

import com.finleap.sm.StateMachineContext;
import com.finleap.sm.states.State;

/**
 * Created by anlcan on 26/01/2017.
 *
 * executes a Task
 */
public interface StateExecutor {

    /**
     *
     */
    String execute(State t,StateMachineContext context);

}
