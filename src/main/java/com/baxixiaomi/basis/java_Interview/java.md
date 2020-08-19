# java

## 基础

### 基本数据类型

- 基本数据类型对应的包装类型是有缓存的，缓存大小是【-127，128】，当初始化的值在这个范围内，会从这个缓存中获取对象，只有超过这个范围之后，才会创建新的对象，类型于字符串的常量池
- 使用float，double数据类型进行计算时，可能存在精度缺失的问题，可以使用BigDecimal,需要注意的是在创建BigDecimal对象时，需要传入的时字符串，否则也有可能精度缺失。

### 字符串

- String

	- 被final修饰的类，不可修改，在修改对象时，会重新创建对象

- StringBuilder

	- 继承于AbstractStringBuilder，线程不安全，初始容量16，调用的父类的构造方法new Char[capacity]，修改对象时是在原来的对象上直接修改

- StringBuffer

	- 继承于AbstractStringBuilder，线程安全，初始容量16，调用父类的构造方法new Char[capacity],修改对象时在原来的对象上直接修改

### 线程

- 线程的状态

	- 初始状态(new)
	- 可运行状态(start)
	- 运行状态(running)
	- 阻塞状态(获取不到锁)
	- 等待超时状态(sleep超过规定时间)
	- 等待状态(sleep,wait timeout)
	- 死亡

### 集合

- Collection

	- List

		- ArrayList

			- 1.基于object[]数组实现的;2.初始容量为10;3.由于构造的数据结构的问题，导致查询快，增删慢;4.线程不安全的;5.实现了RandomAccess接口，导致循环时首先考虑普通for循环

				- 三种构造方法

					- 无参

						- 实际上是初始化了空的数组，只有当给集合中添加元素时，才会将集合的容量扩展为10

					- 有参，即指定大小

						- 当使用add方法扩容之前，建议使用JDK的ensureCapacity的方法，可以减少程序运行的时间。

					- 传入Collection对象

				- 扩容方法

					- grow方法

						- 当集合中的元素超过10的时候，此时调用ensureCapacityInternal(oldSize+1),此时需要的最小容量为oldSize+1，调用grow方法，扩容的大小为oldSize+oldSize>>1，即为原数组的1.5倍(原大小为奇数时，只保留整数位)，当这个数值超过Integer.MAX_VALUE - 8,是，则置为最大Integer.MAX_VALUE，否，则置为Integer.MAX_VALUE - 8。最终调用Arrays.copyOf(原数组，新容量)进行扩容


		- LinkedList

			- 1.基于双向链表实现的；2.增删快，查询慢;3.线程不安全的;4.未实现RandomAccess接口，循环时，首先考虑的是迭代器的方式，不能使用普通for

		- Vector

	- set 
	    
	    - HashSet通过的是hashcode()和equal()方法完成容器中元素的唯一性

- Map

	- HashMap

		- 1.线程不安全；2.初始容量为16；3.支持Null的键；4.当不指定初始容量，扩容是扩张为原来的2倍，当指定初始容量，扩容是扩张为2的幂次方的大小(即当初始化时给定3，实际上初始化出来的map容量为4->2^2，给定的大小为23，实际上初始化出来的容量为32->2^5)

			- 在jdk1.8之后，HashMap在解决hash冲突的时候，采用的是红黑树。当链表的长度大于8时，会将链表转换为红黑树。当链表长度小于6时，会将红黑树转换为链表，但是在数组的长度未超过64时，只是增加数组的容量，而不会转换

	- HashTable

		- 1.线程安全(但只是将所有的方法粗鲁的加上synchronized)2.初始容量为11；3.当不指定初始容量时，扩容是扩展为原来的2n+1，当指定初始大小，是扩容为指定的大小

	- currentHashMap

		- 1.线程安全，替代掉了HashTable。实现的原理的是将数组分组，分组进行操作,对每个分组进行加锁操作,这样在多线程操作的环境下,可以实现不同线程访问不同的数据

	- map

		- HashMap和HashTable的区别

			- 1.HashMap是线程不安全的，HashTable是线程安全的
			- 2.HashMap的初始容量是16，HashTable的初始容量是11
			- 3.当不指定初始容量时，HashMap的扩容是扩大为原来的2倍，HashTable的扩容是扩大为原来的2n+1
			- 4.当指定初始容量，HashMap的扩容是扩大为2的幂次方倍(通过tableSizeFor方法实现)，HashTable的扩容就是扩大到指定的大小

		- HashMap的底层实现

			- 1.HashMap的最大容量是1<<30
			- 2.jdk1.8之前是通过数组+链表实现。jdk1.8之后通过数组+链表/红黑树实现。key值经过扰动函数(key.hashcode()^key.hashcode>>>16)得到hash值.然后通过（n-1）&hash计算index值，如果当前位置存在元素，判断key和hash是否相等，如果相等则直接覆盖，如果不相等，通过拉链解决(是在数组的基础上添加链表)
			- 3.HashMap的扩容，扩容的原因是当某一个数组节点下的链表过多，哈希冲突增多，造成数据的查询速度减慢，(时间复杂度为O(n))。在put元素时，当前数组的大小超过阀值(初始容量*负载因子)通过resize()方法实现扩容

				- resize方法

					- 当旧容量>0

						- 当原数组长度> 1 << 30,将阀值设置为最大
						- 当新容量(旧容量的2倍)小于1<<30且旧容量>16，新的阀值为旧阀值的2倍

					- 当旧容量=0，且旧阀值>0

						- 新容量为旧阀值

					- 当旧容量=0，旧阀值=0

						- 新容量为16，新阀值为16*0.75f

					- 当新表的阀值=0

						- 新的阀值=ft/Integer.MAX_VALUE

			- HashMap的线程问题

				- HashMap在jdk1.7之前，主要的线程问题是：出现死循环。问题是因为，当两个线程同时put操作时，出现扩容的情况，因为rehash方法的存在，容易形成环形链表，所以造成死循环。在jdk1.8中出现线程问题，可能是因为红黑树的根节点被双方同时持有，造成死循环

		- concurrentMap和HashTable的区别

			- 主要的区别在于线程安全的问题上

				- 1.底层数据结构不同，concurrentMap的结构是数组+链表/红黑树，hashtable的结构是数组+链表
				- 2.实现线程安全的方式不同。

					- concurrentMap在jdk1.7之前是通过segment概念实现的，即将容器中的数据分组，对于各个分组进行加锁，所以当多线程在访问前一段的数据不影响后一段数据被其他线程访问。jdk1.8开始，通过的就是数组+链表/红黑树的数据结构实现的，对于每一个根节点进行加锁。通过的是synchronized关键字和CAS原理进行并发操作
					- HashTable只是简单粗暴的在每一个方法上加synchronized关键字进行操作，效率极低

## 进阶

### 反射

- 反射可以在运行状态下，通过Class对象获取任意类中的方法和属性

	- Class class = TargetClass.class
	- Class class = Class.forName()

- 优点：可以提高代码的灵活性，动态的加载类
- 缺点：实际上是通过向JVM解释执行，容易出现性能瓶颈，安全问题
- 运用的场景：1.spring的IOC(依赖注入)和AOP(动态代理)2.JDBC数据库的连接技术


## jvm

### java内存区域详解

- 运行时的数据区域

	- 线程共享

		- 堆

			- 存放对象实例；也是GC的重点区域

		- 方法区

			- 存放的是已经被虚拟机加载的常量，静态变量，类等 
			- 方法区和永久代的关系，方法区更像是一个规范，而永久代是规范的实现

	- 线程私有

		- 程序计数器

			- 记录线程的执行情况，保证下一次线程切换回来可以断点执行

		- 本地方法栈

			- 主要是为了虚拟机的Native方法服务

		- 虚拟机栈

			- 由很多栈帧构成，每个栈帧由局部变量表，操作数栈，动态链接等构成。局部变量表中存放了编译器可知的数据类型

### 虚拟机对象探秘

- 对象的创建(类加载检查->分配内存->初始化零值->设置对象头->执行init方法)

	- 类加载检查

		- 当new对象时，首先会在常量池中查找这个对象的引用是否存在，并且判断这个类是否经过的了初始化。如果没有，则需要先进行类的初始化

	- 分配内存

		- 实际上就是将内存中的一部分区域抠出来。分配方式分为两种，指针碰撞和空闲列表。在并发的情况下，通过CAS+失败重试/TLAB机制实现线程安全

			- 指针碰撞

				- 适用于java堆内存比较规整的情况

			- 空闲列表

				- 适用于java堆内存比较凌乱的情况

	- 初始化零值
	- 设置对象头
	- 执行init方法

### 垃圾回收

- 堆内存常见的分配策略

	- 一般对象创建分配都在Eden区域 
	    
	    - 大多数的对象都在此区域进行分配，当Eden区域没有足够的空间，将会发起一次Minor GC 
	    
	- 大对象直接进老年代

		- 大对象如字符串，数组

	- 超过年龄阀值的进永久代/长期存活的对象进入老年代 
	
	    - 在Eden区域的对象，经过一个Minor GC后，仍然存在，那么将对象的年龄+1，当年龄超过阀值，则进入老年代

- 对象死亡

	- 判断对象死亡的方式

		- 引用计数法

			- 当被引用，数量加一。这种方式简单高效，但是存在互相循环引用的问题

		- 可达性分析算法

			- 当一个对象到GC ROOTS时没有任何引用链，则可以回收

	- 引用分类

		- 强引用

			- 一般不回收

		- 软引用

			- 内存不够则回收

		- 弱引用

			- 发现即回收

		- 虚引用

			- 只是用来记录GC活动

- 垃圾回收算法

	- 标记-清理

		- 比较出需要回收的对象，在标记完成后统一回收所有被标记的对象。存在效率太慢，空间问题(会产生内存碎片的问题)

	- 标记-整理

		- 与“标记-清理”类似，标记完成后，将所有的存活的对象向一端移动，然后清理掉边界以外的内存

	- 复制

		- 将内存划分为两块相等的区域，当其中一个内存使用完成，将还存活的对象复制到另一块内存中，然后将使用过的空间一次性清理

	- 分代收集算法

		- 对于新生代和老年代采用不同的算法。新生代多采用复制的算法，老年代多采用标记-清理的算法

- 垃圾回收器

	- serial收集器(串行收集器)

		- 单线程的收集器，在工作时，需要将所有的应用程序停止。新生代复制，老年代标记-整理

	- parnew收集器

		- 多线程版本的serial收集器

	- parallel scavenge收集器

		- 采用复制算法的收集器，关注点是cpu的吞吐量

	- CMS收集器

		- 真正的并发收集器，获取最短的回收停顿时间为目标的收集器。优点是：停顿时间短，并发收集。缺点是：对cpu资源敏感，会有内存碎片

### 类的加载

- 类的生命周期

	- 加载-连接-初始化-使用-卸载(验证-检查-解析为连接中的步骤)

- 类的加载

	- 分类

		- JVM中内置了三个ClassLoader，除了BootstrapClassLoader以外其余均继承java.lang.classloader

			- BootstrapClassLoader：启动类加载器
			- ExtensionClassLoader：扩展类加载器
			- AppClassLoader：应用程序类加载器

	- 双亲委派加载

		- 主要是自顶向下的尝试加载类，自底向上的检查类是否被加载

- 一个main方法的执行步骤

	- 编译好的java文件，通过javac获得class文件，通过类加载器加载到内存中(将类的信息加载到方法区中)
	- JVM找到程序的主入口，执行main方法
	- 当看到main方法中存在new关键字，会先去方法区中的常量池中检查是否存在对应的类的引用，如果不存在，那么先要进行类的加载
	- 加载完成后，JVM会给该对象分配一个内存，然后调用对应对象的构造方法初始化对象实例，这个实例持有指向方法区中对应类的类型信息的引用
	- 执行对应的方法是，JVM根据对象引用找到对象，根据对象持有的引用定位找到虚拟机栈中的方法表，找到对应的方法
	- 执行方法

### JVM相关参数

- 显式指定堆内存

	- -Xms：最大堆内存
	- -Xmx：最小堆内存

- 显示指定新生代内存

	- -XX:NewSize=
	- -XX：MaxNewSize=
	- -Xmn

- 显示指定老年代/元空间

	- 老年代

		- -XX:PermSize=
		- -XX:MaxPermSize=

	- 元空间

		- -XX:MetaSpaceSize=
		- -XX:MaxMetaSpaceSize=

### GC调优

- GC调优实际上就是通过设置JVM相关参数，选择不同的垃圾回收器来完成。真正的调优是需要通过代码调优
- GC调优的目的：将转移到老年代的对象减少到最低，减少GC执行的时间
- GC调优的策略：增大新生代的大小；设置大对象直接进入老年代的阀值等

## 运维

### 常见的java性能问题分析

- 常用的命令

	- top命令：可以查看当前系统中cpu，内存，硬盘的使用情况
	- vmstat命令：统计内存，cpu，swap的使用情况
	- jstack命令：线程的分析命令，可以通过查询某一个进程的id，将id转换为16进制(pringf '%x' id)，然后通过命令jstack pid | grep -A 20 转换后的id，查看线程的运行情况，可以清楚的看到线程的状态，出现问题的代码行数。其实也就是获取dump文件

## 多线程

### 多线程常见的面试题

- 进程和线程的区别

	- 一个进程可以拥有多个线程
	- 多个线程之前可以共享堆和方法区，但每一个线程都有自己私有的程序计数器，虚拟机栈，本地方法区栈

- 并发和并行的区别

	- 并发：同一时间内，多个任务执行
	- 并行：单位时间内，多个任务执行

- sleep和wait的区别

	- 1.sleep是Thread的方法，在睡眠时间到后等待cpu资源后开始执行，不释放锁资源，只释放cpu资源。wait方法是object的方法，既释放锁资源也释放cpu资源，需要通过notify方法来唤醒进入可调度状态。

- 线程死锁

	- 1.互斥条件：线程需要的资源在任意时刻只能被一个线程所拥有
	- 2.请求与保持：某一个线程在请求另一个资源的同时，不释放当前持有的资源
	- 3.不剥夺：线程所持有的资源不能被另外一个线程所剥夺
	- 4.循环等待：若干线程之间需要的资源形成一种头尾相连的状态，等待资源

- synchronized和ReentrantLock区别

	- 1.两者都是可重入锁
	- 2.synchronized依赖的是JVM，ReentrantLock依赖的是Java的API
	- 3.ReentrantLock提供三个高级功能：等待可中断，当线程进入等待锁的状态，可以中断这种状态，通过lock.lockInterruptibly()实现；可以指定为公平锁，synchronized只能是非公平锁，ReentrantLock可以通过指定构造方法来实现公平锁；提供了等待唤醒的机制。synchronized修饰的线程，通过notifyAll唤醒的是所有的处于睡眠状态的线程，但是ReentrantLock通过condition的方式，将线程注册，通过singAll方法，只唤醒注册在该condition上的线程，其余的线程并不唤醒

- 并发编程的三个关键特性

	- 1.原子性：线程对于一个操作，要么一定执行完成，要么不执行，不存在执行一半退出。
	- 2.可见性：当一个线程修改了某一个变量，其余线程要立刻知道变量的改动，通过volatile关键字实现
	- 3.有序性：代码在执行的过程中的先后顺序，特别是初始化的时候，可以通过volatile关键字来实现

- ThreadLocal的作用

	- 对于类中的某一个变量，或者说多线程环境中共享的资源，被ThreadLocal修饰，那么当多线程访问该资源时，实际上访问的是该资源的副本，对于原数据不会产生影响，从而避免了线程安全问题

		- 源码实现，ThreadLocal实际上不存储数据，存储数据的是内部维护的ThreadLocalMap，他将ThreadLocal作为key，将需要保存的值作为value存储在这个map中。需要注意的是ThreadLocal是弱引用，当它为null时，会被GC掉，此时ThreadLocalMap出现了键值为null的情况，会有内存泄漏的问题. 

- 线程中共享和私有的数据 

    - 共享的数据 
     
        - 堆和方法区 
        
            - 堆：存放的是对象 
            - 方法区：存放的是已被加载的类，变量，常量等
        
    - 私有的数据 
      
        - 程序计数器：为多线程记录当前执行的位置，以便当线程切换回继续执行 
        - 虚拟机栈：每个Java方法创建出的成员变量，操作数栈，常量池引用等(主要的作用就是保护线程中局部变量不被其他线程访问)
        - 本地方法栈：为每个Native方法服务(主要的作用就是保护线程中局部变量不被其他线程访问)

- 线程池

	- 为什么要使用线程池

		- 1.降低资源的消耗
		- 2.提高线程的管理
		- 3.提高响应速度

	- 如何创建线程池

		- 推荐使用ThreadPoolExecutor的构造方法来创建线程池

			- 1.FixedThreadPool：固定数量的线程池
			- 2.SingleThreadExecutor：单一数量的线程池
			- 3.cacheThreadPool：可缓存线程池

		- ThreadPoolExecutor中重要的参数

			- 1.corePoolSize：核心线程数量
			- 2.maximumPoolSize：最大线程数量
			- 3.workQueue:等待队列
			- 4.keepAlivedTime：当无新任务提交且线程数量大于核心线程数量时，如果超过这个时间，则会销毁该线程
			- 5.unit：keepAlivedTime时间的单位
			- 6.threadFactory：executor创建新的线程时需要,可以通过它来指定线程的名称
			- 7.handler：当队列饱和，且线程数量达到最大值时的策略

				- 1.默认策略/AbortPolicy:拒绝新任务
				- 2.callerRunsPolicy：调用启动线程的主线程来执行(常用的策略)
				- 3.discardPolicy：不处理新任务，直接丢弃
				- 4.disCardOldSetPolicy：丢弃最早的但是未处理的任务

	- 线程池的工作原理

		- 提交任务

			- 当工作的线程数< corePoolSize

				- 创建线程，执行任务

			- 当工作的线程数> corePoolSize

				- 判断等待队列是否已满

					- 不满

						- 添加进队列中，等待线程执行

					- 满

						- 判断当前工作的线程是否为最大的线程数

							- 否

								- 创建线程，执行任务

							- 是

								- 执行饱和策略

	- 几种常见的线程池

		- FixedThreadPool

			- 可重用固定线程池的线程池。通过指定corePoolSize，maximumPoolSize为固定值，使用LinkedBlockingQueue阻塞队列来创建线程池，由于LinkedBlockingQueue的最大容量为Integer的最大值(在不指定大小的情况下)，可能存在OOM

		- SingleThreadPool

			- 只有一个线程的线程池。通过指定corePoolSize，maximumPoolSize的大小为1，使用的队列为LinkedBlockingQueue来创建线程池。由于LinkedBlockingQueue的最大容量为Integer的最大值(在不指定大小的情况下)，可能存在OOM

		- cachedThreadPool

			- 根据需要创建新线程的线程池。通过指定corePoolSize为0，maximumPoolSize为Integer最大值来创建线程池，由于maximumPoolSize为Integer的最大值，所以可能出现cpu资源耗尽等问题

		- scheduledThreadPoolExecutor

			- 定时任务线程池。实际上还是通过ThreadPoolExecutor创建。指定corePoolSize的大小，maximumPoolSize大小为Integer的最大值，使用的是DelayedWorkQueue阻塞队列(默认容量是16，此队列会对队列中的任务进行排序，执行所需要时间较短的先被执行)

- jdk提供的并发容器

	- concurrentHashMap

		- 线程安全的HashMap，在读的操作时，不用加锁，在写的操作时，因为分段锁的概念，不影响其他线程对其他数据段的访问

	- copyOnWriteArrayList

		- 线程安全的ArrayList，在读多写少的场景非常好用，通过底层的数组结构，完成对原数据的拷贝，修改副本，然后将副本拷贝回原数据，通过Arrays.copyOf方法实现，在写数据的时候使用的ReentrantLock

	- concurrentLinkedQueue

		- 非阻塞队列，提供了poll，take方法获取数据

	- concurrentSkipListMap

		- 跳表，分层的概念，提高了查询的速度,有点像二分查找法 

- jdk提供的队列 

    - 阻塞队列 ：当队列是空的时候，从队列中获取元素被阻塞；当队列满的时候，往队列中添加元素被阻塞
        
        - BlockingQueue，通过加锁实现线程安全 
        
        - ArrayBlockingQueue: 
            
            - 底层使用的是数组实现 
            - 创建时需要指定队列的大小 
            - 并发操作时，采用的是ReentrantLock
        
        - LinkedBlockingQueue 
            
            - 基于单向链表实现 
            - 与ArrayBlockingQueue比较，有比较高的吞吐量，无参的构造方法创建的队列大小是Integer.MAX_VALUE，建议在创建时指定大小
             
        - priorityBlockingQueue 
            
            - 支持优先级的无界阻塞队列，创建时可以指定初始大小，后面插入元素。若空间不足，可以实现自动扩容 
             
        - concurrentSkipListMap 
        
            - 跳表，分层的概念
        
    - 非阻塞队列 
        
        - concurrentLinkedQueue，通过CAS实现线程安全

- shutdown和shutDownNow的区别

	- shutdown：线程的状态是shutdown，不再接收新的任务，之前的任务是要处理完的
	- shutDownNow：线程池的状态是stop，终止当前的任务，并停止处理排队的任务，返回正在等待的list

- 创建线程池的大小判断

	- 根据任务的类型来区分

		- IO密集型

			- 系统大量的时间都在处理IO。经常有网络读取，文件读取，一般设置为2n(n为cpu核心)

		- cpu密集型

			- 利用cpu的计算能力的任务，比如在内存中进行大量的排序，一般设置为n+1

- 乐观锁和悲观锁

	- 乐观锁

		- 认为所有的线程都不会修改资源，只有在更新的时候判断在执行期间是否有其他线程修改资源。适合读操作比较多的场景

			- 通过加版本号
			- CAS(compare and swap)

		- 乐观锁存在的问题

			- ABA问题(两个线程，当其中一个线程正在对变量A操作，另外一个线程也在对变量A操作，操作为B，然后又修改为A。第一个线程在操作完成后发现变量并未发生改变，但是实际上发生改变了)
			- 循环时间过长(CAS中，需要判断当前的值是否和预期的相同，如果不同，则会循环等待，造成时间过长) 
			- 只能保证一个共享变量的原子操作(CAS只对单个共享变量有效，当操作涉及多个变量时，CAS无效。在JDK1.5之后，提出了AtomicReferences类来保证引用对象之间的原子性，可以把多个共享变量放在一个对象中操作)

	- 悲观锁

		- 认为所有的线程都会去修改资源，所以每次操作都会上锁。适合写操作比较多的场景 
		 
- synchronized 
    
    - synchronized关键字可以保证被它修饰的方法或代码块在任意时刻只有一个线程可以访问，
    - synchronized修饰静态方法时，实际上是修饰静态方法所在的类，因为静态的方法或变量不属于任何一个实例对象，是类成员。 
    - synchronized不要使用synchronized(String string)因为String会有缓存的存在 
    - synchronized关键字实际上是通过monitorEnter和monitorExit两个指令实现的。
     
- synchronized和volatile的区别 

    - volatile关键字只能作用的成员变量上，synchronized可以作用在方法等 
    - volatile关键字不会产生阻塞效果，synchronized会产生 
    - volatile可以保证数据的可见性(即当数据被改变，其他线程也是知道的)，但无法保证原子性，synchronized两者都可以 
    - volatile主要解决的是线程中变量的可见性，synchronized主要解决的是线程中变量的同步性
    

- AQS（abstract queue synchronized）

	- 原理：当一个线程请求空闲资源时，会将这个线程设置为有效线程，并将资源加锁。当其他线程请求这个资源时，会将这个线程加入阻塞队列中，并且需要对应的唤醒锁分配机制。这个可以通过CLH队列实现。这是一个虚拟的双向队列，仅仅只是存在节点之间的关联关系

## BIO,NIO,AIO

### 实际上是java语言对操作系统的各种IO模型的封装，不需要关心不同的操作系统，只需要关心java的api。同步与异步，阻塞与非阻塞

- BIO(Block IO)

	- 同步阻塞的io，数据的读写必须是阻塞在同一个线程中
	- 传统的BIO。服务端通过维护一个while(true)来监控客户端的请求，当一个客户端发来请求，此时服务端和客户端建立连接，当其他客户端发来请求时，服务端无法处理。可以通过在服务端维护多个线程，即当一个客户端发来请求，便开始创建线程。这种操作会在高并发的状态下，造成服务端的宕机。
	- 伪异步IO。伪异步io实际上是对传统io的优化。在服务端通过维护一个队列和线程池来完成客户端的多个请求的处理，当客户端发来连接请求，将请求封装成Task放进队列，通过线程池来完成响应

- NIO(NEW IO)

	- 同步非阻塞io。jdk1.4之后引入Channel，buffer，selector等抽象
	- NIO是面向Buffer的，基于通道的io操作方法
	- 提供了与传统socket，serverSocket相对应的socketChannel和serverSocketChannel。
	- NIO和IO的区别

		- NIO是面向Buffer的，IO是面向流的
		- NIO是直接将数据读取到Buffer中，IO是先将数据读取到流中，然后再到Buffer
		- NIO是非阻塞的，IO是阻塞的
		- NIO是通过channel完成双向的读写，io是通过流实现单向的读写
		- NIO有选择器，IO没有

	- JDK的原生NIO存在的问题

		- JDK原生NIO是通过epoll实现，且编程实现复杂，bug频繁发生。所以演化出Netty框架

- AIO(Asynchronous io)

	- 异步非阻塞io，实际上是NIO2.使用率不高

*XMind - Trial Version*