package com.finleap.statemachine.fields;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.finleap.deser.ChoiceDeserializer;
import com.finleap.ser.ChoiceRuleSerializer;
import com.finleap.statemachine.StateMachineContext;
import com.jayway.jsonpath.JsonPath;

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
@JsonSerialize(using = ChoiceRuleSerializer.class)
public class ChoiceRule {

    @JsonProperty("Variable")
    public String variable;

    private ChoiceOperator operator;

    @JsonProperty("Next")
    public String nextStateName;

    /**
     * the "fixed" value that operator evaluates the variable against
     */
    private Object value;

    public ChoiceRule() {
    }

    public boolean evaluate(StateMachineContext context){
        Object param = null;
        if ( variable != null)
            param = JsonPath.parse(context.getOutput()).read(variable);
        return operator.eval(value, param, context);
    }

    public void setOption(ChoiceOperator option, Object value) {
        this.operator = option;
        this.value = value;
    }

    public String getNextStateName() {
        return nextStateName;
    }

    public ChoiceOperator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }

    public String getVariable() {
        return variable;
    }
}
