package org.springtiny.test;

/**
 * @author zlzhou
 */
public class HelloWorldService implements HelloWorld{

    private HelloNio helloNioService;

    private String text;

    public void hello() {
        helloNioService.hello();
        System.out.println("hello " + text);
    }

    public HelloNio getHelloNioService() {
        return helloNioService;
    }

    public void setHelloNioService(HelloNioService helloNioService) {
        this.helloNioService = helloNioService;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
