1.LB负载均衡（Load Balance）是什么？
简单的说就是将用户的请求，平摊的分配到多个服务上，从而达到系统的HA高可用。常见的负载均衡
有软件Nginx,LVS,硬件F5等

2.Ribbon本地负载均衡客户端 VS Nginx服务端负载均衡区别？
Nginx是服务器负载均衡，客户端所以请求都会交给Nginx,然后有Nginx实现转发请求。即负载均衡是由服务器实现的。
Ribbon本地负载均衡，在调用微服务接口时，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从
而在本地实现RPC远程服务调用技术。

3.集中式LB
即在服务的消费方和服务提供方之间使用独立的LB设施（可以是硬件，如F5，也可以是软件，如Nginx）,由
该设施负责把访问请求通过某种策略转发

4.进程内LB
将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选出一个
合适的服务器。Ribbon就属于进程内LB,它只是一个类库，集成于消费方进程，消费方通过它来获取到服务
提供方的地址。Ribbon 一句话就是 负载均衡+RestTemplate调用

5.Ribbon在工作时分2步
第一步，先选择EurekaServer或是其他集成Ribbon的服务注册中心，它优先选择在同一区域内
负载较少的server.
第二步，再根据用户指定的策略，在从server获取到的服务注册列表中选择一个地址。
其中Ribbon提供了多种策略，比如 轮询、随机、和根据相应时间加权

6.Hystrix(豪猪哥) 非常重要 服务降级
出道即是巅峰，虽然现在spring官方停更，但是后面做服务降级的框架都是借鉴于它
服务雪崩：
多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又
调用其他的微服，这就是所谓的 "扇出"。如果扇出的链路上某个微服务的调用响应时间
过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，
所谓的 "雪崩效应"。
Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用
失败，比如超时、异常等，Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务失败，避免
级联故障，以提供分布式系统的弹性。
"断路器" 本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（类似于熔断）
保险丝，向调用方返回一个符合预期的，可处理的备选响应（FallBack），而不是长时间的等待或者
抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要的占用，从而避免
了故障在分布式系统中的蔓延、乃至雪崩
hystrix的服务降级，是通过fallback返回给调用者一个友好的提示信息。而服务熔断，是当检测到
该节点微服务调用响应正常后，恢复链路调用，这2者是2个不同的概念。
7.网关 zuul (弃用),zuul2(还没有发布出来)（netflix公司出的） 
现在使用新一代网关 gateway(spring公司出的)
springCloud Gateway 是spring Cloud的一个全新项目，基于spring5.0+springboot2.0和
Project Reactor 等技术开发的网关，它旨在为微服务架构提供一种简单有效的统一API路由管理方式。
springCloud Gateway作为 spring Cloud生态系统中的网关，目标是替代zuul，在 
springCloud2.0 以上版本中，没有对新版本的zuul 2.0以上最新高性能版本进行集成，仍然还是
使用的是 zuul 1.x 非Reactor模式的老版本，而为了提升网关性能，springCloud Gateway是
基于WebFlux框架实现的，而WebFlux框架底层则使用了高性能的Reactor模式通用框架Netty.
springCloud Gateway 的目标提供统一的路由方式且基于 Filter链的方式提供了网关基本的功能
，例如：安全，监控/指标，和限流
7.1 zuul 1.x 是一个基于阻塞 I/O 的API Gateway
7.2 zuul 1.x 基于servlet 2.5 使用阻塞架构，它不支持任何长连接（如websocket）,zuul 的设计
模式和nginx较像，每次I/O都是从工作线程中选一个执行，请求线程被阻塞到工作线程完成，但是差别
是nginx是使用C++实现的，zuul是使用java实现的，而JVM本身会有第一次加载较慢的情况，使得
zuul的性能相对较差
7.3 zuul 2.x理念更先进，想基于Netty非阻塞和支持长连接，但springCloud目前还没有整合
在性能方面，根据官方提供的基准测试，springCloud Gateway的PRS(每秒请求数)是zuul的1.6倍
7.4 springCloud Gateway建立在spring Framework 5 、Project Reactor和springboot2.0
之上，使用非阻塞AP。
7.5 springCloud Gateway 还支持 websocket,并且与spring紧密集成拥有更好的开发体验
8.zookeeper 分布式协调服务框架 服务治理
分布式协调：解决分布式环境中多个进程之间的同步控制，让它们有序去访问某种临界资源，防止造成
脏数据。比如说某个订单服务，放在docker中起了3台服务器，就是3个进程，3个JVM.高并发下，
每台服务器有5个线程操作去操作商品服务数据，商品服务 也被消耗了5个，就造成了脏数据。进程之间
没法用同步锁，只能用分布式锁，zookeeper就是一个实现分布式锁的框架，让3个进程有序进行去
操作商品服务，1台操作完了返回了，第二台才能操作。。。就不会造成脏数据。

9.分布式锁的实现有哪些？
9.1 Memcached 利用add命令，此命令是原子性操作，只有在key不存在的情况下，才能add成功，
也就意味这线程得到了锁。
9.2 redis 和 Memcached类似，利用redis的 setnx 命令，此命令同样是原子性的（lua脚本）
，只有在key不存在的情况下，才能set 成功。
9.3 利用zookeeper的顺序临时节点，来实现分布式锁和等待队列，zookeeper设计的初衷，就是
为了实现分布式锁服务的。
9.4 Chubby Google公司实现的粗粒度分布式锁服务，底层利用了Paxos一致性算法。
redis分布式锁具体实现原理：比如现在有2个JVM要去修改某个商品库存。JVM1先去redis里使用
setnx (商品I（唯一）,value),如果返回0，
说明该商品没有被锁住，可以进行操作，如果返回1则说明该商品已被加锁，不能进行操作了。有加锁
肯定有解锁，当JVM1操作完该商品，利用redis.del(商品ID）释放锁，这个时候JVM2再使用setnx
(商品ID），发现返回0，JVM2这时候就可以对该商品进行操作了
如果JVM1 加完锁（setnx），还没有来得及释放锁(del),JVM1突然宕机了，这时候就造成了死锁。
所以我们需要设置一个锁(key)的过期时间 exprie 

**项目github地址：https://github.com/467812367/cloud2020.git

#springCloud bus 消息总线
1.利用消息总线触发一个客户端/bus/refresh,从而刷新所有客户端配置
2.利用消息总线触发一个服务端ConfigServer的/bus/refresh端点，从而刷新所有客户端的配置
使用第2种方式显然更合适，第1种不合适，因为第2种是通过总控去通知其他客户端，而第一种是通过通知一个
客户端，一个客户端被通知了然后在传到到另一个客户端。就跟传染一样。第1种破坏的微服务的职责单一性，
破坏了微服务各个节点的职责对等性。

#springCloud Stream 消息驱动
比如java里面用的 rabbitmq,后台大数据用的 kafka,这样在项目对接起来就很麻烦，有没有一种新技术
让我们不再关注具体MQ的细节，我们只需要用一种适配绑定的方式，自动的给我们在各种MQ中切换。
springCloud Stream 主要用来：屏蔽底层消息中间件的差异，降低切换成本，统一消息的编程模型
官方定义springCloud Stream 是一个构建消息驱动微服务的框架。应用程序通过inputs或者outputs
来与springCloud Stream中binder对象交互。通过我们配置binder(绑定)，而springCloud Stream
的binder对象负责与消息中间件交互，所以，我们只需要搞清楚如何与springCloud Stream交互就可以
方便的使用消息驱动的方式。通过使用 spring Integration来连接消息代理中间件以实现消息事件驱动
springCloud Stream 为一些供应商的消息中间件产品提供了个性化的自动配置实现，引用了发布-订阅、
消费组、分区的三个核心概念。目前只支持 rabbitmq 和 kafka
springCloud Stream 是发布-订阅模式

#Sleuth (链路监控) 之zipkin搭建安装
springCloud 从F版起就不需要自己构建 zipkin server 了，只需要调用jar包即可
下载地址：https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/
然后终端运行该jar 包即可。jar -jar zipkin-server-2.12.9-exec.jar 
浏览器访问：localhost:9411 即可进入该页面

#springCloud alibaba 简介
服务限流降级：默认支持Servlet、 Feign 、RestTemplate 、Dubbo、 RocketMQ 限流降级
功能的接入，可以在运行时通过控制台实时修改限流降级规则，还支持查看限流降级 Metrics 监控。
服务注册与发现：适配 springCloud 服务注册与发现标准，默认支持了Ribbon的支持。
分布式配置管理：支持分布式系统中的外部化配置，配置更改时自动刷新。
消息驱动能力：基于 springCloud Stream 为微服务应用构建消息驱动能力。
阿里云对象存储：阿里云提供的海量、安全、低成本、高可靠的云存储服务。支持在任何应用、任何时间
、任何地点存储和访问任意类型的数据。
分布式任务调度：提供秒级、精准、高可靠、高可用的定时（基于Cron表达式）任务调度服务。同时提供
分布式的任务执行模型，如网格任务。网格任务支持海量子任务均匀分配到所有 
Worker(schedulerx-client) 上执行。
springCloud alibaba 已经加入了springCloud的官方孵化器，而且已经毕业了
nacos 服务注册中心+配置中心  等价于 springCloud的 Eureka+Config+Bus

#注册中心的对比
*总结：eureka和zookeeper 都是基于java语言，而consul是基于go语言。CAP理论中，eureka是使用的AP理论
* 而zookeeper和consul都是使用的CP理论,分布式系统一定会保证P,不然还叫分布式么。服务健康检查方面：eureka是可配置支持，zookeeper和consul是
* 支持。对外暴露接口方面：eureka使用 HTTP，consul使用 HTTP/DNS 二者都有可视化界面的，
* zookeeper使用的是 linux的 zkCli客户端，题外：redis和mongodb等nosql是CP,mysql是CA.
* CAP理论关注的粒度是数据，而不是整体系统设计的策略。假如有A和B二个系统：1、当没有出现网络分区时，系统A和
* 系统B的数据一致，X=1。2、将系统A的值修改为2，X=2。  3、当出现网络分区后，系统A和系统B之间的数据同步
* 数据失败，系统B的X=1。4、当客户端请求系统B时，为了保证可用性，此时B应返回旧值，X=1
* AP架构：当网络分区后（即分布式），为了保证可用性，系统B可以返回旧值，保证系统可用性，结论：违背了一致性C要求
* ，只满足可用性和分区容错性，即AP 比如eureka的自我保护机制，服务挂掉了一个后，短时间该服务还是在上面。
* CP架构：当网络分区后（即分布式），为了保证一致性，一个服务挂掉后，就必须拒绝请求，否则无法保证一致性，结论：违背
* 了可用性A要求，只满足一致性和分区容错性，即CP 比如：使用zookeeper和consul时，上面注册的某个服务挂掉后，通过
* 心跳机制，及时把挂掉服务踢出了注册中心，zookeeper临时节点。
* 3者都能跟springCloud 很好的集成
#nacos 和 CAP
nacos 可以在 AP 和 CP 之间来回切换
C是所有节点在同一时刻看到的数据是一致的，而A的定义是所有的请求都会收到响应
何时选择何种模式？
一般来说：如果不需要存储服务级别的信息，且服务是通过 nacos-client注册，并能保证心跳上报，
那么就可以选择AP。当前主流 springCloud 和 Dubbo 服务，都适用于AP模式。AP为了服务的可用性
而减弱了一致性，因此AP模式下只支持注册临时实例。
如果需要在服务级别编辑或者存储配置信息，那么CP是必须的，K8S服务和DNS服务则适用于CP模式。
CP模式下则支持注册持久化实例，此时则是以 Raft 协议为集群运行模式。该模式下注册实例之前必须
先注册服务，如果服务不存在，则会返回错误。
curl -X PUT "$NACOS_SERVER:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP"
