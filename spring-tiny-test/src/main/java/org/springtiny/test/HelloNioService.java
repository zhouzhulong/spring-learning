package org.springtiny.test;

/**
 * @author zlzhou
 */
public class HelloNioService {

    private HelloWorldService helloWorldService;

    public void hello() {
        helloWorldService.hello();
        System.out.println("hello NIO");
    }
}
