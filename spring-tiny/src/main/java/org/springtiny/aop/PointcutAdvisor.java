package org.springtiny.aop;

/**
 * 切点通知器
 *
 * @author zlzhou
 */
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
