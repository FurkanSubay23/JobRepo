package com.jobapp.service;

import com.jobapp.entity.Job;
import com.jobapp.request.JobPostRequest;
import com.jobapp.request.JobUpdateRequest;
import com.jobapp.response.JobResponse;

import java.util.List;
import java.util.Optional;

public interface JobService {

    List<JobResponse> getAllJobs(Optional<Long> userId, Optional<String> sort);

    Job getOneJob(long jobId);

    Job updateJob(Long jobId, JobUpdateRequest jobUpdateRequest);

    void deleteJob(Long jobId);

    Job createJob(JobPostRequest jobPostRequest);

    List<Job> searchByName(String searchName);
}
