package com.personalproj.ems.controllers;

import com.personalproj.ems.models.Employee;
import com.personalproj.ems.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    @GetMapping("/{id}")
    public ResponseEntity<Employee>  getEmployee(@PathVariable int id){
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @GetMapping
    public  String getAllEmployees(Model model){

        List<Employee> employees= employeeService.getAllEmployees();
        model.addAttribute("employees",employees);
        return "employeeList";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestParam String name, @RequestParam String email) {
        Employee updatedEmployee = employeeService.updateEmployee(id, name,email);
        if (updatedEmployee != null) {
          return "redirect:/employee";  // Return 200 OK with the updated employee
        } else {
            return "redirect:/employee/edit/" + id + "?error=true";  // Return 404 if the employee doesn't exist
        }
    }
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return "redirect:/employee";  // Return 204 No Content if deleted successfully
        } else {
            return "redirect:/employee?error=true";  // Return 404 if the employee doesn't exist
        }
    }
    @GetMapping("/new")
    public String newEmployee(){
        return "newEmployee";
    }
    @GetMapping("/edit/{id}")
    public String updateEmployee(@PathVariable int id, Model model){
        Employee employee = employeeService.getEmployee(id);
        if(employee != null){
            model.addAttribute("employee",employee);
            return "updateEmployee";
        }
        else return "redirect:/employee?error=true";

    }


    @PostMapping
    public String createEmployee(@RequestParam String name, @RequestParam String email) {
        boolean employeeCreated = employeeService.createEmployee(name, email);

        if (employeeCreated) {
            return "redirect:/employee";  // Redirect to employee list after successful creation
        } else {
            return "redirect:/employee/new?error=true";  // Redirect back to form with an error query param if email is duplicate
        }
    }
}
