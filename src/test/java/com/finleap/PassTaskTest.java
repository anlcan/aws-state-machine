package com.finleap;

import com.finleap.sm.Context;
import com.finleap.sm.states.PassState;
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
        Context context = new Context(input);
        state.run(context);

        context.getInput();
    }
}
