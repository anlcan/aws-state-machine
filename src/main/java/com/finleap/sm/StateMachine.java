package com.finleap.sm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.sm.fields.States;
import com.finleap.sm.states.State;

/**
 * Created by anlcan on 20/01/2017.
 */
public final class StateMachine {

    @JsonProperty("Comment")
    private   String comment;

    @JsonProperty("StartAt")
    private String startAt;

    @JsonProperty("Version")
    private String version = "1.0";

    //public final List<State> states = new ArrayList<>();
    @JsonProperty("States")
    public States states;

    @JsonCreator
    public StateMachine(@JsonProperty("Comment") String comment,@JsonProperty("StartAt") String startAt){
        this.comment = comment;
        this.startAt = startAt;
    }

    public String getComment() {
        return comment;
    }

    public String run(String inputJson){
        Context context = new Context(inputJson);

        State state = states.getState(startAt);

        while (!state.end) {
            state.run(context);
            State nextState = states.getState(state.nextStateName);
            if (nextState == null) {
                System.out.println(state.nextStateName);
                throw new StateExecutionException("broken state");
            } else {
                state = nextState;
            }

        }

        return context.getOutput();
    }
}