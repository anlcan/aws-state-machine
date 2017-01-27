package com.finleap.statemachine.fields;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.finleap.deser.StatesDeserializer;
import com.finleap.ser.StatesSerializer;
import com.finleap.statemachine.StateMachine;
import com.finleap.statemachine.states.State;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anlcan on 20/01/2017.
 *
 * states of the {@link StateMachine}
 */
@JsonDeserialize(using = StatesDeserializer.class)
@JsonSerialize(using = StatesSerializer.class)
public class States {

    private  final Map<String, State> states = new HashMap<>();

    public void addState(State state){
        states.put(state.name, state);
    }

    public State getState(String name){
        return states.get(name);
    }

    public Map<String, State> getStates() {
        return new HashMap<>(states);
    }
}
