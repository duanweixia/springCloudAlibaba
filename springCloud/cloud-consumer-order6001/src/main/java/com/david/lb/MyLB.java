package com.david.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Package: com.david.lb
 * @ClassName: MyLB
 * @Author: zhangweixia
 * @CreateTime: 2020/12/24 上午11:34
 * @Description:
 */
@Component //让spring容器能够扫描到
public class MyLB implements LoadBalancer{
    //AtomicInteger 原子类
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    //final定义不让其他方法修改这个方法
    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            //2147483647 这个数是 Integer.MAX_VALUE的值 即整型的最大值,超出这个范围（21亿）即
            //超出了 int 范围就 从新取0，从新计数
            next = current >= 2147483647 ? 0 : current+1;
            //如果这里没有取到我们期望的值，我们就在这里自旋，直到取到我们期望的值
            // 并写进atomicInteger 这个原子类里面，CAS自旋锁

        }while (!this.atomicInteger.compareAndSet(current,next));
        //比如说current（期望值） 开始为0，next（修改值） 开始为1，根据自旋锁，则将atomicInteger
        //修改为1，this.atomicInteger.compareAndSet(current,next)这一团返回true,整个while返回
        //false,跳出 do while循环，下面打印出next 和返回 next
        //加自旋锁是为了比synchronized锁更轻量级，解决高并发下多线程不会同时修改值
        System.out.println("第几次访问次数next:"+next);
        return next;


    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {

        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE); // 2147483647
        System.out.println(Integer.MIN_VALUE); // -2147483648
        System.out.println(Integer.MAX_VALUE+1); // -2147483648
        System.out.println(Integer.MAX_VALUE+2); // -2147483647

    }
}
