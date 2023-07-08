package com.jobapp.controller;

import com.jobapp.entity.Job;
import com.jobapp.exception.ErrorHandling;
import com.jobapp.request.JobPostRequest;
import com.jobapp.request.JobUpdateRequest;
import com.jobapp.response.JobResponse;
import com.jobapp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs(@RequestParam Optional<Long> userId, @RequestParam Optional<String> sort) {
        return new ResponseEntity<>(jobService.getAllJobs(userId, sort), HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getOneJob(@PathVariable long jobId) {
        Optional<Job> job = Optional.ofNullable(jobService.getOneJob(jobId));
        return job.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody JobPostRequest jobPostRequest) {
        return new ResponseEntity<>(jobService.createJob(jobPostRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestBody JobUpdateRequest jobUpdateRequest) {
        return new ResponseEntity<>(jobService.updateJob(jobId, jobUpdateRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/blabla")
    public ResponseEntity<List<Job>> searchByName(@RequestParam String searchName) {
        return ResponseEntity.ok(jobService.searchByName(searchName));
    }

}
