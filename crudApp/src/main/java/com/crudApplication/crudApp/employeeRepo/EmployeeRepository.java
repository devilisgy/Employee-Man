package com.crudApplication.crudApp.employeeRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.crudApplication.crudApp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	

}
