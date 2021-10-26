package com.kim.test.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TestController {

    @Autowired
    private SimpMessagingTemplate websocket;

    @MessageMapping("/sendTo")
    @SendTo("/topics/sendTo")
    public String SendToMessage() throws Exception{
        log.info("sendTo");
        return "상황발생";
    }

    @MessageMapping("/Template")
    public void SendTemplateMessage(){
        log.info("template");
        websocket.convertAndSend("/topics/template", "Template");
    }

}
