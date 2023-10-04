package com.example.G4_DailyReport.repository;

import com.example.G4_DailyReport.model.Project;
import com.example.G4_DailyReport.model.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, UUID> {
    List<ProjectMember> findByUserId(UUID userId);
}
