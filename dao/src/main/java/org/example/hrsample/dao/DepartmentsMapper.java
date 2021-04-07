package org.example.hrsample.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.hrsample.entity.DepartmentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface DepartmentsMapper {
    void insertDepartment(DepartmentEntity departmentEntity);

    Optional<DepartmentEntity> findDepartmentById(Integer id);

    void updateDepartment(DepartmentEntity departmentEntity);

    List<DepartmentEntity> getDepartmentsByNameAndManagerSurname(
            @Param("departmentName") String departmentName,
            @Param("managerSurname") String managerSurname
    );
}
