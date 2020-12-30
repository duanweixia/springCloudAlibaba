package com.david.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Package: com.david.service
 * @ClassName: IMessageProviderImpl
 * @Author: zhangweixia
 * @CreateTime: 2020/12/30 下午1:07
 * @Description:
 */
//这里不用写@service 因为不用给数据库打交道
@EnableBinding(Source.class)    //定义消息的推送管道
public class IMessageProviderImpl implements IMessageProvider {
    @Resource
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {

        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("serial="+serial);
        return null;
    }
}
