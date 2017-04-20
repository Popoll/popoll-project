package com.esgi.popoll.websocket.survey;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SurveyController {

    @MessageMapping("/socket")
    @SendTo("/topic/survey")
    public void pushToSocket() {

    }
}
