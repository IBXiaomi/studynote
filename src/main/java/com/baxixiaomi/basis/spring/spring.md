# spring

## 概述

### spring框架通常指的是spring-framework，提高开发人员的开发效率以及系统的可维护性。

### spring框架的特性

- 核心技术：依赖注入，面向切面编程，事件，资源，i18n，验证，数据绑定，类型转换
- 测试：模拟对象，spring mvc测试
- 数据访问：事务，DAO支持，JDBC，ORM
- web支持：spring MVC
- 集成：远程处理，JMS,JMX,调度，缓存
- 语言：Kotlin,Groovy,动态语言

## spring的IOC和AOP

### IOC(控制反转 Inverse of control)

- 是一种设计思想，就是将原本需要在程序中手动创建对象的过程交给spring容器来管理。IOC容器实际上就是一个MAP
- IOC容器就像是一个工厂一样，当我们需要创建对象时，只需要在配置文件中添加相应的类的信息，并在使用的地方做好注解即可

### AOP(Aspect-oriented programing)面向切面编程

- 就是在不修改源码的基础上实现原有基础的增强，底层使用的就是动态代理。
- 优势：减少代码，提供程序的可维护性

## 常见的问题

### 1.spring中的单例bean的线程安全问题

- spring中通过singleton注解或spring默认的就是单例。当多线程操作时存在线程安全的问题，
- 解决版本：可以通过ThreadLocal来完成。在类中创建一个ThreadLocal的成员变量，当多线程访问该变量实际上是访问的是变量的副本对变量本身无影响

### 2.spring bean的生命周期：

- 1.bean容器在配置文件中找到所有的需要加载类的定义
- 2.bean容器利用反射技术创建对象实例
- 3.涉及属性值的利用set方法完成设置
- 4.如果bean实现了BeanNameAware接口，调用setBeanName()方法，传入的是bean的名字
- 5.如果bean实现了BeanClassLoaderAware接口，调用setBeanClassLoader方法，传入的是ClassLoader对象
- 6.如果实现了其他的Aware的接口，也会调用相应的方法
- 7.如果有和加载这个bean的spring容器相关的beanpostprocessor对象，执行postProcessBeforeInitialization()方法
- 8.如果bean实现了InitializingBean()接口，那么执行的是afterPropertiesSet()方法
- 9.如果 Bean 在配置文件中的定义包含 init-method 属性，执行指定的方法。
- 10.如果有和加载这个 Bean的 Spring 容器相关的 BeanPostProcessor 对象，执行postProcessAfterInitialization() 方法
- 11.当要销毁 Bean 的时候，如果 Bean 实现了 DisposableBean 接口，执行 destroy() 方法。
- 12.当要销毁 Bean 的时候，如果 Bean 在配置文件中的定义包含 destroy-method 属性，执行指定的方法。

### spring创建对象的过程 ： 

  以5.2.5.RELEASE版本为例
- 1.初始化完成BeanFactory，以ClassPathXml的方式来获取配置文件。实际上最终初始化的是DefaultListableBeanFactory对象，bean相关的信息保存在BeanDefinition对象中，然后通过AbstractBeanFactory类中的doGetBean方法来完成
- 2.检查bean的名称是否合规
- 3.检查缓存中是否存在bean
- 4.检查是否存在循环依赖的问题
- 5.检查是否有其他的父类BeanFactory,且当前的容器中不存在要获取的对象
- 6.标记当前的对象是否被标记，存在一种情况是只做类型检查，并不是创建对象
- 7.获取对象的beanDefinition
- 8.处理依赖的问题，如果A->B,那么首先初始化B
- 9.调用getBean的方法，完成自我的初始化
- 10.如果当前的模式是单例，创建Bean，通过的是doCreateBean方法，利用反射完成对象的创建，并且封装为BeanWrapper

## 分支主题 4

*XMind - Trial Version*