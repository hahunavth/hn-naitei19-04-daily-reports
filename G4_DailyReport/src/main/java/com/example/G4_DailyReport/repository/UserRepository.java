package com.example.G4_DailyReport.repository;

import com.example.G4_DailyReport.model.Department;
import com.example.G4_DailyReport.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	Page<User> findByDepartment(Department department, Pageable pageable);
    Page<User> findByDepartmentIsNull(Pageable pageable);
    List<User> findByDepartmentIsNull();
    List<User> findByDepartment(Department department);

}
