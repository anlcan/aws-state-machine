package com.finleap.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.finleap.sm.fields.ChoiceOperator;
import com.finleap.sm.fields.ChoiceRule;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by anlcan on 25/01/2017.
 */
public final class ChoiceDeserializer extends JsonDeserializer{

    private static final List<String> OPS =  Arrays.stream(ChoiceOperator.values())
            .map(Enum::toString)
            .collect(Collectors.toList());

    @Override
    public ChoiceRule deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        ChoiceRule choiceRule = new ChoiceRule(node.get("Next").asText());
        choiceRule.variable = node.get("Variable").asText();

        Iterable<Map.Entry<String, JsonNode>> iterable = node::fields;
        Map.Entry<String, JsonNode> op =  StreamSupport.stream(iterable.spliterator(), false)
                .filter(entry->OPS.contains(entry.getKey()))
                .findFirst()
                .get();

        ChoiceOperator tank = ChoiceOperator.valueOf(op.getKey());
        choiceRule.setOption(tank, op.getValue().textValue());
        switch (tank.getType()){

            case BOOLEAN:
                break;
            case STRING:
                choiceRule.setOption(tank, op.getValue().asText());
                break;
            case NUMERIC:
                choiceRule.setOption(tank, op.getValue().decimalValue());
                break;
            case TIMESTAMP:
                break;
            case MULTI:
                break;
        }


        return choiceRule;
    }



}
