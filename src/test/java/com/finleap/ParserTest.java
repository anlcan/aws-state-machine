package com.finleap;

import com.finleap.deser.Parser;
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
        File content =  new File(getClass().getClassLoader().getResource("hello.json").getFile());
        StateMachine stateMachine = Parser.parseFile(content);
        System.out.println( stateMachine.getComment());
        stateMachine.run("{foo:2}");
    }

    @Test
    public void parse() throws IOException {
        File content =  new File(getClass().getClassLoader().getResource("choice_state_1.json").getFile());
        StateMachine stateMachine = Parser.parseFile(content);
        System.out.println( stateMachine.getComment());

        stateMachine.run("{foo:2}");
    }
}
