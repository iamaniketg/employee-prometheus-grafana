package com.example.Employee_prom.controller;

import com.example.Employee_prom.model.Employee;
import com.example.Employee_prom.service.EmployeeService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final Counter getAllCounter;
    private final Counter getByIdCounter;
    private final Counter createCounter;
    private final Counter updateCounter;

    public EmployeeController(EmployeeService employeeService, MeterRegistry meterRegistry) {
        this.employeeService = employeeService;
        this.getAllCounter = Counter.builder("employee_api_hits_total")
                .description("Total hits for GET /employees")
                .tag("endpoint", "/employees")
                .register(meterRegistry);

        this.getByIdCounter = Counter.builder("employee_api_hits_total")
                .description("Total hits for GET /employees/{id}")
                .tag("endpoint", "/employees/{id}")
                .register(meterRegistry);

        this.createCounter = Counter.builder("employee_api_hits_total")
                .description("Total hits for POST /employees")
                .tag("endpoint", "/employees")
                .tag("method", "POST")
                .register(meterRegistry);
        this.updateCounter = Counter.builder("employee_api_hits_total")
                .description("Total hits for PUT /employees/{id}")
                .tag("endpoint", "/employees/{id}")
                .tag("methods", "PUT")
                .register(meterRegistry);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        getAllCounter.increment();
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        getByIdCounter.increment();
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        createCounter.increment();
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        updateCounter.increment();
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
