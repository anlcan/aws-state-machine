package com.finleap.sm.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.sm.Context;

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
    public String secondsPath;

    @JsonProperty("TimestampPath")
    public String timestampPath;

    @Override
    public void run(Context context) {
        try {
            if (seconds > 0){
                Thread.sleep(seconds * 1000);
            }else if ( timeStamp != null) {
                ZonedDateTime future = ZonedDateTime.parse(timeStamp);
                ZonedDateTime now = ZonedDateTime.now();
                long milliseconds = Duration.between(now, future).toMillis();
                Thread.sleep(milliseconds);
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); // FIXME
        }
    }
}
