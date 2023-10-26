package com.crudApplication.crudApp.EmployeeService;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import com.crudApplication.crudApp.employeeRepo.EmployeeRepository;
import com.crudApplication.crudApp.model.Employee;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired	
    private	EmployeeRepository empRepo;


	

	@Override
	public List<Employee> getAllEmployees() {
		
		return  empRepo.findAll();
	}




	@Override
	public void saveEmployee(Employee employee) {
		 this.empRepo.save(employee);
		
	}

	public Employee getEmployeeById(long id) {
	    Optional < Employee > optional = empRepo.findById(id);
	    Employee employee = null;
	    if (optional.isPresent()) {
	        employee = optional.get();
	    } else {
	        throw new RuntimeException(" Employee not found for id :: " + id);
	    }
	    return employee;
	}




	public Page<Employee> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		 return this.empRepo.findAll(pageable);
		
	}
	
	
	
	

	
}
