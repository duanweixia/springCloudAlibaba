package com.david.controller;

import com.david.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Package: com.david.controller
 * @ClassName: SendMessageController
 * @Author: zhangweixia
 * @CreateTime: 2020/12/30 下午1:16
 * @Description:
 */
@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider iMessageProvider;
    @GetMapping("/sendMessage")
    public String sendMsg(){
        return iMessageProvider.send();
    }
}
