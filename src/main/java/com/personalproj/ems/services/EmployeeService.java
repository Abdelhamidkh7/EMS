package com.personalproj.ems.services;

import com.personalproj.ems.models.Employee;
import com.personalproj.ems.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public boolean deleteEmployee(long id){
        if(employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }


        return false;
    }
    public Employee updateEmployee(Long id, String name, String email) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(name);
            employee.setEmail(email);
            return employeeRepository.save(employee); // Save the updated employee
        } else {
            return null; // Return null if employee not found
        }
    }

    public Boolean createEmployee(String name,String email){
        if(employeeRepository.findByEmail(email).isEmpty()) {
            Employee emp = new Employee();
            emp.setName(name);
            emp.setEmail(email);
            employeeRepository.save(emp);
            return true;
        }
        else return false;
    }

    public Employee getEmployee(long id){
        return employeeRepository.findById(id).orElseThrow((() -> new IllegalArgumentException("Invalid emp ID")));
    }
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
}
