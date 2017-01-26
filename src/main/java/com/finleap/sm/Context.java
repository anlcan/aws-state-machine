package com.finleap.sm;

/**
 * Created by anlcan on 25/01/2017.
 *
 * State execution context
 */
public class Context {

    /**
     *
     */
    private final String input;

    private  String output;

    public Context(String inputJson) {
        this.input = inputJson;
    }

    public String getOutput() {
        return output;
    }

    public String getInput() {
        return input;
    }
}
