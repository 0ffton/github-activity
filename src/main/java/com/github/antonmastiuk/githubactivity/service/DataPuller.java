package com.github.antonmastiuk.githubactivity.service;

import com.github.antonmastiuk.githubactivity.model.Activity;

import java.io.IOException;
import java.util.List;

public interface DataPuller<T> {
    List<Activity> pullData(String url) throws IOException;
}
