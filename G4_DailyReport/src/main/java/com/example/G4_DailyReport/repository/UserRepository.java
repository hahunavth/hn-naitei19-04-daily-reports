package com.example.G4_DailyReport.repository;

import com.example.G4_DailyReport.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import org.springframework.data.domain.Pageable;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Page<User> findAllByDepartmentIdAndPositionName(UUID department, String position, Pageable pageable);
}
