package com.example.G4_DailyReport.service;

import com.example.G4_DailyReport.model.Department;
import com.example.G4_DailyReport.model.User;
import com.example.G4_DailyReport.repository.DepartmentRepository;
import com.example.G4_DailyReport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    public Page<Department> findAll(String search, Pageable pageable) {
        Specification<Department> spec = Specification.where(
                        (root, query, criteriaBuilder) -> criteriaBuilder.or(
                                criteriaBuilder.like(root.get("name"), "%" + search + "%"),
                                criteriaBuilder.like(root.get("description"), "%" + search + "%")
                ));

        Page<Department> pagedResult = departmentRepository.findAll(
                spec,
                pageable
        );
        if (pagedResult.hasContent()) {
            return pagedResult;
        }
        return Page.empty();
    }

    public Department findDepartmentAndManagers(UUID id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department findById(UUID id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Department update(Department department) {
        return departmentRepository.save(department);
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteById(UUID id) {
        departmentRepository.deleteById(id);
    }

    public Page<User> findManagers(UUID id, Pageable pageable) {
        return userRepository.findAllByDepartmentIdAndPositionName(id, "Manager", pageable);
    }
}