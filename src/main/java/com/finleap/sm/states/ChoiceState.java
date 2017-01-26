package com.finleap.sm.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finleap.sm.StateMachineContext;
import com.finleap.sm.fields.ChoiceRule;

import java.util.List;

/**
 * Created by anlcan on 20/01/2017.
 *
 * http://docs.aws.amazon.com/step-functions/latest/dg/awl-ref-states-choice.html
 */
public class ChoiceState extends State{

    @JsonProperty("Choices")
    public List<ChoiceRule> choices;

    @JsonProperty("Default")
    public String defaultStateName;

    public ChoiceState() {
        this.type = StateType.CHOICE;
    }

    /**
     * evaluates {@link #choices} and sets {@link #nextStateName}
     */
    @Override
    public void run(StateMachineContext context) {
        nextStateName = evaluateChoices(context);
    }

    private String evaluateChoices(StateMachineContext context){
        return choices.stream()
                .filter(choice -> choice.evaluate(context))
                .map(ChoiceRule::getNextStateName)
                .findFirst()
                .orElse(defaultStateName) ;

    }

}
