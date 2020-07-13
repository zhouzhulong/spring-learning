package org.springtiny.beans;

/**
 * 从配置文件读取beandefinition
 *
 * @author zlzhou
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;

}
