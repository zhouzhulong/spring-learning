package org.springtiny.test;

/**
 * @author zlzhou
 */
public class HelloWorldService {

    private HelloNioService helloNioService;

    private String text;

    public void hello() {
        System.out.println("hello " + text);
    }
}
