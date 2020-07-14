package org.springtiny.aop;

import org.aopalliance.aop.Advice;

/**
 * 通知器
 *
 * @author zlzhou
 */
public interface Advisor {

    Advice getAdvice();
}
