package com.crudApplication.crudApp.EmployeeController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.crudApplication.crudApp.EmployeeService.EmployeeService;
import com.crudApplication.crudApp.model.Employee;


@Controller
public class EmployeeController {
	@Autowired
	EmployeeService empService;
	
	
	@GetMapping("/")
	public String showHome(Model model)
	{
		model.addAttribute("listEmployees", empService.getAllEmployees());
		return findPaginated(1, model);
        
		
	}
	
	@GetMapping("/showNewEmployeeForm")
	 public String showNewEmployeeForm(Model model) {
	     
	     Employee employee = new Employee();
	     model.addAttribute("employee", employee);
	     return "new_employee";
	 }
	
	
	@PostMapping("/saveEmployee")
	 public String saveEmployee(@ModelAttribute("employee") Employee employee) {
	     // save employee to database
	     empService.saveEmployee(employee);
	     return "redirect:/";
	 }
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
	 
	 
	 Employee employee = empService.getEmployeeById(id);
	 
	 
	 model.addAttribute("employee", employee);
	 return "updateemployee";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
	    int pageSize = 5;

	    Page < Employee > page = empService.findPaginated(pageNo, pageSize);
	    List < Employee > listEmployees = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("listEmployees", listEmployees);
	    return "index";
	}

}
