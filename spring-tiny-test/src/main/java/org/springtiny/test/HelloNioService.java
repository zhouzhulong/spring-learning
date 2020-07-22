package org.springtiny.test;

/**
 * @author zlzhou
 */
public class HelloNioService implements HelloNio {

    private HelloWorld helloWorldService;

    public void hello() {
        System.out.println("hello NIO");
    }

    public HelloWorld getHelloWorldService() {
        return helloWorldService;
    }

    public void setHelloWorldService(HelloWorld helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
}
