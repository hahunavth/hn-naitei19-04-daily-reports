package com.example.G4_DailyReport.service;

import com.example.G4_DailyReport.model.User;
import com.example.G4_DailyReport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> findAllManagerByDepartmentId(UUID department, Pageable pageable) {
        return userRepository.findAllByDepartmentIdAndPositionName(department, "Manager", pageable);
    }

    public Page<User> findAllManagerByDepartmentIsNull(Pageable pageable) {
        return userRepository.findAllByDepartmentIsNullAndPositionName("Manager", pageable);
    }

    public Page<User> findAllManagerByDepartmentIsNull(String name, Pageable pageable) {
        return userRepository.findAllByDepartmentIsNullAndPositionNameAndNameContaining("Manager", name, pageable);
    }
}
