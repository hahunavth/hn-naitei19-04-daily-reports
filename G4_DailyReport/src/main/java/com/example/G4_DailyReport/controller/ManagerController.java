package com.example.G4_DailyReport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.G4_DailyReport.repository.UserRepository;

@Controller
public class ManagerController {
	@Autowired
    private UserRepository userRepository;
	
	 @GetMapping("/manager/departments")
	    public String managerHomepage() {
	     
	        return "managers/index";
	    }
	 
	 @GetMapping("/manager/departments/listEmployee")
	    public String managerDepartments() {
	     
	        return "managers/list-employee-in-depart";
	    }
	 
	 @GetMapping("/manager/departments/addEmployee")
	    public String managerAddEmployee() {
	     
	        return "managers/list-employees-toadd";
	    }
	 
}
