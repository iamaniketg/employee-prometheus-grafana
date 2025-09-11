package com.example.Employee_prom.service;


import com.example.Employee_prom.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private final Map<Long, Employee> employeeMap = new HashMap<>();
    private long idCounter = 1;

    // Get all employees
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    // Get employee by id
    public Employee getEmployeeById(Long id) {
        return employeeMap.get(id);
    }

    // Create new employee
    public Employee createEmployee(Employee employee) {
        employee.setId(idCounter++);
        employeeMap.put(employee.getId(), employee);
        return employee;
    }

    // Update employee
    public Employee updateEmployee(Long id, Employee employee) {
        if (employeeMap.containsKey(id)) {
            employee.setId(id);
            employeeMap.put(id, employee);
            return employee;
        }
        return null;
    }

    // Delete employee
    public boolean deleteEmployee(Long id) {
        return employeeMap.remove(id) != null;
    }
}
