package com.finleap;

import com.finleap.statemachine.StateMachine;

import java.io.File;

/**
 * Created by anlcan on 26/01/2017.
 *
 * runs a state machine against input
 */
public class Main {

    public static void main(String[] params){
        try {
            File stateMachineFile = new File(Main.class.getClassLoader().getResource(params[0]).getFile());
            StateMachine stateMachine = Parser.parseFile(stateMachineFile);
            String input = params[1];

            Interpreter.run(stateMachine, input);

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
