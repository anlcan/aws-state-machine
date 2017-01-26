package com.finleap;

import com.finleap.runtime.PrintExectutor;
import com.finleap.runtime.StateExecutor;
import com.finleap.runtime.TaskExecutorFactory;
import com.finleap.sm.InterpreterContext;
import com.finleap.sm.StateExecutionException;
import com.finleap.sm.StateMachine;
import com.finleap.sm.StateMachineContext;
import com.finleap.sm.states.Executable;
import com.finleap.sm.states.State;

/**
 * Created by anlcan on 26/01/2017.
 *
 * Interpreter that runs a given state machine against an input
 */
public class Interpreter {

    /**
     *
     */
    private final StateMachine stateMachine;

    /**
     *
     */
    public TaskExecutorFactory factory;

    public Interpreter(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    /**
     * convenience method to run a simple Interpreter
     * @param stateMachine state machine to run
     * @param inputJson input
     * @return output after execution
     */
    public static String run(StateMachine stateMachine, String inputJson){
        Interpreter internal = new Interpreter(stateMachine);
        internal.factory = new TaskExecutorFactory() {
            @Override
            public StateExecutor executor() {
                return new PrintExectutor();
            }
        };
        return internal.execute(inputJson);
    }

    public  String execute(String inputJson){
        InterpreterContext interpreterContext = new InterpreterContext(inputJson);

        State state = stateMachine.states.getState(stateMachine.startAt);

        do {
            step(state, interpreterContext);
            if (interpreterContext.finished()) return interpreterContext.getOutput();

            String nextStateName = state.nextStateName;
            state = stateMachine.getState(nextStateName);
            if (state == null) {
                throw new StateExecutionException("State not found: " + nextStateName);
            }


        } while (true);
    }

    /**
     *
     * @param state
     * @return
     */
    private void step(State state, StateMachineContext context){

        state.run(context);
        if (state instanceof Executable) {
            ((Executable) state).execute(context, factory.executor());
        }

        context.setFinished(state.end);
    }
}
