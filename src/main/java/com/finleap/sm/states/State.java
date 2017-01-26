package com.finleap.sm.states;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.sm.Context;

/**
 * Created by anlcan on 20/01/2017.
 * http://docs.aws.amazon.com/step-functions/latest/dg/awl-ref-states.html#awl-ref-common-fields
 *
 */
public abstract class State {

    /**
     * The state's type. Can be any of the values listed in State Types. [Required]
     */
    @JsonProperty("Type")
    protected StateType type;


    @JsonProperty("Resource")
    public String resource;


    /**
     * The name of the next state that will be run when the current state finishes.
     * Some state types, such as ChoiceRule, allow multiple transition states.
     */
    @JsonProperty("Next")
    public String nextStateName;

    /**
     * Designates this state as a terminal state (it ends the execution) if set to true.
     * There can be any number of terminal states per state machine. Only one of Next or End can be used in a state. Some state types, such as ChoiceRule, do not support or use the End field.
     */
    @JsonProperty("End")
    public boolean end = false;

    /**
     * Holds a human-readable description of the state. [Optional]
     */
    @JsonProperty("Comment")
    String comment;


    /**
     * A Path that selects a portion of the state's input to be passed to the state's task for processing. If omitted,
     * it has the value $ which designates the entire input. (See Filters). [Optional]
     */
    @JsonProperty("InputPath")
    public String inputPath = null;

    /**
     * A Path that selects a portion of the state's input to be passed to the state's output. If omitted,
     * it has the value $ which designates the entire input. (See Filters. ) [Optional]
     */
    @JsonProperty("OutputPath")
    public String outputPath;

    /**
     *
     */
    @JsonProperty("ResultPath")
    public String resultPath;


    public String name;


    @JsonCreator
    public State(@JsonProperty("Type") StateType type) {
        this.type = type;
    }

     State(){
        System.out.println();
    }


    /**
     *
     */
    public abstract void run(Context context);

    public String getNextStateName(){
        return nextStateName;
    }

    @Override
    public String toString() {
        return "State{" +
                type + ":" + name +
                '}';
    }
}
