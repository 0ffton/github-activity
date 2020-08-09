package com.github.antonmastiuk.githubactivity.service.impl;

import com.github.antonmastiuk.githubactivity.model.Activity;
import com.github.antonmastiuk.githubactivity.service.DataPuller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubDataPuller implements DataPuller<Activity> {
    @Value("main.url")
    private String url;

    public List<Activity> pullData(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.select(".js-calendar-graph-svg")
                .get(0).children().get(0).children()
                .select("g rect")
                .stream().map(this::mapResponseToModel).collect(Collectors.toList());
    }

    private Activity mapResponseToModel(Element element) {
        Activity activity = new Activity();
        activity.setCount(Integer.parseInt(element.attr("data-count")));
        String date = element.attr("data-date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = new Date(0);
        try {
            parsedDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        activity.setDate(parsedDate);
        activity.setFill(element.attr("fill"));
        return activity;
    }
}
