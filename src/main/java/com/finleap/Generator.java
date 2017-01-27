package com.finleap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.finleap.statemachine.StateMachine;

/**
 * Created by anlcan on 27/01/2017.
 *
 * Generates  json representaton of a State Machine
 */
public interface Generator {

    public String generate(StateMachine stateMachine) throws JsonProcessingException;

}
