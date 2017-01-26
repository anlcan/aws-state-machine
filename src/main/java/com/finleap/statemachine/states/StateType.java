package com.finleap.statemachine.states;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by anlcan on 20/01/2017.
 */
public enum StateType {

    @JsonProperty("Pass")
    PASS,
    @JsonProperty("Task")
    TASK,
    @JsonProperty("Choice")
    CHOICE,
    @JsonProperty("Wait")
    WAIT,
    @JsonProperty("Succeed")
    SUCCEED,
    @JsonProperty("Fail")
    FAIL,
    @JsonProperty("Parallel")
    PARALLEL;
}
