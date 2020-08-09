package com.github.antonmastiuk.githubactivity.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;

@RestController
//@EnableScheduling
public class GithubController {


    private SseEmitter sseEmitter;

    @PostMapping("/payload")
    public void receivePayload(@RequestBody String body) {
        System.out.println(body);
    }


//    @Scheduled(cron = "*/10 * * * * *")
//    private void sendDataByCron() throws IOException {
//     if (sseEmitter != null) {
//         sseEmitter.send(new Date());
//     }
//    }
//
//    @GetMapping("/data")
//    public SseEmitter sendData() throws IOException {
//        sseEmitter = new SseEmitter();
//        return sseEmitter;
//    }

}
