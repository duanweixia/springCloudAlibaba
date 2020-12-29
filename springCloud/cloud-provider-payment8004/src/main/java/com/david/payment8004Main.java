package com.david;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Package: com.david
 * @ClassName: payment8004Main
 * @Author: zhangweixia
 * @CreateTime: 2020/12/23 下午3:22
 * @Description:  这里使用的是zookeeper作为注册中心
 */
@SpringBootApplication
@EnableDiscoveryClient //除了使用euraka 做客户端需要使用@EnableEurekaClient ，其他注册中心使用
//@EnableDiscoveryClient 跟注册中心打交道
public class payment8004Main {
    public static void main(String[] args) {
        //导入的zookeeper依赖版本是3.5.2 ,本地安装的zookeeper 版本是3.5.8,所以这里是正常启动的
        //实际开发中 如果服务器中zookeeper 版本是2.几，这里启动会报版本冲突，实际工作中根据服务器中
        //的zookeeper版本，导入相对应版本的依赖即可
        /*重点说明：zookeeper保证的是CP,即一致性和分区容错性，eureka保证的是AP,即高可用和分区容错性，一个
        * 分布式系统中，首先 P是 必须保证的，即分区容错性（一些特殊的情况导致子网络间出现网络不通的情况，
        * 但是各个子网络内部通信是正常的）。zookeeper放弃掉了A高可用，不能保证每次服务请求的可用性，
        * 任何时候对zookeeper访问，都能得到数据一致性的结果，同时系统对网络分割具有容错性，不能保证每次服务
        * 请求的可用性（注：即在某些极端情况下，zookeeper可能会丢弃一些请求，消费者程序需要从新请求才能获得
        * 结果）所以说zookeeper不能保证可用性。比如：在进行leader选举时，集群都是不可用的，在使用zookeeper
        * 获取服务列表时，当master节点因为网络故障与其他节点失去联系时，剩余节点会从新进行leader选举，问题
        * 在于选举时间过长，30～120秒，在这个期间整个ZK集群都是不可用的。这就导致注册服务不可用，虽然服务能
        * 最终恢复，但是漫长的选举等待时间导致的注册服务长期不可用，所以ZK不满足高可用. eureka之间集群
        * 是对等的，一个服务挂了，注册服务还是可以使用，因为不用像zookeeper那样进行leader选举，所以保证了
        * 高可用特性。一个服务挂了，可能挂了的服务数据还没有同步到其他服务上去，所以不能保证一致性*/
        SpringApplication.run(payment8004Main.class,args);
    }
}
