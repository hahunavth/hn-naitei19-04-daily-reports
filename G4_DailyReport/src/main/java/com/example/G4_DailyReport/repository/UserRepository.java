package com.example.G4_DailyReport.repository;

import com.example.G4_DailyReport.model.Department;
import com.example.G4_DailyReport.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.Optional;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserName(String username);
    Page<User> findByDepartment(Department department, Pageable pageable);
    Page<User> findByDepartmentIsNull(Pageable pageable);
    List<User> findByDepartmentIsNull();
    List<User> findByDepartment(Department department);
    Page<User> findAllByDepartmentIdAndPositionName(UUID department, String position, Pageable pageable);
    Page<User> findAllByDepartmentIsNullAndPositionName(String position, Pageable pageable);
    Page<User> findAllByDepartmentIsNullAndPositionNameAndNameContaining(String position, String name, Pageable pageable);
}
