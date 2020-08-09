package com.github.antonmastiuk.githubactivity.model;

import java.util.Date;

public class Activity {
    private String fill;
    private int count;
    private Date date;

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Response{" +
                "fill='" + fill + '\'' +
                ", count=" + count +
                ", date=" + date +
                '}';
    }
}
