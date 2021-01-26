package com.github.antonmastiuk.githubactivity.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.antonmastiuk.githubactivity.json.response.ContributionDay;
import com.github.antonmastiuk.githubactivity.json.response.ContributionDayListWrapper;
import com.github.antonmastiuk.githubactivity.service.DataPuller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class GraphQLGithubDataPuller implements DataPuller<ContributionDay> {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    @Value("${main.graphQLQuery}")
    private String graphQlQuery;

    public GraphQLGithubDataPuller(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<ContributionDay> pullData(String url) throws IOException {
        ObjectNode query = objectMapper.createObjectNode();
        query.put("query", graphQlQuery);
        ResponseEntity<ContributionDayListWrapper> contributionDayListWrapperResponseEntity = restTemplate.postForEntity(url, query.toString(), ContributionDayListWrapper.class);
        return contributionDayListWrapperResponseEntity.getBody().getContributionDays();
    }
}
