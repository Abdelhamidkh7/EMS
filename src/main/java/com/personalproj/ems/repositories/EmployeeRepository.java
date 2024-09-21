package com.personalproj.ems.repositories;

import com.personalproj.ems.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    public Optional<Employee> findByEmail(String email);
}
