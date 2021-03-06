package org.springtiny.test.aop;

import org.springtiny.aop.AdvisedSupport;
import org.springtiny.aop.JdkDynamicAopProxy;
import org.springtiny.aop.TargetSource;
import org.springtiny.context.ApplicationContext;
import org.springtiny.context.ClassPathXmlApplicationContext;
import org.springtiny.test.HelloNio;
import org.springtiny.test.HelloWorld;

/**
 * @author zlzhou
 */
public class JdkDynamicAopProxyTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorld helloWorldService = (HelloWorld) applicationContext.getBean("helloWorldService");
        helloWorldService.hello();
        HelloNio helloNioService = (HelloNio) applicationContext.getBean("helloNioService");
        helloNioService.hello();

        //1. 设置被代理对象
        AdvisedSupport advised = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorld.class);
        advised.setTargetSource(targetSource);

        //2.设置拦截器
        TimeInterceptor timerInterceptor = new TimeInterceptor();
        advised.setMethodInterceptor(timerInterceptor);

        //3.创建代理
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advised);
        HelloWorld helloWorldServiceProxy = (HelloWorld) jdkDynamicAopProxy.getProxy();

//        helloWorldServiceProxy.hello();

    }
}
