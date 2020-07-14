package org.springtiny.context;

import org.springtiny.beans.BeanDefinition;
import org.springtiny.beans.factory.AbstractBeanFactory;
import org.springtiny.beans.factory.AutowireCapableBeanFactory;
import org.springtiny.beans.io.ResourceLoader;
import org.springtiny.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author zlzhou
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
