package org.springtiny.beans;

/**
 * bean 初始化扩展
 * bean 后处理器
 *
 * @author zlzhou
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessAfterInitializaiton(Object bean, String beanName) throws Exception;

}
