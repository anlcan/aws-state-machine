package com.finleap.statemachine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.statemachine.fields.States;
import com.finleap.statemachine.states.State;

/**
 * Created by anlcan on 20/01/2017.
 */
public final class StateMachine {

    @JsonProperty("Comment")
    private   String comment;

    @JsonProperty("StartAt")
    public String startAt;

    @JsonProperty("Version")
    private String version = "1.0";

    @JsonProperty("States")
    public States states;

    @JsonCreator
    public StateMachine(@JsonProperty("StartAt") String startAt,@JsonProperty("Comment") String comment){
        this.comment = comment;
        this.startAt = startAt;
        states = new States();
    }

    public String getComment() {
        return comment;
    }

    public State getState(String stateName){
        return states.getState(stateName);
    }
}