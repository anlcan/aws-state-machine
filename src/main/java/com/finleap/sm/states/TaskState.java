package com.finleap.sm.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.sm.Context;

/**
 * Created by anlcan on 20/01/2017.
 *
 * http://docs.aws.amazon.com/step-functions/latest/dg/awl-ref-states-task.html
 *
 * represents a single unit of work performed by a state machine.
 */
public class TaskState extends ErrorHandlingState {
    /**
     * If the task runs longer than the specified seconds, then this state fails with a States.Timeout Error Name.
     * Must be a positive, non-zero integer. If not provided, the default value is 99999999. [Optional]
     */
    @JsonProperty("TimeoutSeconds")
    public int timeoutSeconds = 99999999;

    /**
     * If more time than the specified seconds elapses between heartbeats from the task, then this state fails with an States.Timeout Error Name.
     * Must be a positive, non-zero integer less than the number of seconds specified in the TimeoutSeconds field. If not provided, the default value is 99999999. [Optional]
     */
    @JsonProperty("HeartbeatSeconds")
    public int heartbeatSeconds = 99999999;

    public TaskState(){
        this.type = StateType.TASK;
    }

    @Override
    public void run(Context context) {

    }

}
