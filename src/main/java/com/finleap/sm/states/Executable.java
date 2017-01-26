package com.finleap.sm.states;

import com.finleap.runtime.StateExecutor;
import com.finleap.sm.StateMachineContext;


/**
 * Created by anlcan on 26/01/2017.
 */
public interface Executable {
    String execute(StateMachineContext context, StateExecutor executor);
}
