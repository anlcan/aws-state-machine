package com.finleap.sm.fields;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.finleap.deser.ChoiceDeserializer;
import com.finleap.sm.Context;
import com.jayway.jsonpath.JsonPath;

import java.util.List;

/**
 * Created by anlcan on 20/01/2017.
 *
 *       "Choices": [
 {
 "Variable": "$.foo",
 "NumericEquals": 1,
 "Next": "FirstMatchState"
 },
 {
 "Variable": "$.foo",
 "NumericEquals": 2,
 "Next": "SecondMatchState"
 }
 ],
 "Default": "DefaultState"
 },
 */
@JsonDeserialize(using = ChoiceDeserializer.class)
public class ChoiceRule {

    @JsonProperty("Variable")
    public String variable;

    private ChoiceOperator option;

    @JsonProperty("Next")
    public String nextStateName;

    private Object value;

    // HERE BE DRAGONS
    public List<ChoiceRule> multiRuleValue;


    @JsonCreator
    public ChoiceRule(@JsonProperty("Next")String nextStateName) {
        this.nextStateName = nextStateName;
    }

    public boolean evaluate(Context context){
        Object param = JsonPath.parse(context.getInput()).read(variable);
        return option.eval(value, param);
    }

    public void setOption(ChoiceOperator option, Object value) {
        this.option = option;
        this.value = value;
    }

    public String getNextStateName() {
        return nextStateName;
    }
}
