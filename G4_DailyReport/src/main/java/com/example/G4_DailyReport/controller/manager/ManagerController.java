package com.example.G4_DailyReport.controller.manager;

import com.example.G4_DailyReport.bean.ReportBean;
import com.example.G4_DailyReport.controller.manager.request.ReportFilter;
import com.example.G4_DailyReport.service.ReportService;


import com.example.G4_DailyReport.repository.DepartmentRepository;
import com.example.G4_DailyReport.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.G4_DailyReport.model.User;
import com.example.G4_DailyReport.service.ManagerService;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
	@Autowired

	ManagerService managerService;

	@Autowired
	private ManagerSingleton managerSingleton;


	@ModelAttribute("user")
	public User loadCurrentUser() {
		// giả sử đây là manager khi đăng nhập
		return managerSingleton.getUser();
	}

	@GetMapping("/departments")
	public String managerHomepage() {
		return "managers/index";
	}

	@GetMapping("/departments/listEmployee")
	public String managerDepartments(Model model, @RequestParam(defaultValue = "0") int page) {
		model.addAttribute("usersPage",
				managerService.getAllUserInDepartment(loadCurrentUser().getDepartment().getId().toString(), page));
		return "managers/list-employee-in-depart";
	}

	@GetMapping("/departments/addEmployee")
	public String managerAddEmployee(Model model, @RequestParam(defaultValue = "0") int page) {
		model.addAttribute("usersPage", managerService.getAllUserNoDepartment(page));
		return "managers/list-employees-toadd";
	}

	@GetMapping("/departments/user")
	public String addEmployee(String userId) {
		managerService.getUser(userId).setDepartment(loadCurrentUser().getDepartment());
		int currentPage = managerService.save(managerService.getUser(userId));
		return "redirect:/manager/departments/addEmployee?page=" + currentPage;
	}

	@GetMapping("/departments/delete")
	public String deleteEmployee(String userId) {
		int currentPage = managerService.delete(managerService.getUser(userId));
		return "redirect:/manager/departments/listEmployee?page=" + currentPage;
	}
}
