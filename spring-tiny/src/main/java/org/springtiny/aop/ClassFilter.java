package org.springtiny.aop;

/**
 * 类过滤器
 *
 * @author zlzhou
 */
public interface ClassFilter {

    boolean matches(Class targetClass);
}
