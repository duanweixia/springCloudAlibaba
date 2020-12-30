package com.david.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Package: com.david.controller
 * @ClassName: SendMessageController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/30 下午1:16
 * @Description:
 */
@Component  //添加进组件
@EnableBinding(Sink.class) //Sink表示是消息接收者，Source表示消息的推送者
public class ReceiveMessageController {
   @Value("${server.port}")
   private String serverPort;
   //目前2个消费者8802和8803都消费了相同的信息，存在重复消费情况。我们使用cloud-stream中的分组来避免重复消费
   //导致原因：默认分组group是不同的，组流水号不一样，被认为是不同组，可以消费
   //解决方案：自定义配置分组，自定义配置分为同一个组，解决重复消费问题
   @StreamListener(Sink.INPUT)
   public void input(Message<String> message){

       System.out.println("消费者1号，接收到的消息是----->"+message.getPayload()+"\t"+"port:"+serverPort);
   }
}
