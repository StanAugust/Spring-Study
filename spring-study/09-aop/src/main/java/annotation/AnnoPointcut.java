package annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @ClassName AnnoPointcut
 * @Description 使用注解实现AOP
 * @Author Stan
 * @Date 2021年08月09日
 */
@Aspect     // 标注这个类是一个切面
public class AnnoPointcut {

    @Before("execution(* springAPI.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("=======方法执行前=======");
    }

    @After("execution(* springAPI.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("=======方法执行后=======");
    }

    @Around("execution(* springAPI.UserServiceImpl.*(..))")
    public void aroudn(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("around before》》》");

        Object proceed = jp.proceed();// 执行目标方法
        System.out.println(jp.getSignature());  // 连接点上还能获得切入点的一些其它信息

        System.out.println("around after》》》");
    }
}
