package com.finleap;

import com.finleap.statemachine.InterpreterContext;
import com.finleap.statemachine.states.PassState;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anlcan on 26/01/2017.
 */
public class PassTaskTest {

    private PassState state;

    @Before
    public void setup() {
        state = new PassState();
    }


    @Test
    public void coords() throws Exception {
        String input = "{georefOf: Home}";
        InterpreterContext interpreterContext = new InterpreterContext(input);
        state.run(interpreterContext);

        interpreterContext.getInput();
    }
}
