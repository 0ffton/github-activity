package com.github.antonmastiuk.githubactivity.service;

import java.io.IOException;
import java.util.List;

public interface DataPuller<T> {
    List<T> pullData(String url) throws IOException;
}
