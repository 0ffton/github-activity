package com.github.antonmastiuk.githubactivity.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.github.antonmastiuk.githubactivity.json.response.ContributionDay;
import com.github.antonmastiuk.githubactivity.json.response.ContributionDayListWrapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContributionDayDeserializer extends StdDeserializer<ContributionDayListWrapper> {

    public ContributionDayDeserializer() {
        this(null);
    }


    public ContributionDayDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ContributionDayListWrapper deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = p.getCodec().readTree(p);
        ArrayNode contributionDays = (ArrayNode) treeNode
                .get("data")
                .get("viewer")
                .get("contributionsCollection")
                .get("contributionCalendar")
                .get("weeks");

        List<ContributionDay> contributionDayList = new ArrayList<>();
        contributionDays.elements().forEachRemaining(jsonNode -> {
            jsonNode.elements().forEachRemaining(jsonNode1 -> {
                jsonNode1.elements().forEachRemaining(jsonNode2 -> {
                    ContributionDay contributionDay = new ContributionDay();
                    contributionDay.setColor(jsonNode2.get("color").asText());
                    contributionDay.setContributionCount(jsonNode2.get("contributionCount").asInt());
                    contributionDay.setDate(convertTextToDate(jsonNode2.get("date").asText()));
                    contributionDayList.add(contributionDay);
                });
            });
        });
        return new ContributionDayListWrapper(contributionDayList);
    }

    private Date convertTextToDate(String stringDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
           return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
