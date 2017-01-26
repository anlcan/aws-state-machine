package com.finleap.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.sm.fields.States;
import com.finleap.sm.states.*;

import java.io.IOException;
import java.util.Map;
import java.util.stream.StreamSupport;

/**
 * Created by anlcan on 20/01/2017.
 */
public final class StatesDeserializer extends JsonDeserializer<States> {

    private static final ObjectMapper STATE_MAPPER = new ObjectMapper();


    @Override
    public States deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        States result = new States();
        Iterable<Map.Entry<String, JsonNode>> iterable = node::fields;
        StreamSupport.stream(iterable.spliterator(), true)
                .map(entry -> {

                    Class<? extends State> stateClass;
                    switch (entry.getValue().get("Type").asText()) {
                        case "Choice":
                            stateClass = ChoiceState.class;
                            break;
                        case "Fail" :
                            stateClass = FailState.class;
                            break;
                        case "Succeed":
                            stateClass = SucceedState.class;
                            break;
                        case "Parallel":
                            stateClass = ParallelState.class;
                            break;
                        case "Task":
                        default:
                            stateClass = TaskState.class;
                            break;
                    }
                try{

                    JsonNode stateNode = entry.getValue();
                    State state =  STATE_MAPPER.readValue(stateNode.traverse(jp.getCodec()), stateClass);
                    state.name  = entry.getKey();

                    return state;
                } catch (IOException e) {
            // FIXME: better exception handling
            e.printStackTrace();
            return null;
        }
                })
                .forEach(result::addState);

        return result;
    }

}
