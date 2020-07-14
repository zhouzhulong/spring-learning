package org.springtiny.aop;

import org.aopalliance.aop.Advice;

/**
 * AspectJ表达式切点
 *
 * @author zlzhou
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    public AspectJExpressionPointcut getPointcut() {
        return pointcut;
    }

    public void setPointcut(AspectJExpressionPointcut pointcut) {
        this.pointcut = pointcut;
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
