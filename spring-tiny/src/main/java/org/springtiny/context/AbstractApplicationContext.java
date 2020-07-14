package org.springtiny.context;

import org.springtiny.beans.factory.AbstractBeanFactory;

/**
 * @author zlzhou
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {
        
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

}
