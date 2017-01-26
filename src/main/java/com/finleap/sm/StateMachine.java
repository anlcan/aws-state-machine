package com.finleap.sm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.sm.fields.States;

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
    public StateMachine(@JsonProperty("Comment") String comment,@JsonProperty("StartAt") String startAt){
        this.comment = comment;
        this.startAt = startAt;
    }

    public String getComment() {
        return comment;
    }


}