package org.springtiny.test.bean;

import org.springtiny.beans.BeanDefinition;
import org.springtiny.beans.PropertyValue;
import org.springtiny.beans.PropertyValues;
import org.springtiny.beans.factory.AutowireCapableBeanFactory;
import org.springtiny.beans.factory.BeanFactory;
import org.springtiny.test.HelloWorldService;

/**
 * @author zlzhou
 */
public class BeanFactoryTest {

    public static void main(String[] args) throws Exception {

        //1. 初始化beanfactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        //2. bean 定义
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("org.springtiny.test.HelloWorldService");

        //3. 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("text","NIO"));
        beanDefinition.setPropertyValues(propertyValues);

        //4. 生成
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        //5. 获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.hello();
    }
}
