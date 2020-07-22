package org.springtiny.aop;

/**
 * 通过动态代理实现了逻辑的织入
 *
 * @author zlzhou
 */
public interface AopProxy {

    Object getProxy();
}
