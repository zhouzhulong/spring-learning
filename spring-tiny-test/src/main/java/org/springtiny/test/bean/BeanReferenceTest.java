package org.springtiny.test.bean;

import org.springtiny.beans.BeanDefinition;
import org.springtiny.beans.factory.AbstractBeanFactory;
import org.springtiny.beans.factory.AutowireCapableBeanFactory;
import org.springtiny.beans.io.ResourceLoader;
import org.springtiny.beans.xml.XmlBeanDefinitionReader;
import org.springtiny.test.HelloNioService;
import org.springtiny.test.HelloWorldService;

import java.util.Map;

/**
 * @author zlzhou
 */
public class BeanReferenceTest {

    public static void main(String[] args) throws Exception {

        //1.从xml加载bean配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
        //2.初始化BeanFactory并注册bean
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
        //3.初始化bean
        beanFactory.preInstantiateSingletons();
        //4.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.hello();
        HelloNioService helloNioService = (HelloNioService) beanFactory.getBean("helloNioService");
        helloNioService.hello();
    }
}
