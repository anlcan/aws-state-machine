package com.finleap;

import com.finleap.sm.StateMachine;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by anlcan on 20/01/2017.
 */
public class ParserTest {

    @Test
    public void parseHello() throws IOException {
        File content =  new File(getClass().getClassLoader().getResource("hello_world.json").getFile());
        StateMachine stateMachine = Parser.parseFile(content);
        System.out.println( stateMachine.getComment());
        Interpreter.run(stateMachine,"{foo:2}");
    }

    @Test
    public void parseChoice1() throws IOException {
        File content =  new File(getClass().getClassLoader().getResource("choice_state_1.json").getFile());
        StateMachine stateMachine = Parser.parseFile(content);
        System.out.println( stateMachine.getComment());

        Interpreter.run(stateMachine,"{foo:2}");

    }

    @Test
    public void parseChoice2() throws IOException {
        File content =  new File(getClass().getClassLoader().getResource("choice_state_2.json").getFile());
        StateMachine stateMachine = Parser.parseFile(content);
        System.out.println( stateMachine.getComment());

        Interpreter.run(stateMachine,"{\n" +
                "  \"type\": \"Private\",\n" +
                "  \"value\": 22\n" +
                "}");

    }

    @Test
    public void parseParallel() throws IOException {
        File content =  new File(getClass().getClassLoader().getResource("parallel_state_1.json").getFile());
        StateMachine stateMachine = Parser.parseFile(content);
        System.out.println( stateMachine.getComment());

        Interpreter.run(stateMachine, "[3, 2]");

    }
}
