package org.example.hrsample.service;

import lombok.RequiredArgsConstructor;
import org.example.hrsample.dao.EmployeesMapper;
import org.example.hrsample.dto.EmployeeDto;
import org.example.hrsample.entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Employees service implementation.
 */
@Service
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeesService {
    private final EmployeesMapper employeesMapper;
    private final JobHistoryService jobHistoryService;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public EmployeeDto addNewEmployee(EmployeeDto employeeDto) {
        Integer employeeId = employeeDto.getEmployeeId();
        if (employeesMapper.getEmployeeById(employeeId).isPresent()) {
            throw new RuntimeException(
                    String.format("Employee with id=%d already exists!", employeeId)
            );
        }
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeesMapper.saveEmployee(employeeEntity);
        return getEmployeeById(employeeEntity.getEmployeeId());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(Integer id) {
        EmployeeEntity employeeEntity = getEmployeeEntity(id);
        return modelMapper.map(employeeEntity, EmployeeDto.class);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployeeData(EmployeeDto employeeDto) {
        EmployeeEntity oldEmployeeEntity = getEmployeeEntity(employeeDto.getEmployeeId());
        updateJobHistory(oldEmployeeEntity, employeeDto, LocalDate.now());
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeesMapper.updateEmployee(employeeEntity);
        return getEmployeeById(employeeEntity.getEmployeeId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> getEmployeesByFilter(String firstName, String lastName, String email,
                                                  LocalDate startDate, LocalDate endDate) {
        List<EmployeeEntity> employeeEntities =
                employeesMapper.getEmployeesByParameters(firstName, lastName, email,
                        startDate, endDate);
        return modelMapper.map(
                employeeEntities,
                new TypeToken<List<EmployeeDto>>() {}.getType()
        );
    }

    private void updateJobHistory(EmployeeEntity oldEntity,
                                  EmployeeDto newDto,
                                  LocalDate endDate) {
        String oldJobId = oldEntity.getJob().getJobsId();
        String newJobId = newDto.getJob().getJobsId();
        if (!oldJobId.equals(newJobId)) {
            jobHistoryService.saveJobOfEmployee(newDto, endDate);
        }
    }

    private EmployeeEntity getEmployeeEntity(Integer id) {
        return employeesMapper.getEmployeeById(id).orElseThrow(
                () -> new RuntimeException(
                        String.format("Employee with id=%s was not found!", id)
                )
        );
    }
}
