package com.example.G4_DailyReport.repository;

import com.example.G4_DailyReport.model.Project;
import com.example.G4_DailyReport.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    Project findProjectById(UUID uuid);
}
