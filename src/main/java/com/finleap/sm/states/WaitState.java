package com.finleap.sm.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.sm.StateExecutionException;
import com.finleap.sm.StateMachineContext;
import com.jayway.jsonpath.JsonPath;

import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Created by anlcan on 25/01/2017.
 */
public class WaitState extends State {

    public WaitState() {
        this.type = StateType.WAIT;
    }

    /**
     * A time, in seconds, to wait before beginning the state specified in the Next field.
     */
    @JsonProperty("Seconds")
    public int seconds = -1;

    @JsonProperty("Timestamp")
    public String timeStamp = null;

    @JsonProperty("SecondsPath")
    public String secondsPath = null;

    @JsonProperty("TimestampPath")
    public String timestampPath;

    @Override
    public void run(StateMachineContext context) {
        try {
            if (seconds > 0){
                sleepSeconds(seconds);
            }  else if (secondsPath != null){
                int sec = JsonPath.parse(context.getOutput()).read(secondsPath);
                sleepSeconds(sec);
            } else if ( timeStamp != null) {
                untilItSleeps(this.timeStamp);
            }else if ( timestampPath!= null) {
                String aTimeStamp = JsonPath.parse(context.getOutput()).read(timestampPath);
                untilItSleeps(aTimeStamp);
            }
        } catch (InterruptedException e) {
             throw new StateExecutionException(e);
        }
    }

    /**
     *
     * @param sec
     * @throws InterruptedException
     */
    private void sleepSeconds(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }

    /**
     *
     * @param aTimeStamp
     * @throws InterruptedException
     */
    private void untilItSleeps(String aTimeStamp) throws InterruptedException {
        ZonedDateTime future = ZonedDateTime.parse(aTimeStamp);
        ZonedDateTime now = ZonedDateTime.now();
        long milliseconds = Duration.between(now, future).toMillis();
        Thread.sleep(milliseconds);
    }
}
