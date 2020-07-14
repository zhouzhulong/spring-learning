package org.springtiny.test.aop;

import org.springtiny.aop.AspectJExpressionPointcut;
import org.springtiny.test.HelloWorldService;

/**
 * @author zlzhou
 */
public class AspectJExpressionPointcutTest {

    public static void main(String[] args) throws Exception {
        testClassFilter();
    }

    public static void testClassFilter() throws Exception {
//        1、execution(): 表达式主体。
//        2、第一个*号：表示返回类型，*号表示所有的类型。
//        3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
//        4、第二个*号：表示类名，*号表示所有的类。
//        5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
        String expression = "execution(* org.springtiny.test.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(HelloWorldService.class.getDeclaredMethod("hello"), HelloWorldService.class);
        System.out.println(matches);
        boolean matchesClass = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldService.class);
        System.out.println(matchesClass);
    }
}
