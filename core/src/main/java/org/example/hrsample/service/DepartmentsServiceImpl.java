package org.example.hrsample.service;

import lombok.RequiredArgsConstructor;
import org.example.hrsample.dao.DepartmentsMapper;
import org.example.hrsample.dto.DepartmentDto;
import org.example.hrsample.dto.LocationsDto;
import org.example.hrsample.dto.ManagerDto;
import org.example.hrsample.entity.DepartmentEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Departments service implementation.
 */
@Service
@RequiredArgsConstructor
public class DepartmentsServiceImpl implements DepartmentsService {
    private final DepartmentsMapper departmentsMapper;
    private final EmployeesService employeesService;
    private final LocationsService locationsService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public DepartmentDto addNewDepartment(DepartmentDto departmentDto) {
        Integer departmentId = departmentDto.getDepartmentId();
        if (departmentsMapper.findDepartmentById(departmentId).isPresent()) {
            throw new RuntimeException(
                    String.format("Department with id=%d already exists!", departmentId)
            );
        }
        checkIfAllDepartmentPropertiesPresent(departmentDto);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDto,
                DepartmentEntity.class);
        departmentsMapper.insertDepartment(departmentEntity);
        return getDepartmentById(departmentEntity.getDepartmentId());
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        Integer departmentId = departmentDto.getDepartmentId();
        if (!departmentsMapper.findDepartmentById(departmentId).isPresent()) {
            throw new RuntimeException(
                    String.format("There is no department with id = %d to update!", departmentId)
            );
        }
        checkIfAllDepartmentPropertiesPresent(departmentDto);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDto,
                DepartmentEntity.class);
        departmentsMapper.updateDepartment(departmentEntity);
        return getDepartmentById(departmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto getDepartmentById(Integer id) {
        DepartmentEntity departmentEntity = departmentsMapper.findDepartmentById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                String.format("Department with id=%s was not found!", id)
                        )
                );
        return modelMapper.map(departmentEntity, DepartmentDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDto> getDepartmentsByNameAndManagerSurname(String departmentName, String managerSurname) {
        List<DepartmentEntity> departmentEntities = departmentsMapper.getDepartmentsByNameAndManagerSurname(
                departmentName,
                managerSurname
        );
        return modelMapper.map(
                departmentEntities,
                new TypeToken<List<DepartmentDto>>() {}.getType()
        );
    }

    private boolean checkIfAllDepartmentPropertiesPresent(DepartmentDto departmentDto) {
        ManagerDto managerDto = departmentDto.getManager();
        LocationsDto locationsDto = departmentDto.getLocation();
        if(managerDto == null) {
            throw new RuntimeException("Manager field shouldn't be null!");
        }
        if(locationsDto == null) {
            throw new RuntimeException("Location field shouldn't be null!");
        }
        employeesService.getEmployeeById(managerDto.getEmployeeId());
        locationsService.getLocationById(locationsDto.getLocationId());
        return true;
    }
}
