package com.baidubce.services.kafka.model.job;

import com.baidubce.model.ListResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListJobsResponse extends ListResponse {

    private List<Job> jobs;
}
