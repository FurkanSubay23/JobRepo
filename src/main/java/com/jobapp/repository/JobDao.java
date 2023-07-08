package com.jobapp.repository;

import com.jobapp.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobDao extends JpaRepository<Job, Long> {
    List<Job> findByUserId(Long userId);

    @Query("SELECT j FROM Job j WHERE lower(j.title) LIKE concat('%',lower(:searchName),'%') OR lower(j.definition) LIKE concat('%',lower(:searchName),'%')")
    List<Job> searchByTitleLikeOrDefinitionLikes(@Param("searchName") String searchName);

    // List<Job> findByTitleContaining(String searchName);

    @Query("SELECT j FROM Job j WHERE j.user.id = :userId ORDER BY " +
            "CASE WHEN :sort = 'title' THEN j.title END ASC, " +
            "CASE WHEN :sort = 'startDate' THEN j.startDate END ASC")
    List<Job> sortByTitleOrStartDateWithUserId(@Param("userId") Long userId, @Param("sort") String sort);


    @Query("SELECT j FROM Job j ORDER BY :sort ASC ")
    List<Job> sortByTitleOrStartDate(@Param("sort") String sort);
}
