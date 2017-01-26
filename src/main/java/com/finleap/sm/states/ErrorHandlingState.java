package com.finleap.sm.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.sm.fields.Catcher;
import com.finleap.sm.fields.Retrier;

import java.util.List;

/**
 * Created by anlcan on 26/01/2017.
 *
 * http://docs.aws.amazon.com/step-functions/latest/dg/concepts-error-handling.html
 */
public abstract class ErrorHandlingState extends State implements Executable {
    /**
     * an array of objects, called Retriers.
     * An individual Retrier represents a certain number of retries, usually at increasing time intervals.
     */
    @JsonProperty("Retry")
    public List<Retrier> retriers;

    @JsonProperty("Catch")
    public List<Catcher> catchers;
}
