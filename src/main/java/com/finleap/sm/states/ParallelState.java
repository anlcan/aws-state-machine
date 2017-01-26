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
public class ParallelState extends ErrorHandlingState {
    /**
     * An array of objects that specify state machines to execute in parallel.
     * Each such state machine object must have fields named States and StartAt
     * whose meanings are exactly like those in the top level of a state machine.
     * [Required]
     */
    @JsonProperty("Branches")
    public List<StateMachine> branches;



    public ParallelState() {
        this.type = StateType.PARALLEL;
    }

    /**
     * A Parallel state causes AWS Step Functions to execute each branch,
     * starting with the state named in that branch's StartAt field,
     * as concurrently as possible, and wait until all branches terminate
     * (reach a terminal state) before processing the Parallel state's Next field.
     * @param context
     */
    @Override
    public void run(Context context) {

        branches.parallelStream()
                .map(sm -> Interpreter.run(sm, context.getInput()))
                .collect(Collectors.toList());
    }
}
