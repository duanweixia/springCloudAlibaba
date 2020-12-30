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

   @StreamListener(Sink.INPUT)
   public void input(Message<String> message){

       System.out.println("消费者1号，接收到的消息是----->"+message.getPayload()+"\t"+"port:"+serverPort);
   }
}
