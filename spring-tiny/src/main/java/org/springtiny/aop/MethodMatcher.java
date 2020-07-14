package org.springtiny.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配器
 *
 * @author zlzhou
 */
public interface MethodMatcher {

    boolean matches(Method method, Class targetClass);

}
