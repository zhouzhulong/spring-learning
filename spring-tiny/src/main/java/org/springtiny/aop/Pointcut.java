package org.springtiny.aop;

/**
 * 切点
 *
 * @author zlzhou
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
