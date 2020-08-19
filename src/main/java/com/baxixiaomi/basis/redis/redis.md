# redis

## 简介

### 简单来说，redis就是一个非关系型的数据库，支持KEY-VALUE的数据，存在于内存中，读写速度比硬盘中的数据快

### 为什么使用redis而不是使用map等数据结构来存储数据：map等数据结构是本地缓存，生命周期随着JVM的销毁而销毁。并且在多实例的情况下，每个本地都需要保存一份，可能存在不一致的问题

### redis和Memcached的区别：

- 1.redis支持更丰富的数据结构，Memcached仅支持基本数据类型和String
- 2.redis的数据可以持久化到外存中，Memcached的数据只能保存在内存中
- 3.redis支持集群模式，Memcached不支持 
- 4.redis是单线程多路IO复用模型，Memcached是多线程，非阻塞IO复用的网络模型

## redis常见的数据结构

### String(字符串)

- 数据结构很像java中的ArrayList
- 最大值位512MB
- 常见的命令：SET,GET,DEL,MGET,MSET,SETEX(SET+EXPTRE)
- 应用场景：微博粉丝数

### List(列表)

- 数据结构像java中的LinkedList
- 常见的命令：LPUSH,RPUSH,LPOP,RPOP.LRANGE
- 应用场景：通过Lrange命令完成微博的分页展示,微博关注列表，粉丝列表，消息列表

### hash(字典)

- 数据结构像java中的HashMap
- 由于数据结构的问题导致hash在内存中的消耗是大于String
- 常见的命令：HSET,HGET,HGETALL
- 应用场景：商品信息

### set(集合)

- 数据结构像java中的HashSet，是一个无序，唯一的集合
- 常见的命令：SADD,SPOP,SINTER,SMEMBERS
- 应用场景：共同关注，可以将不同用户的关注列表 放在set中，利用sinter命令求两个set的交集

### zset(有序列表)

- 数据结构像java中的sorted和HashMap的结合体
- 实现原理，就是在原有的set集合上加上score权重值
- 常见的命令：ZADD,ZRANGE,ZRERANGE
- 应用场景：直播过程中的在线人数，礼物排行榜

## 常见的问题

### 1.redis中的key存活问题

- 由于redis中的数据是KEY-VALUE的结构，而redis又存在于内存中，所以空间的利用重要，当redis中存在大量的冷点key， ，对于系统来说是很不值当的

	- 引申出redis设置key的过期时间，可以通过expire time命令来指定过期时间

		- 定时删除

			- 根据设置的过期时间将对应的key删除。redis会每隔100ms随机抽取一些过期的key，检查是否过期，过期则删除

		- 惰性删除

			- 简单的理解就是，只有用户主动查key是否过期，如果过期则删除。如果没有 ，则不管

	- 以上两种方式会存在一个问题，如果定时删除未删除，惰性删除也未删除key，那么redis中将存在大量的无用key将内存中的空间耗尽，因此需要内存淘汰机制

		- VALATILE-LRU:从已设置过期时间的数据集合中挑选最近最少使用的数据淘汰
		- VALATILE-TTL:从已设置过期时间的数据集合中挑选将要过期的数据淘汰
		- VALATILE-RANDOM:从已设置过期时间的数据集合中随机淘汰
		- ALLKEYS-LRU:当内存空间不足时，移除最近不常用的key(最常用的策略)
		- ALLKEYS-RANDOM:从数据集合中任意选择数据随机淘汰
		- NO-EVICTION:当内存不足时，新写入数据会报错(不会使用)

### 2.redis的持久化

- 保证内存中的数据在遇到宕机等情况后可以恢复，原理是将内存中的数据保存到外存中

	- RDB(快照)

		- 就是将内存中的某个时间点的数据形成副本，保存起来，可以将这个副本复制到其他服务器(redis的主从结构)
		- redis的默认持久化方式，在redis.conf中有默认配置

			- save 900 1:表示当15分钟之后，有至少1个key发生变化，则创建快照
			- save 300 10：表示当5分钟之后，有至少10个key发生变化，则创建快照
			- save 60 10000：表示当1分钟之后，有至少10000个key发生变化，则创建快照

	- AOF(append-only file)

		- 默认情况下AOF是未开启的，可以通过appendonly yes配置来开启
		- 开启后，redis每执行一条改变数据的命令，AOF都会将命令保存在AOF文件中
		- AOF的三种方式

			- appendfsync always：每次有数据发生变化，都将命令保存在文件中
			- appendfsync everysec：每秒钟同步一次，将多个命令同步到AOF文件中
			- appendfsync no：取决于操作系统何时进行同步

	- redis4.0之后支持了RDB和AOF混合持久化

		- 默认是关闭的，通过aof-use-rdb-preamble开启
		- 原理是：AOF文件的重写会将RDB的内容写进原AOF文件的开头
		- 这种模式结合了RDB和AOF的优点，快速加载同时避免丢失过多的数据，缺点是，重写之后的AOF文件的可读性太差
		- AOF文件的重写：用户可以向redis发送BGREWRITEAOF命令，通过移除AOF文件中的冗余命令来减少AOF文件的大小。也可以通过配置来完成AOF文件的重写

			- auto-aof-rewirte-percentage 100
			- auto-aof-rewrite-in-size 64MB

### 3.redis的事务

- redis通过将多个命令打包，统一执行，即使命令出错，也不会回滚

### 4.redis的雪崩

- 原理：当redis中的数据大面积失效，或者说redis宕机，大量的请求直接请求到数据库，导致数据库崩掉
- 解决办法

	- 事前：保证redis的高可用，采用主从结构。选择合适的过期删除策略和内存淘汰策略
	- 事中：使用本地ehcache和hystrix限流和降级
	- 事后：利用持久化的技术尽快将数据恢复

### 5.redis的穿透

- 原理：当redis中存在大量的无效key，导致过多的请求落在数据库上，导致数据库崩掉
- 解决办法

	- 布隆过滤器

		- 原理：布隆过滤中设置多个hash函数，在布隆过滤器中添加元素，经过hash函数运算，将对应位置上的下标设置为1，当请求来时，经过同样的hash运算，判断是不是所有位置上下表都是1，如果是，则这个元素存在，如果不是，直接返回

### 6.redis的并发竞争key的问题

- 原因：当多个系统对同一个key进行操作时，执行的顺序和预期的不一致，出现的问题
- 解决版本

	- 分布式锁

### 7.redis如何保证数据的一致性

- 即redis中的数据和数据库中的数据如何保证一致

	- 一般来说，redis中存放的数据相较于数据库中的数据而言，没有那么重要，因此对于一致性而言，不显得那么重要。而且如果要保证一致性，肯定是要在内存中同时进行读写操作，大大影响速度
	- 可以通过以下方式解决

		- 先在缓存中查找如果缓存中存在，那个直接返回，如果没有，去数据库中查找，然后同步给缓存，返回结果
		- 更新的时候，先更新数据库，再删除缓存

### 8.分布式锁

- 原理：同一时间只允许同一个用户进行操作
- 常见的锁

	- mysql的锁：mysql自带悲观锁
	- zookeeper：可以通过创建临时的有序的子节点
	- redis的单线程：redis是单线程，命令会以串行的方式执行

- redis的分布式锁的问题

	- 锁超时的问题

		- 当服务A持有锁时候，服务却挂掉，导致其他服务无法获得锁
		- 解决办法：通过设置锁的过期时间，但又引来另外一个问题，服务A持有锁，服务未执行完成但是锁超时，导致锁提前释放
		- 可以通过给锁设置一个随机值，当服务A执行完成，释放锁的时候，通过判断随机值是否一致，然后再释放

### 9.geohash算法

- redis可以通过此算法，完成附近的人功能的实现

### 10.redis的发布/订阅模式

- pubsub

	- redis可以通过pubsub模块完成订阅/发布
	- 引入了频道的概念：channel
	- pubsub模块支持模糊匹配，通过此特性完成当发布者新增，实现消费者实时更新
	- pubsub的缺点

		- 没有ACK机制，
		- 没有持久化的机制

- 通过stream实现持久化的发布/订阅

### 11.redis的集群

- 主从复制

	- 将一台redis机器上的数据复制到另外一个redis的机器上，前者称为主节点，后者成为从节点。redis支持主从复制和从从复制
	- 通过执行slaveof命令或者设置slaveof配置完成
	- 需要注意的是：主从复制的发起者是从节点，主节点不需要做任何事情
	- 通过slaveof no one命令断开连接，从节点断开连接后，不会删除连接之前的数据
	- redis2.8以后支持部分复制，通过主从节点维护一个偏移量，当连接中断或者节点宕机，当连接恢复正常，通过判断主从上维护的偏移量来，实现端点续传

- 哨兵机制

	- 哨兵机制实际上就是redis节点，只是该节点不会存储数据，只是作为哨兵使用
	- 哨兵的作用

		- 1.监控：通过向主节点和从节点发送类似于ping命令的操作判断主从的状态
		- 2.故障转移：当主节点宕机，哨兵需要从从节点中选取一个状态好的redis机器成为主节点。
		- 3.配置提供者：客户端可以通过连接哨兵来获取主节点地址
		- 4.通知：将故障转移的结果发送给客户端

	- 故障转移过程中选取主节点的策略

		- 1.从服务器中，被标记为主观下线，已断线或者最后一次回复ping命令超过五秒钟的从服务器被淘汰
		- 2.从服务器中，与主服务器连接断开的时长超过down-after选项指定时长的10倍的从服务器淘汰
		- 3.在剩下的从服务器中，挑选拥有最大偏移量的节点 为主节点，或者拥有最小的工作id的节点为主节点

- redis集群

	- 集群中的redis服务器互相两两相连，客户端连接到集群中的任意一个节点，就可以对其他redis节点进行读写的操作
	- 集群的主要作用

		- 数据分区：集群将数据分散到各个节点，突破了单个redis节点的存储容量，大大增加了存储能力；提高了集群的响应能力
		- 高可用：集群支持主从复制和主节点的自动故障转移，当任意节点发生故障，集群仍可以对外提供服务

	- 建立集群

		- 通过命令：redis-cli --cluster create --cluster-replicas 1 127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005就创建了三主三从的集群(-replicas 1表示给每个主节点创建一个从节点)

	- 验证集群

		- 通过命令：redis-cli -c -h 127.0.0.1 -p 7000（-c表示集群模式）

*XMind - Trial Version*