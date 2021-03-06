package org.example.hrsample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hrsample.dao.JobHistoryMapper;
import org.example.hrsample.dto.EmployeeDto;
import org.example.hrsample.entity.JobHistoryEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Job history service implementation.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class JobHistoryServiceImpl implements JobHistoryService {
    private final JobHistoryMapper jobHistoryMapper;

    @Override
    public void saveJobOfEmployee(EmployeeDto employeeDto, LocalDate endDate) {
        Optional<LocalDate> optionalLocalDate =
                jobHistoryMapper.getLastJobDateByEmployeeId(employeeDto.getEmployeeId());
        LocalDate startDate = optionalLocalDate.orElseGet(employeeDto::getHireDate);
        JobHistoryEntity jobHistoryEntity = createJobHistory(employeeDto, startDate, endDate);
        jobHistoryMapper.addJob(jobHistoryEntity);
        log.info("Created new note in job history for employee = {}", jobHistoryEntity.getEmployeeId());
    }

    private JobHistoryEntity createJobHistory(EmployeeDto employeeDto,
                                              LocalDate startDate,
                                              LocalDate endDate) {
        return new JobHistoryEntity(
                employeeDto.getEmployeeId(),
                startDate,
                endDate,
                employeeDto.getJob().getJobsId(),
                employeeDto.getDepartment().getDepartmentId()
        );
    }
}
