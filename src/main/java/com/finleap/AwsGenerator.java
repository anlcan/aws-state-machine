package com.finleap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finleap.statemachine.StateMachine;

/**
 * Created by anlcan on 27/01/2017.
 *
 * Genearates an Aws-step-function compatible representation
 */
public class AwsGenerator implements Generator {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    static  {
        MAPPER.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public AwsGenerator() {
    }

    @Override
    public String generate(StateMachine stateMachine) throws JsonProcessingException {

        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(stateMachine);
    }
}
