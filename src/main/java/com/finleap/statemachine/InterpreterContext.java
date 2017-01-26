package com.finleap.statemachine;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anlcan on 25/01/2017.
 *
 * State execution context
 */
public class InterpreterContext implements StateMachineContext{

    /**
     *
     */
    private final String input;

    /**
     *
     */
    private String output;

    /**
     * keeping track of the changes on input
     */
    private final Map<String, String> history = new HashMap<>();

    /**
     *
     */
    private boolean isFinished;

    /**
     * current state name
     * on each step of
     */
    private String stateName;

    public InterpreterContext(String inputJson) {
        this.input = inputJson;
        this.output = inputJson;
    }

    public void updateInput(String taskOuput){
        //history.put(currentStateName, input);
        this.output = taskOuput;
    }

    @Override
    public void setCurrentStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getOutput() {
        return output;
    }

    public String getInput() {
        return input;
    }



    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public boolean finished() {
        return isFinished;
    }
}
