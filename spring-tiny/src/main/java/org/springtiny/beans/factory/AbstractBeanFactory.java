package org.springtiny.beans.factory;

import org.springtiny.aop.AspectJAwareAdvisorAutoProxyCreator;
import org.springtiny.aop.ObjectFactory;
import org.springtiny.beans.BeanDefinition;
import org.springtiny.beans.BeanPostProcessor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zlzhou
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    /**
     * Cache of singleton objects: bean name to bean instance.
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
    /**
     * Cache of singleton factories: bean name to ObjectFactory.
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);
    /**
     * Cache of early singleton objects: bean name to bean instance.
     */
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);
    /**
     * Names of beans that are currently in creation.
     */
    private final Set<String> singletonsCurrentlyInCreation = Collections.newSetFromMap(new ConcurrentHashMap<>(16));

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private final List<String> beanDefinitionNames = new ArrayList<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws Exception {
        //从缓存获取对象
        Object bean = getSingleton(name);
        if (bean == null) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(name);
            if (beanDefinition == null) {
                throw new IllegalArgumentException("No bean named " + name + " is defined");
            }
            bean = doCreateBean(name, beanDefinition);
            bean = initializeBean(bean, name);
            singletonObjects.put(name, bean);
        }
        return bean;
    }


    protected Object doCreateBean(String name, BeanDefinition beanDefinition) throws Exception {
        singletonsCurrentlyInCreation.add(name);
        Object bean = createBeanInstance(beanDefinition);
        addSingletonFactory(name, () -> getEarlyBeanReference(name, bean));
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected Object getEarlyBeanReference(String beanName, Object bean) throws Exception {
        Object exposedObject = bean;
        for (BeanPostProcessor bp : beanPostProcessors) {
            if (bp instanceof AspectJAwareAdvisorAutoProxyCreator) {
                AspectJAwareAdvisorAutoProxyCreator ibp = (AspectJAwareAdvisorAutoProxyCreator) bp;
                exposedObject = ibp.getEarlyBeanReference(exposedObject, beanName);
            }
        }
        return exposedObject;
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        synchronized (this.singletonObjects) {
            if (!this.singletonObjects.containsKey(beanName)) {
                this.singletonFactories.put(beanName, singletonFactory);
                this.earlySingletonObjects.remove(beanName);
            }
        }
    }

    protected Object getSingleton(String beanName) throws Exception {
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            synchronized (this.singletonObjects) {
                singletonObject = this.earlySingletonObjects.get(beanName);
                if (singletonObject == null) {
                    ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
                    if (singletonFactory != null) {
                        singletonObject = singletonFactory.getObject();
                        this.earlySingletonObjects.put(beanName, singletonObject);
                        this.singletonFactories.remove(beanName);
                    }
                }
            }
        }
        return singletonObject;
    }

    public boolean isSingletonCurrentlyInCreation(String beanName) {
        return this.singletonsCurrentlyInCreation.contains(beanName);
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    public void preInstantiateSingletons() throws Exception {
        for (String beanName : this.beanDefinitionNames) {
            getBean(beanName);
        }
    }

    protected Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }
        //TODO:call initialize method
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitializaiton(bean, name);
        }
        return bean;
    }


    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
        this.beanPostProcessors.add(beanPostProcessor);
    }


    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

    }
}
