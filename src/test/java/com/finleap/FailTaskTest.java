package com.finleap;

import com.finleap.sm.Context;
import com.finleap.sm.StateMachineError;
import com.finleap.sm.states.FailState;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by anlcan on 26/01/2017.
 */
public class FailTaskTest {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void name() throws Exception {

        FailState f = new FailState();
        thrown.expect(StateMachineError.class);
        f.run(new Context("{}"));
        // not reached

    }
}
