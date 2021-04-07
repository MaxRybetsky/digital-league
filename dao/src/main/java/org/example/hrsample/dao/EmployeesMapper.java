package org.example.hrsample.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.hrsample.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface EmployeesMapper {
    Optional<EmployeeEntity> getEmployeeById(Integer id);

    void saveEmployee(EmployeeEntity employeeEntity);

    void updateEmployee(EmployeeEntity employeeEntity);

    List<EmployeeEntity> getEmployeesByParameters(@Param("firstName") String firstName,
                                                  @Param("lastName") String lastName,
                                                  @Param("email") String email,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);
}
