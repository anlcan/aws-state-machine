package com.finleap.sm.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.sm.fields.Catcher;
import com.finleap.sm.fields.Retrier;

import java.util.List;

/**
 * Created by anlcan on 26/01/2017.
 */
public abstract class ErrorHandlingState extends State {

    @JsonProperty("Retry")
    public List<Retrier> retriers;

    @JsonProperty("Catch")
    public List<Catcher> catchers;

}
