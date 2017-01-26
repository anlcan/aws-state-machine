package com.finleap.sm.fields;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by anlcan on 26/01/2017.
 *
 * Task and Parallel states may have a field named Retry, whose value must be an array of
 * objects, called Retriers. An individual Retrier represents a certain number of retries, usually at increasing time intervals.
 */
public class Retrier {

    /**
     * A non-empty array of Strings that match Error Names.
     * When a state reports an error, Step Functions scans through the Retriers and,
     * when the Error Name appears in this array,
     * it implements the retry policy described in this Retrier. [Required]
     */
    @JsonProperty("ErrorEquals")
    public List<String> errorEquals;

    /**
     * An integer that represents the number of seconds
     * before the first retry attempt (default 1). [Optional]
     */
    @JsonProperty("IntervalSeconds")
    public int intervalSeconds;

    /**
     * A positive integer, representing the maximum number of retry attempts (default 3).
     * If the error recurs more times than specified, retries cease and normal error handling resumes. A value of 0 is permitted and indicates that the error or errors should never be retried. [Optional]
     */
    @JsonProperty("MaxAttempts")
    public int maxAttempts = 3;

    /**
     * A number that is the multiplier by which the retry interval increases on each attempt
     * (default 2.0). [Optional]
     */
    @JsonProperty("BackoffRate")
    public int backoffRate = 2;
}
