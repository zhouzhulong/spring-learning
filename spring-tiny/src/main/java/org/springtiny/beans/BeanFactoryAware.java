package org.springtiny.beans;

import org.springtiny.beans.factory.BeanFactory;

/**
 * @author zlzhou
 */
public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
