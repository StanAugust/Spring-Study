

# Spring学习笔记



## 1. Spring

### 1.1 简介

> Spring : 春天 ——给软件行业带来了春天
>
> 2002年，Rod Jahnson首次推出了Spring框架雏形interface21框架。
>
> 2004年3月24日，Spring框架以interface21框架为基础，经过重新设计，发布了1.0正式版。



Spring理念 : 使现有技术更加实用 . 本身就是一个大杂烩 , 整合现有的框架技术

官网 : http://spring.io/

官方下载地址 : https://repo.spring.io/libs-release-local/org/springframework/spring/

GitHub : https://github.com/spring-projects



### 1.2 优点

1. Spring是一个开源免费的框架（容器）
2. Spring是一个轻量级、非入侵式（不会改变原有代码）的框架
3. 控制反转（IOC）、面向切面编程（AOP）
4. 对事务处理的支持，对框架整合的支持

==**总结：Spring是一个轻量级的控制反转（IOC）和面向切面（AOP）的非侵入式容器（框架）**==



### 1.3 组成

Spring框架是一个分层架构，由7个定义良好的模块组成。

Spring模块构建在核心容器之上，核心容器定义了创建、配置和管理bean的方式

![image-20210727173706956](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210727173706956.png)



### 1.4 拓展

![image-20210727174445178](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210727174445178.png)



#### 1.4.1 Spring Boot

+ Spring Boot是Spring的一套快速配置脚手架，可以基于Spring Boot快速开发出单个微服务
+ 专注于快速、方便集成的单个微服务个体
+ 使用了约束优于配置的理念，很多集成方案已经帮你选择好了，能不配置就不配置

#### 1.4.2 Spring Cloud

+ Spring Cloud是基于Spring Boot实现的（Spring Boot可以离开Spring Cloud独立使用开发项目，但是Spring Cloud离不开Spring Boot，属于依赖的关系）
+ 关注全局的服务治理框架



> 现代化的开发，说白了就是基于Spring的开发
>
> 但发展了太久之后，违背了原来的理念！配置十分繁琐，人称：“配置地狱！”

---



## 2. IOC

### 2.1 IOC理论推导

#### 2.1.1 原代码

原来的方式实现一个业务：

![image-20210728101649872](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728101649872.png)	

+ 先写一个UserDao接口

  ```java
  public interface UserDao {
      public void getUser();
  }
  ```

+ 再去写UserDao的实现类

  ```java
  public class UserDaoImpl implements UserDao{
      @Override
      public void getUser() {
          System.out.println("默认实现获取用户");
      }
  }
  ```

+ 再写一个UserService的接口

  ```java
  public interface UserService {
      public void getUser();
  }
  ```

+ 最后再写UserService的实现类

  ```java
  public class UserServiceImpl implements UserService{
      private UserDao userDao = new UserDaoImpl();
  
      @Override
      public void getUser() {
          userDao.getUser();
      }
  }
  ```

+ 进行测试

  ```java
  public class UserServiceTest {
      @Test
      public void userServiceTest() {
          // 用户实际调用的是业务层，不需要接触dao层
          UserServiceImpl userService = new UserServiceImpl();
          userService.getUser();
      }
  }
  ```

  ![image-20210728102116426](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728102116426.png)	

#### 2.1.2 出现问题

如果此时用户增加一个需求，很有可能会影响原来的代码，用户每改变一次需求就要修改一次，成本非常高

+ 比如增加多个UserDao的实现类

  ![image-20210728102733330](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728102733330.png)	

+ 紧接着如果要去使用这些新功能，就需要去service实现类中修改对应的实现

  ![image-20210728102930752](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728102930752.png)	

代码耦合性太高，牵一发而动全身

#### 2.1.3 解决方案

在需要用到它的地方，不去实现它，而是留出一个接口：

```java
public class UserServiceImpl2 implements UserService{

    private UserDao userDao;

    // 利用set实现值的动态注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void getUser() {
        userDao.getUser();
    }
}
```

此时再去进行测试：

```java
public class UserServiceTest {
    @Test
    public void userService2Test(){
        UserServiceImpl2 userService = new UserServiceImpl2();

        userService.setUserDao(new UserDaoMysqlImpl());
        userService.getUser();
        // 现在又想用Oracle去实现
        userService.setUserDao(new UserDaoOracleImpl());
        userService.getUser();
    }
}
```

![image-20210728103951041](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728103951041.png)	



之前，程序是主动创建对象，控制权在程序媛手上

使用了set注入后，程序不再具有主动性，而是变成了被动的接收对象

**这种思想，从本质上解决了问题，程序媛不用再去管理对象的创建，系统的耦合性大大降低，可以更加专注在业务的实现上，这是IOC的原型**



### 2.2 IOC本质

**控制反转(Inversion of Control，IOC)，是一种设计思想，可以用来降低代码之间的耦合度，**

它通过描述（XML或注解）并通过第三方去生产或获取特定对象

其中最常见的方式叫做**依赖注入（Dependency Injection， DI）**

没有IOC的程序中 , 我们使用面向对象编程 , 对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制。

个人认为所谓控制反转就是：获得依赖对象的方式反转了。



**IOC是Spring框架的核心内容**，在Spring中实现控制反转的是IOC容器，实现方式可以是XML配置，也可以是注解，新版本的Spring也可以零配置实现IOC。

Spring容器在初始化时先读取配置文件，根据配置文件或元数据创建与组织对象存入容器中，程序使用时再从IOC容器中取出需要的对象。

<img src="https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728114024738.png" alt="image-20210728114024738" style="zoom:80%;" />

采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。

---



## 3. Hello Spring

新建一个maven项目，结构如下：

![image-20210728163946069](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728163946069.png)	

+ 编写一个Hello实体类

  ```java
  public class Hello {
      private String str;
  
      public String getStr() {
          return str;
      }
  
      public void setStr(String str) {
          this.str = str;
      }
  
      @Override
      public String toString() {
          return "Hello, " + str;
      }
  }
  ```

+ 编写Spring文件，这里命名为beans.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--Spring中bean就相当于Java对象，由Spring创建和管理-->
      <bean id="hello" class="pojo.Hello">
          <property name="str" value="Spring"/>
      </bean>
  
  </beans>
  ```

+ 可以进行测试了

  ```java
  public class HelloSpringTest {
      @Test
      public void test() {
          // 解析xml文件，生成管理相应的bean对象
          ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
  
          // getBean：参数即为配置文件中bean的id
          Hello hello = (Hello) context.getBean("hello");
          System.out.println(hello.toString());
      }
  }
  ```

  ![image-20210728164728662](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728164728662.png)	



> **思考**

+ Hello对象是由谁创建的？——由Spring创建
+ Hello对象的属性是怎么设置的？——由Spring容器设置

这个过程就叫控制反转：

+ 控制：谁来控制对象的创建？传统应用程序的对象是由程序本身控制创建的，使用Spring后，对象由Spring来创建
+ 反转：程序本身不创建对象，而变成被动的接受对象

依赖注入，就是利用set方法来进行注入



> 修改案例一

+ 在案例一中，新增一个配置文件beans.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <bean id="mysql" class="dao.UserDaoMysqlImpl"/>
      <bean id="oracle" class="dao.UserDaoOracleImpl"/>
  
      <bean id="serviceImpl" class="service.UserServiceImpl2">
          <!--注意: 这里的name并不是属性 , 而是set方法后面的那部分 , 首字母小写-->
          <!--引用另外一个bean , 不是用value 而是用 ref-->
          <property name="userDao" ref="mysql"/>
      </bean>
  </beans>
  ```

+ 测试

  ```java
  public class UserServiceTest {
      @Test
      public void xmlTest(){
          ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
          UserServiceImpl2 serviceImpl = (UserServiceImpl2) context.getBean("serviceImpl");
          serviceImpl.getUser();
      }
  }
  ```

  ![image-20210728172327255](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728172327255.png)	

  现在，就可以彻底不用在程序中进行改动了，要实现不同的操作，只需要在xml配置文件中进行修改。所谓的IOC，就是对象由Spring来创建、管理、装配！

---



## 4. IOC创建对象的方式

### 4.1 使用无参构造器（默认）

> 无参构造器是默认的构造器，并且在配置文件加载时，其中管理的对象都已经初始化了

![image-20210728185615893](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728185615893.png)	

> 并且默认使用单例模式，只会初始化一份实例

​	![image-20210728191815248](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728191815248.png)	

![image-20210728191823069](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728191823069.png)	

> 有参构造器不是默认的构造器

![image-20210728185058019](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728185058019.png)	



### 4.2 使用有参构造器

> 实体类

![image-20210728190612834](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728190612834.png)	

> 测试类

![image-20210728190625019](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728190625019.png)	



#### 4.2.1 下标赋值

```xml
<bean id="userInfo" class="pojo.UserInfo">
        <constructor-arg index="0" value="1"/>
        <constructor-arg index="1" value="Stan"/>
</bean>
```

![image-20210728190804304](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728190804304.png)	

#### 4.2.2 类型赋值

相同类型的参数按顺序赋值

```xml
<bean id="userInfo" class="pojo.UserInfo">
        <constructor-arg type="int" value="2"/>
        <constructor-arg type="java.lang.String" value="Stan"/>
</bean>
```

![image-20210728191017946](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728191017946.png)	

#### 4.2.3 参数名赋值（推荐）

```xml
<bean id="userInfo" class="pojo.UserInfo">
        <constructor-arg name="id" value="3"/>
        <constructor-arg name="name" value="Stan"/>
</bean>
```

![image-20210728191149650](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210728191149650.png)	

---



## 5. Spring配置

### 5.1 alias

![image-20210729181140441](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729181140441.png)·

这行代码的位置被取别名的bean的前后均可

取了别名后，原名的id或name还能用

![image-20210729181403435](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729181403435.png)	



### 5.2 Bean的配置

```xml
<!--
	id: 是bean的标识符,要唯一
    class是bean的全限定名=包名+类名
    name: 如果没有配置id,name就是默认标识符
          如果配置id,又配置了name,那么name是别名
          name可以设置多个别名,可以用逗号,分号,空格隔开
    如果不配置id和name,可以根据applicationContext.getBean(.class)获取对象;
-->
<bean id="userInfo" class="pojo.UserInfo" name="info, i; information">
    <constructor-arg name="id" value="3"/>
    <constructor-arg name="name" value="Stan"/>
</bean>
```



### 5.3 import

一般用于团队开发，它可以将多个配置文件，导入合并为一个

![image-20210729183902970](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729183902970.png)	

![image-20210729183927460](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729183927460.png)	

---





## 6. 依赖注入

> 依赖注入（Dependency Injection,DI）
>
> 依赖：指Bean对象的创建依赖于容器
>
> 注入：指Bean对象所依赖的资源，由容器来设置和装配



### 6.1 构造器注入

参考 [4. IOC创建对象的方式](#4.-IOC创建对象的方式)



### 6.2 Set方式注入（重点）

要求被注入的属性，必须有set方法（Boolean类型是isXx）

> 测试pojo类

![image-20210729185338210](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729185338210.png)	

![image-20210729185414235](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729185414235.png)	

> 注入

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="address" class="pojo.Address">
        <!-- 1.常量注入 -->
        <property name="address" value="China"/>
    </bean>

    <bean id="student" class="pojo.Student">
        <property name="name" value="Stan"/>
        <!-- 2. Bean注入 -->
        <property name="address" ref="address"/>

        <!-- 3. 数组注入-->
        <property name="majors">
            <array>
                <value>Computer Science</value>
                <value>Management</value>
            </array>
        </property>

        <!-- 4. List注入-->
        <property name="hobbies">
            <list>
                <value>coding</value>
                <value>music</value>
                <value>movie</value>
            </list>
        </property>

        <!-- 5. Map注入-->
        <property name="cards">
            <map>
                <entry key="id" value="350122111111111111"/>
                <entry key="bank" value="41050311111111111111"/>
            </map>
        </property>

        <!-- 6. Set注入-->
        <property name="games">
            <set>
                <value>Starve Together</value>
                <value>Red Alert</value>
            </set>
        </property>

        <!-- 7. Null注入-->
        <property name="partner">
            <null/>
        </property>

        <!-- 8. Properties注入-->
        <property name="info">
            <props>
                <prop key="sex">Female</prop>
                <prop key="age">18</prop>
            </props>
        </property>
    </bean>
</beans>
```

> 测试

![image-20210729191226860](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729191226860.png)	

![image-20210729191151827](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729191151827.png)	



### 6.3 拓展方式注入

> 测试pojo类（注意：这里没有有参构造器）

![image-20210729193118716](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729193118716.png)	

#### 6.3.1 p命名注入

需要导入约束：p命名注入就是属性（properties）注入

```xml
xmlns:p="http://www.springframework.org/schema/p"

<!-- P(属性: properties)命名空间 , 属性依然要设置set方法 -->
<bean id="user" class="pojo.User" p:id="3" p:name="Stan"/>
```

#### 6.3.2 c命名注入

需要导入约束：

```xml
xmlns:c="http://www.springframework.org/schema/c"

<!-- C(构造: Constructor)命名空间 , 属性依然要设置set方法 -->
<bean id="user" class="pojo.User" c:id="3" c:name="Stan"/>
```

这样会出现问题：

![image-20210729193722160](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729193722160.png)	

解决：pojo类中要加上有参的构造器，所以，c命名注入就是所谓的构造器（Construct）注入

![image-20210729193917063](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729193917063.png)	



### 6.4 bean的作用域

![image-20210729194039792](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210729194039792.png)

#### 6.4.1 单例模式（默认）

当一个bean的作用域为Singleton，那么IOC容器中只会存在一个共享的bean实例

所有对bean的请求，只要id与该bean定义相匹配，则只会返回bean的同一实例

在创建起容器时就同时自动创建了一个bean的对象，不管你是否使用，他都存在了

可以显示说明：

```xml
<bean id="user" class="pojo.User" p:id="3" p:name="Stan" scope="singleton"/>
```

[4.1节中有测试结果](#4.1-使用无参构造器（默认）)

#### 6.4.2 原型模式

当一个bean的作用域为Prototype，表示一个bean定义对应多个对象实例

在每次对该bean请求（将其注入到另一个bean中，或者以程序的方式调用容器的getBean()方法）时都会创建一个新的bean实例

在创建容器的时候并没有实例化，而是当获取bean的时候才会去创建一个对象（懒加载）

配置：

```xml
<bean id="user" class="pojo.User" p:id="3" p:name="Stan" scope="prototype"/>
```

#### 6.4.3 其它

其余的request、session等，仅在基于web的应用中使用（无关web应用框架），只能用在基于web的Spring ApplicationContext环境

---



## 7. Beans的自动装配

> 说明

自动装配是使用spring**满足bean==依赖==**的一种方法，spring会自动在应用上下文中为某个bean寻找其依赖的bean

Spring中bean有三种装配机制，分别是：

+ 在xml中显式配置；
+ 在java中显式配置；
+ **隐式的bean发现机制和自动装配**

Spring的自动装配需要从两个角度来实现，或者说是两个操作：

+ 组件扫描(component scanning)：spring会自动发现应用上下文中所创建的bean

+ 自动装配(autowiring)：spring自动满足bean之间的依赖，也就是我们说的IOC/DI



> 环境搭建：测试pojo类

![image-20210730202803508](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730202803508.png)	

![image-20210730201232600](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730201232600.png)	



### 7.1 xml里的自动装配

如果在xml中进行显示配置，会是这样：

![image-20210730203334501](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730203334501.png)	

手动配置xml过程中，常常发生字母缺漏和大小写等错误，采用自动装配将避免这些错误，并且使配置简单化



#### 7.1.1 ByName自动装配

![image-20210730203558479](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730203558479.png)	

![image-20210730203635251](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730203635251.png)	

注意点：

- byName的时候，需要保证所有bean的id唯一

- 这个bean的id或者name的值要跟需要set的对象的属性名字一值

  比如该例中$Person$是$setDog$，那么必须有个bean的id或者name为$dog$，才可以为$person$实现byName的自动装配

#### 7.1.2 ByType自动装配

![image-20210730204446169](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730204446169.png)	

注意点：

- byType的时候，需要保证所有bean的class唯一。只要class唯一，甚至把id属性去掉也不影响结果

- 这个bean需要和自动注入的属性的类型一致，且需要自动注入的属性必须有set方法

  ![image-20210730204903460](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730204903460.png)	

​	

### 7.2 使用注解实现自动装配

jdk1.5开始支持注解，spring2.5开始全面支持注解

> 使用注解的准备工作

+ 导入约束

+ 配置注解的支持

  ![image-20210730205613095](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730205613095.png)	

#### 7.2.1 @Autowired

> @Autowired 可以直接加在属性上，也可以加在set方法上，加在属性就可以不用写set方法了

![image-20210730210831570](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730210831570.png)	

正确输出结果：

![image-20210730211229893](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730211229893.png)	

> @Autowired优先按类型自动转配，类型相同时按name转配，都不符合则报错

![image-20210730212641686](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730212641686.png)	

![image-20210730213323426](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730213323426.png)	

> @Autowired(required=false)  ：
>
> ​	false，对象可以为null；
>
> ​	true，必须存对象，不能为null，否则报错

![image-20210730212247335](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730212247335.png)	

#### 7.2.2 @Qualifier

- 如果@Autowired自动装配的环境比较复杂，这时可以配合上@Qualifier根据byName的方式，指定一个唯一的bean对象注入！

- @Qualifier不能单独使用

  ![image-20210730213904429](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730213904429.png)	

#### 7.2.3 @Resource

> @Resource如有指定的name属性，先按该属性进行byName方式查找装配

![image-20210730214816925](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730214816925.png)	

> 如果没有额外限定，则按默认的byName方式进行装配

![image-20210730214956285](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730214956285.png)	

> 如果以上都不成功，则按byType的方式自动装配，如果type也不相同则报错

![image-20210730215320814](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730215320814.png)	

 

> **@Resource和@Autowired的异同：**

+ 它们的作用相同，都是用注解方式注入对象
+ 执行顺序不同：@Autowired先byType（更常用），@Resource先byName

---



## 8. 使用注解开发

### 8.1 基础 @Component

> 导包

在spring4之后，想要使用注解形式，必须得要引入aop的包

![image-20210802172509727](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802172509727.png)	

> 配置约束

​	![image-20210730205613095](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210730205613095.png)

> 属性如何注入

+ 配置扫描哪些包下的注解

  ```xml
  <!--指定要扫描的包，这个包下的注解就会生效-->
  <context:component-scan base-package="pojo"/>
  ```

+ 在指定包下编写类，添加注解 `@Component`

  ![image-20210802201144241](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802201144241.png)	

  > ![image-20210825213642329](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210825213642329.png)	

+ 属性注入`@Value`

  ![image-20210802201305815](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802201305815.png)	

  + 可以不用提供set方法，直接在属性上添加@value("值")

  + 如果提供了set方法，在set方法上添加@value("值");

+ 测试

  ![image-20210802200810828](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802200810828.png)	



### 8.2 衍生的注解

@Component有几个衍生注解，主要是为了按照mvc三层架构进行更好的分层。

目前功能都是一样的，都是代表将某个类注册到Spring中

+ dao 层：【**@Repository**】
+ service 层： 【**@Service**】
+ controller 层：【**@Controller**】



### 8.3 自动装配

[就是前面7. Beans的自动装配的内容](#7.-Beans的自动装配)



### 8.4 作用域

**@Scope**

- singleton：默认的，Spring会采用单例模式创建这个对象。关闭工厂 ，所有的对象都会销毁。

- prototype：多例模式。关闭工厂 ，所有的对象不会销毁。内部的垃圾回收机制会回收

  ![image-20210802202643953](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802202643953.png)	

  

### 8.5 小结

**XML与注解比较:**

+ XML可以适用任何场景 ，结构清晰，维护方便
+ 注解开发简单方便，但是不是自己提供的类使用不了

**xml与注解整合开发** ：推荐最佳实践

- xml管理Bean
- 注解完成属性注入

---



## 9. 基于Java类配置Spring

现在要完全不使用Spring的xml配置了，全权交给Java来做

> JavaConfig 原来是 Spring 的一个子项目，它通过 Java 类的方式提供 Bean 的定义信息
>
> 在 Spring4 的版本， JavaConfig 已正式成为 Spring4 的核心功能 。



### 代码实现

![image-20210802205118017](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802205118017.png)	

+ 编写一个实体类，用 `@Component `将其注册到Spring中

  ![image-20210802205237485](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802205237485.png)		

+ 新建一个config配置包，编写一个配置类，用 `@Configuration` 表示

  ![image-20210802205258325](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802205258325.png)	

  `@Configuration` 和 `@Bean` 结合使用

  + `@Configuration` 相当于xml中的<beans>标签

  + `@Bean` 相当于xml中的<bean>标签

    > ![](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210825214407314.png)	
    >
    > ![image-20210825214728491](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210825214728491.png)	

+ 测试，读取配置的类不同了

  ![image-20210802205433584](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802205433584.png)	

  ![image-20210802204618123](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802204618123.png)

  ![image-20210802205737000](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802205737000.png)	



### 导入其它配置@Import

![image-20210802210629144](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210802210629144.png)	



这种纯Java的配置方式，在SpringBoot中随处可见，现在先简单了解

---



## 10. 代理模式

代理模式分为：静态代理和动态代理

AOP的底层机制就是动态代理



### 10.1 静态代理

#### 10.1.1 角色分析

+ 抽象角色 : 一般使用接口或者抽象类来实现

+ 真实角色 : 被代理的角色

+ 代理角色 : 去代理真实角色 ，代理真实角色后 , 一般会做一些附属的操作 

+ 客户  :  使用代理角色来进行一些操作 

  （图片加强理解）

  <img src="https://gitee.com/StanAugust/picbed/raw/master/img/image-20210803195508185.png" alt="image-20210803195508185"  />	

#### 10.1.2 代码实现

![image-20210803195701426](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210803195701426.png)	

+ 抽象角色：Rent.java

  ```java
  public interface Rent {
      public void rent();
  }
  ```

+ 真实角色：HouseHolder.java

  ```java
  public class HouseHolder implements Rent {
      @Override
      public void rent() {
          System.out.println("房东出租房子");
      }
  }
  ```

+ 代理角色：Agency.java

  ```java
  public class Agency implements Rent {
  
      private HouseHolder holder;
  
      // constructor
      public Agency() {    }
      public Agency(HouseHolder holder) {		this.holder = holder;    }
  
      @Override
      public void rent() {
          visit();
          holder.rent();	// 执行真实角色的功能
          signContract();
          collectFees();
      }
  	
      // 代理额外的附属操作
      private void visit(){	System.out.println("中介带着参观房子");    }
  
      private void collectFees(){	System.out.println("收取中介费");    }
  
      private void signContract(){	System.out.println("签订合同");    }
  }
  ```

+ 客户：Client.java

  ```java
  public class Client {
      public static void main(String[] args) {
          HouseHolder houseHolder = new HouseHolder();
          Agency agency = new Agency(houseHolder);	// 设置要代理的真实角色
          agency.rent();
      }
  }
  ```

+ 测试结果

  ![image-20210803200529684](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210803200529684.png)	

#### 10.1.3 小结

在这个过程中，客户直接接触的就是中介，就如同现实生活中的样子，客户看不到房东，但是依旧能通过代理租到房东的房子，这就是所谓的代理模式。

源码工程demo02 有静态代理再理解



**静态代理的优点:**

- 可以使真实角色更加纯粹 ，不用关注一些公共的事情 
- 公共的业务由代理来完成，实现了业务的分工 
- 公共业务发生扩展时变得更加集中和方便 

缺点 :

- 静态代理可以将接口的某一个方法增强，但如果该接口有n个方法，都需要做同样的增强，就会产生很多重复工作

  ![image-20210806205523307](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210806205523307.png)	



这时候就需要动态代理，可以实现将某接口的所有方法做同样的增强。



### 10.2 动态代理

- 动态代理的角色和静态代理的一样 

- 动态代理的代理类是动态生成的；静态代理的代理类是我们提前写好的

- 动态代理分为两类 :

  + 基于接口的动态代理——JDK动态代理（本节介绍，其余道理是一样的）

  - 基于类的动态代理——cglib

- 现在用的比较多的是 javasist 来生成动态代理 . 百度一下javasist



#### 10.2.1 JDK动态代理核心类

##### Proxy

![image-20210806211735006](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210806211735006.png)

Proxy.newProxyInstance()

![image-20210806212300966](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210806212300966.png)

##### **InvocationHandler**

![image-20210806212801580](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210806212801580.png)

invoke()

![image-20210806213210818](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210806213210818.png)

#### 10.2.2 代码实现

![image-20210806221732647](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210806221732647.png)	

+ 抽象角色：Rent.java

  ```java
  public interface Rent {
      public void rent();
  }
  ```

+ 真实角色：HouseHolder.java

  ```java
  public class HouseHolder implements Rent {
      @Override
      public void rent() {
          System.out.println("房东出租房子");
      }
  }
  ```

+ 代理类：ProxyInvocationHandler.java

  ```java
  public class ProxyInvocationHandler implements InvocationHandler {
  
      private Rent rent;	// 要代理的接口
  
      public void setRent(Rent rent) {
          this.rent = rent;
      }
  	
      // 生成代理类，重点是第二个参数，获取该代理类要实现的接口
      // 之前都是代理某一类角色，现在可以代理多个类，因为代理的是接口！
      public Object getProxy(){
          return Proxy.newProxyInstance(this.getClass().getClassLoader(), rent.getClass().getInterfaces(), this);
      }
  	
      // 处理代理实例上的方法并返回结果
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          visit();
          // 核心：本质是利用反射实现的！
          Object result = method.invoke(rent, args);
          return result;
      }
      
      // 代理做的额外操作
      private void visit(){
          System.out.println("中介带着参观房子");
      }
  }
  ```

+ 客户：Client.java

  ```java
  public class Client {
      public static void main(String[] args) {
          // 真实角色
          HouseHolder holder = new HouseHolder();
  
          // 代理类，这里还没生成代理实例
          ProxyInvocationHandler pih = new ProxyInvocationHandler();
          pih.setRent(holder);    // 设置要代理的角色
  
          // 动态生成对应的代理类
          Rent proxy = (Rent)pih.getProxy();
  
          proxy.rent();
  
          // 源码工程里下面有自己看着java api写的proxy class和invocation handler分离版
      }
  }
  ```

+ 测试结果

  ![image-20210807191409493](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210807191409493.png)	

#### 10.2.3 加深理解

使用动态代理改进demo02（见工程源码）

+ 代理类：ProxyInvocationHandler.java

  ```java
  public class ProxyInvocationHandler implements InvocationHandler {
  
      private Object proxied;    // 被代理的对象。将代理对象设置为Object，这个类就是通用的了
  
      public void setProxied(Object proxied) {
          this.proxied = proxied;
      }
  	
      // 生成代理类
      public Object getProxy(){
          return Proxy.newProxyInstance(this.getClass().getClassLoader(), proxied.getClass().getInterfaces(), this);
      }
  
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          log(method.getName());	// 这样只需一行代码，即可将该接口下所有方法进行同样的增强
          Object result = method.invoke(proxied, args);
          return result;
      }
  	
      // 代理做的额外操作
      private void log(String msg){
          System.out.println("[DEBUG] 执行了"+msg+"方法");
      }
  
  }
  ```

+ 测试 ClientTest.java

  ```java
  public class ClientTest {
      public static void main(String[] args) {
          // 真实业务
          UserServiceImpl userService = new UserServiceImpl();
  
          // 代理类，这里还没生成代理实例
          ProxyInvocationHandler pih = new ProxyInvocationHandler();
          pih.setProxied(userService);    // 设置要代理的角色
  
          // 动态生成对应的代理类
          UserService proxy = (UserService) pih.getProxy();
  
          proxy.delete();
      }
  }
  ```

+ 测试结果：

  ![image-20210807193535101](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210807193535101.png)	

#### 10.2.4 小结

动态代理的优点：静态代理有的它都有，静态代理没有的——

- 一个静态代理 , 一般代理某一类业务；一个动态代理可以代理多个类，代理的是接口！
- 对接口下n个方法进行增强时，显得更加简洁

---



## 11. AOP

### 11.1 AOP介绍

#### 11.1.1 什么是AOP

AOP（Aspect Oriented Programming）意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。

利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用。

![image-20210809141305845](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210809141305845.png)

![image-20210809143059163](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210809143059163.png)

#### 11.1.2 AOP在Spring中的作用

==提供声明式事务；允许用户自定义切面；==

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志 , 安全 , 缓存 , 事务等等 ....

- 切面（ASPECT）：横切关注点 被模块化 的特殊对象，即，它是一个类

- 通知（Advice）：切面必须要完成的工作，即，它是类中的一个方法

- 目标（Target）：被通知对象

- 代理（Proxy）：向目标对象应用通知之后创建的对象

- 切入点（PointCut）：切面通知 执行的 “地点”的定义

- 连接点（JointPoint）：与切入点匹配的执行点

  ![image-20210809143615265](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210809143615265.png)

SpringAOP中，通过Advice定义横切逻辑，Spring中支持5种类型的Advice:

![image-20210809144003704](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210809144003704.png)

即AOP在不改变原有代码的情况下，去增加新的功能。



### 11.2 使用Spring实现AOP

使用AOP织入，需要导入一个依赖包

```xml
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
   <groupId>org.aspectj</groupId>
   <artifactId>aspectjweaver</artifactId>
   <version>1.9.4</version>
</dependency>
```



**代码实现**

> 业务接口

```java
// 抽象角色：增删改查业务
public interface UserService {
    void add();
    void delete();
    void update();
    void select();
}
```

> 实现类

```java
// 真实对象，完成增删改查操作的人
public class UserServiceImpl implements UserService{

    @Override
    public void add() {    System.out.println("add a user");    }

    @Override
    public void delete() {    System.out.println("delete a user");    }

    @Override
    public void update() {    System.out.println("update a user");    }

    @Override
    public void select() {    System.out.println("select a user");    }
}
```

> 测试类

```java
@Test
public void testAOP(){
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    UserService userService = context.getBean("userServiceImpl", UserService.class);
    userService.select();
}
```



#### 11.2.1 使用Spring的API接口

+ 先写增强类

  ```java
  public class Log implements MethodBeforeAdvice, AfterReturningAdvice {
      /**
       * @param method    要执行的目标对象的方法
       * @param args      被调用的方法的参数
       * @param target    目标对象
       * @throws Throwable
       */
      @Override
      public void before(Method method, Object[] args, Object target) throws Throwable {
          System.out.println("[DEBUG] 执行了 " + target.getClass().getName() + " 的 " + method.getName() + " 方法");
      }
  
      /**
       * @param returnValue   返回值
       * @param method        被调用的方法
       * @param args          被调用的方法的对象的参数
       * @param target        目标对象
       * @throws Throwable
       */
      @Override
      public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
          System.out.println("[DEBUG] 执行了 " + target.getClass().getName() + " 的 " + method.getName() + " 方法，返回值：" + returnValue);
      }
  }
  ```

+ Spring容器中导入约束，并注册bean，实现aop切入

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:aop="http://www.springframework.org/schema/aop"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/aop
          http://www.springframework.org/schema/aop/spring-aop.xsd">
  
      <!-- 注册bean -->
      <bean id="userServiceImpl" class="springAPI.UserServiceImpl"/>
      <bean id="log" class="springAPI.Log"/>
  
      <!-- 方式一：使用原生Spring API接口 -->
      <!-- aop的配置 -->
      <aop:config>
          <!-- 切入点，expression="execution(要执行的位置)" -->
          <aop:pointcut id="pointcut" expression="execution(* springAPI.UserServiceImpl.*(..))"/>
          <!-- 执行方法，advice-ref：在切入点附近要加上的功能 . pointcut-ref：切入点 -->
          <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
      </aop:config>
  </beans>

+ 结果

  ![image-20210809151018485](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210809151018485.png)	

#### 11.2.2 使用自定义类

+ 写一个自定义的切入类

  ```java
  public class DefinePointcut {
  
      public void before(){    System.out.println("=======方法执行前=======");    }
  
      public void after(){    System.out.println("=======方法执行后=======");    }
  }
  ```

+ Spring配置

  ```xml
  <!-- 注册bean -->
  <bean id="diy" class="userDefine.DefinePointcut"/>
  
  <!-- 方式二：自定义类来实现AOP -->
  <!-- aop的配置 -->
  <aop:config>
      <!-- 定义该bean为切面 -->
      <aop:aspect ref="diy">	
          <aop:pointcut id="pointcut" expression="execution(* springAPI.UserServiceImpl.*(..))"/>
          <aop:before method="before" pointcut-ref="pointcut"/>
          <aop:after method="after" pointcut-ref="pointcut"/>
      </aop:aspect>
  </aop:config>
  ```

+ 结果

  ![image-20210809151724398](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210809151724398.png)	

#### 11.2.3 使用注解

+ 写一个注解实现的增强类

  ```java
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
  
          jp.proceed();       // 执行目标方法
          System.out.println(jp.getSignature());  // 连接点上还能获得切入点的一些其它信息
  
          System.out.println("around after》》》");
      }
  }
  ```

+ Spring配置

  ```xml
  <!-- 方式三：使用注解实现AOP-->
  <!-- 注册bean -->
  <bean id="anno" class="annotation.AnnoPointcut"/>
  <!-- 开启注解支持，使用JDK进行动态代理织入增强（默认，proxy-target-class="false"）；使用cgLib（proxy-target-class="true"）-->
  <!-- 通过该声明自动为spring容器中那些配置@Aspect切面的bean创建代理，织入切面 -->
  <aop:aspectj-autoproxy proxy-target-class="false"/>
  ```

+ 结果

  ![image-20210809153737675](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210809153737675.png)	



### 11.3 小结

Aop的重要性 : 很重要 ！一定要理解其中的思路 , 主要是思想的理解这一块 

Spring的AOP就是将公共的业务 (日志 , 安全等) 和领域业务结合起来。 当执行领域业务时，会把公共业务加进来，实现公共业务的重复利用

领域业务更纯粹，程序媛专注领域业务，**其本质还是动态代理** 

---



## 12. 整合Mybatis

[mybatis-spring官方文档](http://mybatis.org/spring/zh/index.html)



### 12.1 整体步骤：

1. 导入相关依赖：

   ```xml
   <dependencies>
       <!-- junit -->
       <dependency>
           <groupId>junit</groupId>
           <artifactId>junit</artifactId>
           <version>4.12</version>
   	</dependency>
   	<!-- mybatis -->
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis</artifactId>
           <version>3.5.2</version>
       </dependency>
   	<!-- mysql -->
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>8.0.22</version>
       </dependency>
       <!-- Spring -->
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-webmvc</artifactId>
           <version>5.2.0.RELEASE</version>
       </dependency>
   	<!-- Spring操作数据库 -->
       <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-jdbc</artifactId>
           <version>5.1.10.RELEASE</version>
       </dependency>
   	<!-- aspectJ AOP 织入器 -->
       <dependency>
           <groupId>org.aspectj</groupId>
           <artifactId>aspectjweaver</artifactId>
           <version>1.9.4</version>
       </dependency>
   	<!-- mybatis-spring整合包 【重点】 -->
       <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis-spring</artifactId>
           <version>2.0.2</version>
       </dependency>
   	<!-- lombok依赖【可选】 -->
       <dependency>
           <groupId>org.projectlombok</groupId>
           <artifactId>lombok</artifactId>
           <version>1.16.10</version>
       </dependency>
   </dependencies>
   
   <!-- 配置Maven静态资源过滤问题! -->
   <build>
       <resources>
           <resource>
               <directory>src/main/java</directory>
               <includes>
                   <include>**/*.properties</include>
                   <include>**/*.xml</include>
               </includes>
               <filtering>true</filtering>
           </resource>
       </resources>
   </build>
   ```

2. 编写配置文件

3. 测试



### 12.2 回忆Mybatis

![image-20210810160159593](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210810160159593.png)	

1. 编写实体类User

   ```java
   @Data
   public class User {
   
       private int id;
       private String name;
       private String pwd;
   }
   ```

2. 编写Mybatis核心配置文件

   ```xml
   <configuration>
   
       <typeAliases>
           <!-- 这样指定包下所有类都有别名了 -->
           <package name="pojo"/>
       </typeAliases>
   
       <environments default="development">
           <environment id="development">
               <transactionManager type="JDBC"/>
               <dataSource type="POOLED">
                   <property name="driver" value="com.mysql.jdbc.Driver"/>
                   <property name="url" value="jdbc:mysql://localhost:3306/mybatis_test?serverTimezone=CST&amp;useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
                   <property name="username" value="root"/>
                   <property name="password" value="111111"/>
               </dataSource>
           </environment>
       </environments>
   
   </configuration>
   ```

3. 编写接口 XXMapper/XXDao

   ```java
   public interface UserMapper {
       public List<User> selectUser();
   }
   ```

4. 编写接口对应的Mapper.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   
   <mapper namespace="mapper.UserMapper">
       <select id="selectUser" resultType="user">
           select * from mybatis_test.user;
       </select>
   </mapper>
   ```

5. 在Mybatis核心配置文件中注册该接口

   ```xml
   <mappers>
       <package name="mapper"/>
   </mappers>
   ```

6. 测试

   ```java
   @Test
   public void test() throws IOException {
       InputStream is = Resources.getResourceAsStream("mybatis-config.xml");		// 读取mybatis核心配置文件
       SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);		// 生成SqlSessionFactory
       SqlSession sqlSession = factory.openSession(true);							// 通过SqlSessionFactory获取SqlSession
   
       UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
       List<User> users = mapper.selectUser();
       for (User user : users) {
           System.out.println(user);
       }
   
       sqlSession.close();
   }
   ```

   结果：

   ![image-20210810160654124](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210810160654124.png)	



### 12.3 mybatis-spring 整合方式一

![image-20210810170040936](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210810170040936.png)	

1. 在Spring中配置mybatis：编写数据源配置、SqlSessionFactory、SqlSessionTemplate

   > spring-dao.xml

   ```xml
   <!--Datasource: 使用Spring的数据源替换Mybatis的配置
       可以用第三方的（c3po、druid..），这里使用Spring提供的JDBC
   -->
   <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
       <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
       <property name="url" value="jdbc:mysql://localhost:3306/mybatis_test?serverTimezone=CST&amp;useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
       <property name="username" value="root"/>
       <property name="password" value="111111"/>
   </bean>
   
   <!-- sqlSessionFactory -->
   <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
       <property name="dataSource" ref="dataSource"/>
       <!-- 绑定Mybatis配置文件。其实不用绑定，Spring这个xml中可以配置所有mybatis的属性，这边只是展示用法-->
       <property name="configLocation" value="classpath:mybatis-config.xml"/>
       <property name="mapperLocations" value="classpath:mapper/Usermapper.xml"/>
   </bean>
   
   <!-- SqlSessionTemplate : 就是之前使用的sqlSession -->
   <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
       <!-- 只能使用构造器注入，因为它没有setter（看源码） -->
       <constructor-arg index="0" ref="sqlSessionFactory"/>
   </bean>
   ```

2. 需要给接口加实现类 UserMapperImpl.java【多的一步】

   ```java
   public class UserMapperImpl implements UserMapper{
   
       // 原来所有的操作都用 sqlSession 来执行，现在都用 SqlSessionTemplate
       // sqlSession不用我们自己创建了，Spring来管理
       private SqlSessionTemplate sqlSession;
   
       public void setSqlSession(SqlSessionTemplate sqlSession) {
           this.sqlSession = sqlSession;
       }
   
       @Override
       public List<User> selectUser() {
           UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
           return userMapper.selectUser();
       }
   }
   ```

3. 将实现类注入到Spring，并整合配置文件

   > applicationContext.xml

   ```xml
   <import resource="spring-dao.xml"/>
   
   <bean id="userMapper" class="mapper.UserMapperImpl">
       <property name="sqlSession" ref="sqlSession"/>
   </bean>
   ```

4. 测试

   ![image-20210810171354285](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210810171354285.png)	

   结果：

   ![image-20210810160654124](https://gitee.com/StanAugust/picbed/raw/master/img/image-20210810160654124.png)	



### 12.4 mybatis-spring 整合方式二

<img src="https://gitee.com/StanAugust/picbed/raw/master/img/image-20210810172628421.png" alt="image-20210810172628421" style="zoom:90%;" />



1. 修改接口实现类

   ```java
   public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
       @Override
       public List<User> selectUser() {
           //SqlSession sqlSession = getSqlSession();
           //UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           //return mapper.selectUser();
   
           return  getSqlSession().getMapper(UserMapper.class).selectUser();
       }
   }
   ```

2. 在Spring中注册

   ```xml
   <bean id="userMapper2" class="mapper.UserMapperImpl2">
       <!-- 该类本身无需注入，但是继承的SqlSessionDaoSupport类需要注入-->
       <!-- 如果采用这种方式，那么spring-dao.xml中的SqlSessionTemplate也无需配置-->
       <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
   </bean>
   ```

   

### 12.5 小结

整合到spring以后可以完全不要mybatis的配置文件，由Spring来管理SqlSessionFactory以及SqlSessionTemplate。

比起方式1 , 方式2不需要管理SqlSessionTemplate , 而且对事务的支持更加友好 （可跟踪源码查看）

除了这些方式之外，还可以使用注解来实现整合，等后面学习SpringBoot的时候进行测试

