package com.example.G4_DailyReport.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.G4_DailyReport.model.Department;
import com.example.G4_DailyReport.model.User;
import com.example.G4_DailyReport.repository.DepartmentRepository;
import com.example.G4_DailyReport.repository.UserRepository;

@Service
public class ManagerService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
    
	public User getUser(String userId) {
		return userRepository.findById(UUID.fromString(userId)).orElse(null);
	}
	public Page<User> getAllUserInDepartment(String departmentId,int page){
		int pageSize = 5;
		Optional<Department> department= departmentRepository.findById(UUID.fromString(departmentId));
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<User> pageUsers = userRepository.findByDepartment(department.get(), pageable);
		return department.isPresent()?pageUsers:Page.empty();
	}
	
	public Page<User> getAllUserNoDepartment(int page){
		int pageSize =5;
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<User> pageUsers= userRepository.findByDepartmentIsNull(pageable);
		return pageUsers.isEmpty()?Page.empty():pageUsers;
		
	}
	
	public int save(User user) {
		int page=calculatePageNumber(userRepository.findByDepartmentIsNull(), user.getId());
		userRepository.save(user);
		return page;
	}
	
	public int delete(User user) {
		int page=calculatePageNumber(userRepository.findByDepartment(user.getDepartment()), user.getId());
		user.setDepartment(null);
		userRepository.save(user);
		return page;
	}
	
	private int calculatePageNumber(List<User> userList ,UUID userId) {
	   
	    int pageSize = 5; 
	    int userIndex = -1;

	    for (int i = 0; i < userList.size(); i++) {
	        if (userList.get(i).getId().equals(userId)) {
	            userIndex = i;
	            System.out.println(i);
	            break;
	        }
	    }
	    if (userIndex >= 0) {
	        return userIndex / pageSize;
	    } else {
	        return 0;
	    }
	}

}
