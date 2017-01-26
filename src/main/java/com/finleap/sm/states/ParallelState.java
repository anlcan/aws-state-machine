package com.finleap.sm.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.Interpreter;
import com.finleap.sm.Context;
import com.finleap.sm.StateMachine;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by anlcan on 25/01/2017.
 */
public class ParallelState extends State {
    /**
     * An array of objects that specify state machines to execute in parallel.
     * Each such state machine object must have fields named States and StartAt
     * whose meanings are exactly like those in the top level of a state machine.
     * [Required]
     */
    @JsonProperty("Branches")
    public List<StateMachine> branches;

//    @JsonProperty("Retry")
//    public List<Retrier> retriers;
//
//    @JsonProperty("Catch")
//    public List<Catcher> catchers;

    public ParallelState() {
        this.type = StateType.PARALLEL;
    }

    @Override
    public void run(Context context) {

        branches.parallelStream()
                .map(sm -> Interpreter.run(sm, context.getInput()))
                .collect(Collectors.toList());
    }
}
