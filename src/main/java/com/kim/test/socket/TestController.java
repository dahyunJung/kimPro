package com.kim.test.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TestController {

    @Autowired
    private SimpMessagingTemplate websocket;

    @MessageMapping("/sendTo")
    @SendTo("/topics/sendTo")
    public String SendToMessage() throws Exception{
        return "상황발생";
    }

    @MessageMapping("/template")
    public void SendTemplateMessage(){
        websocket.convertAndSend("/topics/template", "template");
    }

    @RequestMapping("/api")
    public void SendAPI(){
        websocket.convertAndSend("/topics/api", "api");
    }


}
