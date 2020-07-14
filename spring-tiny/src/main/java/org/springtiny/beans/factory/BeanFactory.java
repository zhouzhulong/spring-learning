package org.springtiny.beans.factory;

/**
 * @author zlzhou
 */
public interface BeanFactory {

    public Object getBean(String name) throws Exception;

}
