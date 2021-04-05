package org.example.hrsample.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.hrsample.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface EmployeesMapper {
    Optional<EmployeeEntity> getEmployeeById(Integer id);
}
