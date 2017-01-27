package com.finleap;

import com.finleap.statemachine.StateMachine;
import com.finleap.statemachine.fields.ChoiceOperator;
import com.finleap.statemachine.fields.ChoiceRule;
import com.finleap.statemachine.states.*;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by anlcan on 27/01/2017.
 */
public class GeneratorTest {

    private Generator gen = new AwsGenerator();

    private TaskState builder(String name, String next){
        TaskState taskState = new TaskState();
        taskState.name = name;
        taskState.resource = "arn::lambda::" + name;
        taskState.end = false;
        taskState.nextStateName = next;

        return taskState;
    }


    private TaskState parallelTask(String name){
        TaskState taskState = new TaskState();
        taskState.name = name;
        taskState.resource = "arn::parallel::lambda::" + name;
        taskState.end = true;

        return taskState;
    }

    private StateMachine branch(String name){
        TaskState p1 = parallelTask("Parallel Task " + name);
        StateMachine inner1 = new StateMachine(name, "Parallel State Machine " + name);
        inner1.states.addState(p1);

        return inner1;
    }

    @Test
    public void name() throws Exception {

        TaskState ts1 = new TaskState();
        ts1.name = "HelloWorld";
        ts1.resource = "arn::lambda::HelloWorld";
        ts1.end = false;
        ts1.nextStateName = "ChoiceState";

        TaskState ts2 = new TaskState();
        ts2.name = "Goodbye";
        ts2.resource = "arn::lambda::GoodbyeWorld";
        ts2.end = true;

        SucceedState ss = new SucceedState();
        ss.name = "Success";

        FailState fs = new FailState();
        fs.name ="FailState";
        fs.cause = "Syntax Error";
        fs.error = "States.ALL";

        PassState ps = new PassState();
        ps.name ="PassState";
        ps.result = "{'bar':3}";
        ps.resultPath = "$.coord";
        ps.nextStateName = "FailState";

        WaitState ws = new WaitState();
        ws.name = "WaitState";
        ws.seconds = 10;
        ws.nextStateName = "Goodbye";

        ParallelState pp = new ParallelState();
        pp.name = "Parallel";
        pp.branches.add(branch("task1"));
        pp.branches.add(branch("task2"));
        pp.nextStateName = "WaitState";

        ChoiceRule cr = new ChoiceRule();
        cr.setOption(ChoiceOperator.NumericEquals, 4);
        cr.variable = "$.value";
        cr.nextStateName = "Parallel";

        ChoiceRule cr2 = new ChoiceRule();
        cr2.setOption(ChoiceOperator.StringEquals, "four");
        cr2.variable = "$.stringValue";

        ChoiceRule notChoice = new ChoiceRule();
        notChoice.setOption(ChoiceOperator.Not, Arrays.asList(cr2));
        notChoice.nextStateName = "PassState";


        ChoiceRule crAnd1 = new ChoiceRule();
        crAnd1.setOption(ChoiceOperator.NumericGreaterThanEquals, 4);
        crAnd1.variable = "$.stringValue";
        ChoiceRule crAnd2 = new ChoiceRule();
        crAnd2.setOption(ChoiceOperator.NumericLessThan, 10);
        crAnd2.variable = "$.stringValue";

        ChoiceRule andChoice = new ChoiceRule();
        andChoice.setOption(ChoiceOperator.And, Arrays.asList(crAnd1, crAnd2));
        andChoice.nextStateName = "Success";

        ChoiceState  cs = new ChoiceState();
        cs.name = "ChoiceState";
        cs.choices.add(cr);
        cs.choices.add(notChoice);
        cs.choices.add(andChoice);

        /* */
        StateMachine sm = new StateMachine(ts1.name, "Generation test");

        sm.states.addState(ts1);
        sm.states.addState(ts2);
        sm.states.addState(fs);
        sm.states.addState(ps);
        sm.states.addState(ss);
        sm.states.addState(ws);
        sm.states.addState(pp);
        sm.states.addState(cs);

        System.out.println(gen.generate(sm));
    }
}
