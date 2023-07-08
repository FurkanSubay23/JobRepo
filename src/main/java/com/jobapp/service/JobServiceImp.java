package com.jobapp.service;

import com.jobapp.Utils.SortUtils;
import com.jobapp.entity.Job;
import com.jobapp.entity.User;
import com.jobapp.exception.ErrorHandling;
import com.jobapp.repository.JobDao;
import com.jobapp.request.JobPostRequest;
import com.jobapp.request.JobUpdateRequest;
import com.jobapp.response.JobResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class JobServiceImp implements JobService {

    private JobDao jobDao;
    private UserService userService;
    private final Logger logger = Logger.getLogger(UserServiceImp.class.getName());


    @Autowired
    public JobServiceImp(JobDao jobDao, UserService userService) {
        this.jobDao = jobDao;
        this.userService = userService;
    }

    @Override
    public List<JobResponse> getAllJobs(Optional<Long> userId, Optional<String> sort) {
        List<Job> jobList;
        String sorting = null;

        if (sort.isPresent()) {
            logger.info("Sorting here");
            int theSort = Integer.parseInt(sort.get());
            logger.info("the sort value " + theSort);
            switch (theSort) {
                case SortUtils.TITLE:
                    sorting = "title";
                    break;
                default:
                    sorting = "startDate";
            }
            logger.info(sorting);
            if (userId.isPresent()) {   // Both of param here
                logger.info("Joblist turn back with userId and sorting !!!");
                jobList = jobDao.sortByTitleOrStartDateWithUserId(userId.get(), sorting);
                logger.info("1");
            } else {                    // Just Sorting
                jobList = this.jobDao.findAll(Sort.by(Sort.Direction.ASC, sorting));
                logger.info("2");
            }
        } else if (userId.isPresent()) {   // Just userId
            logger.info("JobsList turn back with userId and without sorting !!!");
            jobList = jobDao.findByUserId(userId.get());
            logger.info("3");
        } else {                    // None of them
            logger.info("Joblist turn back with all jobs !!!");
            jobList = jobDao.findAll();
            logger.info("4");
        }
/*
        if (sort.isPresent()) {
            logger.info("Sorting here");
            int theSort = Integer.parseInt(sort.get());
            logger.info("the sort value " + theSort);
            switch (theSort) {
                case SortUtils.TITLE:
                    sorting = "title";
                    break;
                default:
                    sorting = "startDate";
            }
            Sort sort1 = Sort.by(Sort.Direction.ASC, sorting);
            if (userId.isPresent()) {
                logger.info("Joblist turn back with userId and sorting !!!");
                jobList = jobDao.findByUserId(userId.get(), sort1);
                jobList = jobList.stream().sorted(Sort.B)
            }
        }
 */
        return jobList.stream().map(job -> {
            return new JobResponse(job);
        }).collect(Collectors.toList());

    }

    @Override
    public Job getOneJob(long jobId) {
        logger.info("Get one job with Id");
        Optional<Job> job = this.jobDao.findById(jobId);
        return job.orElse(null);
    }

    @Override
    public Job updateJob(Long jobId, JobUpdateRequest jobUpdateRequest) {

        Optional<Job> job = jobDao.findById(jobId);
        if (job.isPresent()) {
            logger.info("Job Here");
            Job newJob = job.get();
            newJob.setTitle(jobUpdateRequest.getTitle());
            logger.info("Job set title");
            newJob.setDefinition(jobUpdateRequest.getDefinition());
            logger.info("Job set definition");
            if (jobUpdateRequest.getStartDate() != null) {
                newJob.setStartDate(jobUpdateRequest.getStartDate());
            }
            newJob.setStartDate(newJob.getStartDate());
            if (jobUpdateRequest.getEndDate() != null)
                newJob.setEndDate(jobUpdateRequest.getEndDate());
            logger.info("Created job method used");
            //newJob.setId(job.get().getId());
            return this.jobDao.save(newJob);
        } else
            throw new ErrorHandling("This is not acceptable Id");
    }

    @Override
    public void deleteJob(Long jobId) {
        logger.info("Deleting job method used");
        this.jobDao.deleteById(jobId);
    }

    @Override
    public Job createJob(JobPostRequest jobPostRequest) {
        User user = userService.getOneUser(jobPostRequest.getUserId());
        logger.info("User found !!!");
        if (user != null) {
            Job job = new Job();
            job.setUser(user);
            job.setTitle(jobPostRequest.getTitle());
            job.setDefinition(jobPostRequest.getDefinition());
            logger.info("Jobs setter attributes");
            if (jobPostRequest.getStartDate() != null) {
                job.setStartDate(jobPostRequest.getStartDate());
                logger.info("Jobs Set Startdate");
            } else {
                job.setStartDate(LocalDate.now());
            }
            job.setEndDate(jobPostRequest.getEndDate());
            return this.jobDao.save(job);
        }
        throw new ErrorHandling("This is not acceptable Id");
    }

    @Override
    public List<Job> searchByName(String searchName) {
        logger.info("Search method start");
        List<Job> jobList = this.jobDao.searchByTitleLikeOrDefinitionLikes(searchName);
        logger.info("Liste geri ok oldu mu");
        if (jobList.size() != 0) {
            logger.info("Return searh word job");
            return jobList;
        } else
            throw new ErrorHandling("Can not found any job include search name");
    }
}
