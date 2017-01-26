package com.finleap;

import com.finleap.sm.Context;
import com.finleap.sm.states.WaitState;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;

/**
 * Created by anlcan on 26/01/2017.
 */
public class WaitTaskTest {

    WaitState state;

    @Before
    public void setup() {
        state = new WaitState();
    }

    @Test
    public void secondsTest() throws Exception {

        state.seconds = 3;
        long start = System.currentTimeMillis();
        state.run(new Context(""));
        long end = System.currentTimeMillis();
        assertTrue((end-start > state.seconds * 1000));

    }


    @Test
    public void timeStampTest() throws Exception {
        int secondToWait = 2;
        ZonedDateTime time =  ZonedDateTime.now().plusSeconds(secondToWait);
        state.timeStamp = time.format(DateTimeFormatter.ISO_INSTANT);
        long start = System.currentTimeMillis();
        state.run(new Context(""));
        long end = System.currentTimeMillis();
        assertTrue((end-start > secondToWait * 1000));

    }
}
