package org.springtiny.test.bean;

import org.springtiny.context.ApplicationContext;
import org.springtiny.context.ClassPathXmlApplicationContext;
import org.springtiny.test.HelloWorldService;

/**
 * @author zlzhou
 */
public class ApplicationContextTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.hello();
    }
}
