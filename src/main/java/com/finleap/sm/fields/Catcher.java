package com.finleap.sm.fields;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by anlcan on 26/01/2017.
 *
 * Task and Parallel states may have a field named Catch,
 * whose value must be an array of objects, called Catchers.
 */
public class Catcher {

    /**
     * A non-empty array of Strings that match Error Names, specified exactly as with the
     * Retrier field of the same name. [Required]
     */
    @JsonProperty("ErrorEquals")
    public List<String> errorEquals;

    /**
     * A string which must exactly match one of the state machine's state names. [Required]
     */
    @JsonProperty("Next")
    public String nextStateName;

    /**
     * A path which determines what is sent as input to the state specified by the Next field. [Optional]
     */
    @JsonProperty("ResultPath")
    public String resultPath;


}
