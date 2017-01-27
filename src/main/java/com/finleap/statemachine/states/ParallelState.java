package com.finleap.statemachine.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.finleap.Interpreter;
import com.finleap.runtime.StateExecutor;
import com.finleap.ser.ParallelSerializer;
import com.finleap.statemachine.StateMachine;
import com.finleap.statemachine.StateMachineContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by anlcan on 25/01/2017.
 */
@JsonSerialize(using = ParallelSerializer.class)
public class ParallelState extends ErrorHandlingState  {
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
        this.branches = new ArrayList<>();
    }

    /**
     * A Parallel state causes AWS Step Functions to execute each branch,
     * starting with the state named in that branch's StartAt field,
     * as concurrently as possible, and wait until all branches terminate
     * (reach a terminal state) before processing the Parallel state's Next field.
     * @param context
     */
    @Override
    public void run(StateMachineContext context) {

    }

    @Override
    public String execute(StateMachineContext context, StateExecutor executor) {
        branches.parallelStream()
                .map(sm -> Interpreter.run(sm, context.getOutput()))
                .collect(Collectors.toList());

        return null; //fixme
    }
}
