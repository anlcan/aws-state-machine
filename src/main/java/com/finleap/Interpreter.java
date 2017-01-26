package com.finleap;

import com.finleap.sm.Context;
import com.finleap.sm.StateExecutionException;
import com.finleap.sm.StateMachine;
import com.finleap.sm.states.State;

/**
 * Created by anlcan on 26/01/2017.
 *
 * Interpreter that runs a given statemachine against an input
 */
public class Interpreter {

    public static String run(StateMachine stateMachine, String inputJson){
        Context context = new Context(inputJson);

        State state = stateMachine.states.getState(stateMachine.startAt);

        do {
            state.run(context);
            if ( state.end)
                break;
            String nextStateName = state.nextStateName;
            state = stateMachine.states.getState(nextStateName);
            if (state == null) {
                throw new StateExecutionException("State not found: " + nextStateName);
            }

        } while (true);

        return context.getOutput();
    }
}
