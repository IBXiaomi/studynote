# 时光科技
公司主要是做硬件，是做软硬件结合的，需要懂蓝牙，wifi协议等 

### sql编写:一个team表，里面只有一个字段name，一共有4条记录，分别是a,b,c,d。对应四个战队，现在四个球队进行比赛，用一条SQL表示显示所有可能的结果 
    select * from team a ,team b where a.name = b.name; 
### mongdb与mysql的区别 
    最主要的区别是mongdb数据库中是collection的概念，存储的数据是JSON格式
    mysql数据库是表的概念，存储的数据可以是各种类型 
# 杭州鸿泉物联网 
1.trim动态标签的使用方法 
	
	动态标签，trim，有四个属性，分别是 
	prefix ：前缀，给SQL语句添加的前缀
	suffix ：后缀，给SQL语句添加的后缀
	prefixesToOverride ：去除sql语句前面的关键字或者字符，该关键字或者字符由prefixesToOverride属性指定，假设该属性指定为”AND”，当sql语句的开头为”AND”，trim标签将会去除该”AND”
	suffixesToOverride ：去除sql语句后面的关键字或者字符，该关键字或者字符由suffixesToOverride属性指定 
	例如：
	<insert id="insertSysUser" parameterType="com.mydemo.entity.SysUser">
        insert into sys_user
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="id != null and id != ''">
                id,
            </if>
            <if test="username != null and username != ''">
                username,
            </if>
            <if test="realName != null and realName != ''">
                real_name,
            </if>
            <if test="idcard != null and idcard != ''">
                idcard,
            </if>
            create_time
        </trim>
        <trim prefix="values(" suffix=")" suffixOverrides=",">
               <if test="id != null and id != ''">
                #{id, jdbcType = VARCHAR},
            </if>
            <if test="username != null and username != ''">
                #{username, jdbcType = VARCHAR},
            </if>
            <if test="realName != null and realName != ''">
                #{realName, jdbcType = VARCHAR},
            </if>
            <if test="idcard != null and idcard != ''">
                #{idcard, jdbcType = VARCHAR},
            </if>
            now()
        </trim>
    </insert> 

	最终的结果就是：insert into sys_user ( id, username, real_name, idcard, create_time ) values( ?, ?, ?, ?, now() ) 

2.ApplicationContextAware接口的作用 

	ApplicationContextAware接口的作用：就是获取Spring的配置中所有的bean对象。通过setApplicationContext(ApplicationContext)方法，将ApplicationContext对象加载。获取所有的bean。 
	ApplicationContext：这个接口，有两个常用的实现类。分别是ClassPathXmlApplicationContext和FileSystemXmlApplicationContext。通过将Spring配置文件的路径作为参数传递，获取对应的对象，加载所有的Bean对象。 
	除了ApplicationContextAware接口，经常使用的xxxAware类型的接口还有：BeanFactoryAware，BeanNameAware两个接口。
	BeanNameAware接口：通过setBeanName(String)方法，获取bean的名称，传递的是配置文件中的bean的id值
	BeanFactoryAware接口：通过setBeanFactory(BeanFactory)方法，传递是BeanFactory对象，让Bean获取配置他们的BeanFactory的引用。

3.如何在项目启动时将一些配置加载 
	
	目前查的方式有：
	1.通过注解@PreConstuctor 
	2.通过在Spring的配置文件中添加init-method标签，指定相应的方法
	3.还有一种待确认，实现InitializingBean接口，调用afterPropertiesSet()方法，可以在Spring初始化完成后自动调用该方法，实现预加载文件 

4.SpringBoot项目如何远程调试 

	1.idea中实现远程调试
	新建Remote，修改启动参数：-Xdebug -Xrunjdmp:transport-dt_socket,server=y,suspend=n,address=5005
	

	启动项目时，使用如下命令：java -jar Xdebug -Xrunjdmp:transport-dt_socket,server=y,suspend=n,address=5005 xxx.jar
	
	这样就可以正常远程debug了

5.项目中遇到的比较棘手的问题 

	1.解释了采集机升级过程中遇到的命令执行导致升级阻塞的问题。
	在升级过程中使用到了unzip命令来执行解压升级包，由于压缩包大小的问题，导致unzip命令输出的信息将缓存充满，使得命令无法继续执行。
	解决版本：将错误信息和正确的信息分别输出到不同的位置。并在升级中加判断，当升级超过时间限制，自动的停止升级进程。

6.补充：Spring的初始化过程 
	
	1.初始化bean 
		BeanFactory:懒加载，只有当使用对应的Bean时，才会进行初始化
		ApplicationContext：启动结束后，会通过BeanDefinition对象中的信息，实例化所有的bean
	2.设置对象属性 
		实例化后的对象装在BeanWrapper对象中，根据BeanDefinition中的信息完成依赖注入(构造方法，setter注入，注解注入)
	3.处理Aware接口 
		BeanNameAware：通过setBeanName(String)方法，获取bean的名称，传递的是配置文件中的bean的id值 
		BeanFactoryAware接口：通过setBeanFactory(BeanFactory)方法，传递是BeanFactory对象，让Bean获取配置他们的BeanFactory的引用。
		ApplicationContextAware接口的作用：就是获取Spring的配置中所有的bean对象。通过setApplicationContext(ApplicationContext)方法，将ApplicationContext对象加载。获取所有的bean
	4.initalizingBean接口和init-method
	5.BeanPostProcessor
	6.DisPosableBean 
		当Bean不再需要的时候，通过实现该接口，调用destory()方法。
	7.在Spring的配置文件中添加destory-method
		自定义销毁方法。


# 中软ODC线
1.AOP的理解 
    
    1.AOP是Spring框架重要的特性之一，动态代理，可以让开发人员专注于业务代码的实现，不需要关注其他一些代码，比如日志管理，事务管理等 
    2.AOP是动态代理，有两种实现方式，一种是通过Cglib实现，一种是通过JDK自己实现。两种实现方式的区别如下 
        Cglib方式：
            1.Cglib可以通过实现接口也可以通过继承的方式来实现 
            2.代理类InvocationHandler需要实现MethodInterceptor接口来完成代理 
            3.使用步骤
                创建Enhancer对象
                设置需要代理对象    setSuperClass
                设置Handler        setCallback
                创建代理对象        enhancer.create()
        JDK方式：
            1.必须实现InvocationHandler接口，使用Proxy.newProxyInstance产生代理对象
            2.使用步骤：
                直接通过Proxy类的静态方法newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
                其中ClassLoader是选择一个对象的loader
                interface：要代理的接口
                InvocationHandler:即自己定义的handler

2.redis的数据结构 
    
    常见的redis的数据结构是：
        String,list,hash,set,zset
    比较少见的数据结构是：
        GeoHash(可以用来做地图查找周围的人),是一种算法的思想
        HyperLogLog:一种算法，并非redis独有，目的是做基数统计，不是集合，不会保存元数据，只记录数量而不是数值
        bitMap:本身并不是一个数据结构，实际上就是字符串，但是可以对字符串进行位操作
        BloomFilter(布隆过滤器)

3.redis分布式锁，如何解决redis中并发竞争key的问题 
    
    1.首先先来了解下分布式
        由于当前的系统越来越庞杂，如果将所有的服务放在同一个机器上，那么当一个服务出现问题，其他服务也会出现问题。因此分布式诞生
    2.分布式理论
        因为不同的服务存在于不同的机器，那么就不得不考虑数据的最终一致性的问题，因此会有分布式事务(两阶段提交)，分布式锁的概念
    3.分布式锁
        需要保证一个方法在同一时间内只能被同一个线程执行。
    4.分布式锁的目的
        可以保证在分布式部署的集群中，同一个方法在同一时间只能被一台机器上的一个线程执行
        这把锁需要是一个可重入锁
        这把锁需要是一个阻塞锁(根据业务需求)
        这把锁需要是一个公平锁(根据业务需求)
        有高可用的获取锁和释放锁功能
        获取锁和释放锁的性能要好
    5.基于redis实现分布式锁
        使用命令setnx,expire,delete来实现分布式锁。setnx命令实际上是set if not exists。当锁存在时返回0，当锁不存在返回1。
        原理是，当锁不存在时，使用setnx命令完成加锁，通过expire命令完成锁的过期时间设置，当使用完成使用delete命令删除。但是这种方式存在一个问题就是setnx和expire不是一个原子操作，有可能会导致死锁
        所以redis官方文档给出一个setex的命令，可以实现加锁和过期时间为原子操作。
        
        官方文档另外给出一个命令jedis.set(key value [EX seconds|PX milliseconds] [NX|XX] [KEEPTTL])
        EX seconds-设置指定的到期时间，以秒为单位。
        PX milliseconds-设置指定的到期时间（以毫秒为单位）。
        NX-仅在不存在的情况下设置密钥。
        XX-仅设置密钥（如果已存在）。
        KEEPTTL-保留与密钥关联的生存时间。
        如果设置成功则会返回OK。
        redis官方指出set命令后面可能会替代setex，setnx等命令
        
        delete命令需要结合lua脚本来完成删除
        ```java
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        ```

4.kakfa如何保证消息的顺序，保证消息的不重复消费 

5.redis做过内存的优化吗 

6.单点登陆，JWT 

7.服务端如何完成跨域 

8.AIO,NIO 

# Objectiva 
第一轮面试 

1.手撕单例和spring的单例有什么区别 

2.spring默认的单例是如何实现的 

3.Arraylist的初始大小，扩容 

4.redis如何保证数据的顺序 

5.kafka的partional的选主策略 

6.redis的选主策略 

7.偏向锁 

8.函数式编程 

9.jvm的垃圾回收 

10.hash的扩容 

11.最近有看过什么完整的书籍 

12.职业规划 

第二轮面试 

1.mysql的组成 

2.枚举如何实现单例 

3.cap理论，base理论，结合实际项目

4.介绍项目的大概架构情况 

5.为什么要使用redis

# 中软汇丰线 
1.JQuery的选择器 

2.反射的理解 

3.动态代理的理解 

4.spring和springboot的优点 

5.springboot如何完成spring的配置文件加载，即如何帮助程序人员减负

6.@Configuration注解在springBoot的那个版本开始使用

7.@SpringBootApplication注解是由那几个注解构成 

8.Tcp/Ip

9.servlet

10.jsp的内置对象，request和session的区别

11.重定向和转发的区别

12.linux相关知识

13.springmvc的请求流程

14.redis单点登录的实现

15.fastdfs的原理

16.队列的理解

17.javaBean中如何实现一个属性的排序，compareTo

18.SSM的配置文件如何整合

19.安全框架

20.责任链模式

21....  

# 维谛技术 

第一轮面试 

1.关系型数据库和非关系型数据库的区别 

2.maven的生命周期 

3.阐述自己项目的工作流程 

4.前端框架了解多少 

第二轮面试 

1.大批量数据更新的思路或者说是怎么进行sql优化 

hr面试 

1.为什么要从上家公司离职 

2.谈一谈自己在之前的公司中遇到的比较难的问题，如何解决的，有什么收获 

3. 期望薪资，如果低于期望薪资怎么办 

4.之前的工作氛围如何，对于下一份工作有什么要求




