package com.finleap.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.finleap.statemachine.fields.ChoiceRule;

import java.io.IOException;

/**
 * Created by anlcan on 27/01/2017.
 */
public class ChoiceRuleSerializer extends JsonSerializer<ChoiceRule> {


    @Override
    public void serialize(ChoiceRule value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeObjectField(value.getOperator().toString(), value.getValue());

        switch (value.getOperator().getType()) {
            case BOOLEAN:
            case STRING:
            case NUMERIC:
            case TIMESTAMP:
                gen.writeObjectField("Variable", value.getVariable());
                break;
            case CHOICE:
            case MULTI:
            default:
                break;
        }
        gen.writeObjectField("Next", value.getNextStateName());
        gen.writeEndObject();
    }
}
