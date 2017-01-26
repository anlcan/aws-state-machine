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

    private WaitState state;
    private static final int WAIT_SECONDS = 2;
    @Before
    public void setup() {
        state = new WaitState();
    }

    @Test
    public void secondsTest() throws Exception {

        state.seconds = 2;
        long start = System.currentTimeMillis();
        state.run(new Context(""));
        long end = System.currentTimeMillis();
        assertTrue((end-start > state.seconds * 1000));

    }


    @Test()
    public void timeStampTest() throws Exception {

        ZonedDateTime time =  ZonedDateTime.now().plusSeconds(WAIT_SECONDS);
        state.timeStamp = time.format(DateTimeFormatter.ISO_INSTANT);
        long start = System.currentTimeMillis();
        state.run(new Context(""));
        long end = System.currentTimeMillis();
        long execution = end-start + 100; //100 is the execution diff
        assertTrue((execution > WAIT_SECONDS * 1000));

    }


    @Test
    public void secondsPath() throws Exception {
        String input ="{seconds:2}";
        state.secondsPath = "$.seconds";
        long start = System.currentTimeMillis();
        state.run(new Context(input));
        long end = System.currentTimeMillis();
        assertTrue((end-start > 2 * 1000));

    }

    @Test
    public void timestampPath() throws Exception {
        ZonedDateTime time =  ZonedDateTime.now().plusSeconds(WAIT_SECONDS);
        String timestamp = time.format(DateTimeFormatter.ISO_INSTANT);
        String input = "{timestamp:" +  timestamp +"}";
        state.timestampPath = "$.timestamp";
        long start = System.currentTimeMillis();
        state.run(new Context(input));
        long end = System.currentTimeMillis();
        long execution = end-start + 100; //100 is the execution diff
        assertTrue((execution > WAIT_SECONDS * 1000));

    }
}
