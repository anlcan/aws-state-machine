package com.finleap.statemachine;

/**
 * Created by anlcan on 26/01/2017.
 *
 * A context to pass around for the state machine
 */
public interface StateMachineContext {

    void setCurrentStateName(String stateName);

    String getOutput();

    void setFinished(boolean isFinished);

    boolean finished();
}
