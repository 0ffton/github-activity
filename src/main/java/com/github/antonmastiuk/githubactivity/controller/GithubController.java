package com.github.antonmastiuk.githubactivity.controller;

import com.github.antonmastiuk.githubactivity.model.Activity;
import com.github.antonmastiuk.githubactivity.service.DataPuller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@EnableScheduling
@CrossOrigin("*")
public class GithubController {
    @Value("${main.url}")
    private String url;

    private DataPuller<Activity> dataPuller;

    //    todo: add thread safety
    private SseEmitter sseEmitter;

    public GithubController(DataPuller dataPuller) {
        this.dataPuller = dataPuller;
    }

    @PostMapping("/payload")
    public void receivePayload(@RequestBody String body) throws IOException {
        getDataAndSend();
    }


    @Scheduled(cron = "* 59 23 * * *")
    private void sendDataByCron() throws IOException {
        if (sseEmitter != null) {
            getDataAndSend();
        }
    }

    @GetMapping("/data")
    public SseEmitter sendData() throws IOException {
        sseEmitter = new SseEmitter();
        getDataAndSend();
        return sseEmitter;
    }

    private void getDataAndSend() throws IOException {
        List<Activity> activities = dataPuller.pullData(url);
        sseEmitter.send(activities);
    }

}
