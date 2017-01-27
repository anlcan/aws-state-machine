package com.finleap.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.finleap.statemachine.fields.States;

import java.io.IOException;

/**
 * Created by anlcan on 27/01/2017.
 */
public class StatesSerializer  extends JsonSerializer<States>{

    @Override
    public void serialize(States value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        gen.writeObject(value.getStates());

    }

}
