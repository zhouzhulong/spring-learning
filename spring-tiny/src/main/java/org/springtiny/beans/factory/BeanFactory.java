package org.springtiny.beans.factory;

import org.springtiny.beans.BeanDefinition;

/**
 * @author zlzhou
 */
public interface BeanFactory {

    public Object getBean(String name);

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
