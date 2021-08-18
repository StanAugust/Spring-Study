package userDefine;

/**
 * @ClassName DefinePointcut
 * @Description 自定义类来实现AOP
 * @Author Stan
 * @Date 2021年08月07日
 */
public class DefinePointcut {

    public void before(){
        System.out.println("=======方法执行前=======");
    }

    public void after(){
        System.out.println("=======方法执行后=======");
    }
}
