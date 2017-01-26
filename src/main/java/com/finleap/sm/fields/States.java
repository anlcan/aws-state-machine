package com.finleap.sm.fields;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.finleap.deser.StatesDeserializer;
import com.finleap.sm.StateMachine;
import com.finleap.sm.states.State;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anlcan on 20/01/2017.
 *
 * states of the {@link StateMachine}
 */
@JsonDeserialize(using = StatesDeserializer.class)
public class States {

    private  final Map<String, State> states = new HashMap<>();

    public void addState(State state){
        states.put(state.name, state);
    }

    public State getState(String name){
        return states.get(name);
    }

}
