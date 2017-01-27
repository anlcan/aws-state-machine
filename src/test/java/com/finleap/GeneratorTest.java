package com.finleap;

import com.finleap.statemachine.StateMachine;
import com.finleap.statemachine.states.FailState;
import com.finleap.statemachine.states.TaskState;
import org.junit.Test;

/**
 * Created by anlcan on 27/01/2017.
 */
public class GeneratorTest {

    private Generator gen = new AwsGenerator();

    @Test
    public void name() throws Exception {


        TaskState ts1 = new TaskState();
        ts1.name = "HelloWorld";
        ts1.resource = "arn::lambda::HelloWorld";
        ts1.end = false;
        ts1.nextStateName = "Goodbye";

        TaskState ts2 = new TaskState();
        ts2.name = "Goodbye";
        ts2.resource = "arn::lambda::GoodbyeWorld";
        ts2.end = true;

        FailState fs = new FailState();
        fs.name ="FailState";
        fs.cause = "Syntax Error";
        fs.error = "States.ALL";

        StateMachine sm = new StateMachine(ts1.name, "Generation test");

        sm.states.addState(ts1);
        sm.states.addState(ts2);
        sm.states.addState(fs);
        System.out.println(gen.generate(sm));
    }
}
