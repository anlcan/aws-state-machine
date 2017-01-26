package com.finleap.statemachine.states;

import com.finleap.runtime.StateExecutor;
import com.finleap.statemachine.StateMachineContext;


/**
 * Created by anlcan on 26/01/2017.
 */
public interface Executable {
    String execute(StateMachineContext context, StateExecutor executor);
}
