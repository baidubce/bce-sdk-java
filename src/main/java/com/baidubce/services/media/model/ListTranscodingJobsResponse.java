package com.baidubce.services.media.model;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

public class ListTranscodingJobsResponse extends AbstractBceResponse {
    private List<Job> jobs = new ArrayList<Job>();

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

}
