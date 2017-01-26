package com.finleap.runtime;

import com.finleap.statemachine.StateMachineContext;
import com.finleap.statemachine.states.State;

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
