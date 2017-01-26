package com.finleap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.sm.StateMachine;

import java.io.File;
import java.io.IOException;

/**
 * Created by anlcan on 20/01/2017.
 */
public class Parser {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {

        MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);

    }

    public static StateMachine parseString(String json ) throws IOException {
        // convert JSON string to Map
        return MAPPER.readValue(json,StateMachine.class);

    }

    public static StateMachine parseFile(File file ) throws IOException {
        // convert JSON string to Map
        return MAPPER.readValue(file, StateMachine.class);

    }
}
