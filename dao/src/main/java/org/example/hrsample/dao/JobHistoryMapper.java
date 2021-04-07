package org.example.hrsample.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.hrsample.entity.JobHistoryEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Mapper
@Repository
public interface JobHistoryMapper {
    void addJob(@Param("jobHistoryEntity") JobHistoryEntity jobHistoryEntity);

    Optional<LocalDate> getLastJobDateByEmployeeId(Integer id);
}
