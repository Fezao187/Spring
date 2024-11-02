package net.JavaGuides.ems.impl;

import lombok.AllArgsConstructor;
import net.JavaGuides.ems.dto.EmployeeDto;
import net.JavaGuides.ems.entity.Employee;
import net.JavaGuides.ems.mapper.EmployeeMapper;
import net.JavaGuides.ems.repository.EmployeeRepository;
import net.JavaGuides.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = new EmployeeMapper().mapToEmployee(employeeDto);
        Employee savedEmployee=employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
}
