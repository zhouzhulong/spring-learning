package org.springtiny.test;

/**
 * @author zlzhou
 */
public class HelloWorldService implements HelloWorld{

    private HelloNioService helloNioService;

    private String text;

    public void hello() {
        System.out.println("hello " + text);
    }
}
