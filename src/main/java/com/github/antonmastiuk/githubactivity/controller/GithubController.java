package com.github.antonmastiuk.githubactivity.controller;

import com.github.antonmastiuk.githubactivity.json.response.ContributionDay;
import com.github.antonmastiuk.githubactivity.service.DataPuller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@RestController
@EnableScheduling
@CrossOrigin("*")
public class GithubController {
    @Value("${main.url}")
    private String url;

    private DataPuller<ContributionDay> graphQLGithubDataPuller;
    private SseEmitter sseEmitter;

    public GithubController(DataPuller<ContributionDay> graphQLGithubDataPuller) {
        this.graphQLGithubDataPuller = graphQLGithubDataPuller;
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
        List<ContributionDay> activities = graphQLGithubDataPuller.pullData(url);
        sseEmitter.send(activities);
    }

}
