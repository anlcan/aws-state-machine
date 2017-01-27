package com.finleap.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.finleap.statemachine.states.ParallelState;

import java.io.IOException;

/**
 * Created by anlcan on 27/01/2017.
 */
public class ParallelSerializer extends JsonSerializer<ParallelState> {


    @Override
    public void serialize(ParallelState value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        gen.writeStartObject();
        gen.writeStringField("Type", value.getType().toString());
        gen.writeObjectField("Branches", value.branches);
        gen.writeStringField("Next", value.getNextStateName());

        gen.writeEndObject();
    }
}
