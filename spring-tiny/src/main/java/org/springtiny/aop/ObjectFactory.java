package org.springtiny.aop;

/**
 * @author zlzhou
 */
public interface ObjectFactory<T> {

    T getObject() throws Exception;

}
