package com.github.antonmastiuk.githubactivity.json.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.antonmastiuk.githubactivity.json.deserializer.ContributionDayDeserializer;

import java.util.List;

@JsonDeserialize(using = ContributionDayDeserializer.class)
public class ContributionDayListWrapper {

    public ContributionDayListWrapper(List<ContributionDay> contributionDays) {
        this.contributionDays = contributionDays;
    }

    private List<ContributionDay> contributionDays;

    public List<ContributionDay> getContributionDays() {
        return contributionDays;
    }

    public void setContributionDays(List<ContributionDay> contributionDays) {
        this.contributionDays = contributionDays;
    }
}
