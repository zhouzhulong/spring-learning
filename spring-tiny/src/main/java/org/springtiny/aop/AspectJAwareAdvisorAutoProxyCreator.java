package org.springtiny.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.springtiny.beans.BeanFactoryAware;
import org.springtiny.beans.BeanPostProcessor;
import org.springtiny.beans.factory.AbstractBeanFactory;
import org.springtiny.beans.factory.BeanFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zlzhou
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private AbstractBeanFactory beanFactory;
    private final Map<Object, Object> earlyProxyReferences = new ConcurrentHashMap<>(16);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitializaiton(Object bean, String beanName) throws Exception {
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        if (this.earlyProxyReferences.remove(beanName) != bean) {
            return wrapIfNecessary(bean);
        }
        return bean;
    }

    protected Object wrapIfNecessary(Object bean) throws Exception {
        List<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            if (advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
                AdvisedSupport advisedSupport = new AdvisedSupport();
                advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
                advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
                TargetSource targetSource = new TargetSource(bean, bean.getClass().getInterfaces());
                advisedSupport.setTargetSource(targetSource);
                return new JdkDynamicAopProxy(advisedSupport).getProxy();
            }
        }
        return bean;
    }

    public Object getEarlyBeanReference(Object bean, String beanName) throws Exception {
        this.earlyProxyReferences.put(beanName, bean);
        return wrapIfNecessary(bean);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }

}
