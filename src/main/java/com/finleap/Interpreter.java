package com.finleap;

import com.finleap.runtime.PrintExecutor;
import com.finleap.runtime.StateExecutor;
import com.finleap.runtime.TaskExecutorFactory;
import com.finleap.statemachine.InterpreterContext;
import com.finleap.statemachine.error.StateExecutionException;
import com.finleap.statemachine.StateMachine;
import com.finleap.statemachine.StateMachineContext;
import com.finleap.statemachine.states.Executable;
import com.finleap.statemachine.states.State;

/**
 * Created by anlcan on 26/01/2017.
 *
 * Interpreter that runs a given state machine against a context.
 * execute each step, update the state and finish the state machine.
 */
public class Interpreter {

    /**
     * state machine
     * one interpreter with a state machine, can run multiple input
     */
    private final StateMachine stateMachine;

    /**
     * an executor
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
                return new PrintExecutor();
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
        context.setCurrentStateName(state.name);
        state.run(context);
        if (state instanceof Executable) {
            ((Executable) state).execute(context, factory.executor());
        }

        context.setFinished(state.end);
    }
}
