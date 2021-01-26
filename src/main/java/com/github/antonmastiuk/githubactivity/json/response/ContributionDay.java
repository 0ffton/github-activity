package com.github.antonmastiuk.githubactivity.json.response;

import java.util.Date;

public class ContributionDay {
    private int contributionCount;
    private Date date;
    private String color;

    public int getContributionCount() {
        return contributionCount;
    }

    public void setContributionCount(int contributionCount) {
        this.contributionCount = contributionCount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
