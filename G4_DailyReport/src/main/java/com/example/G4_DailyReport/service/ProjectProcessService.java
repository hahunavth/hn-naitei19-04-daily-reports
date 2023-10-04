package com.example.G4_DailyReport.service;

import com.example.G4_DailyReport.model.Project;
import com.example.G4_DailyReport.model.ProjectProcess;

import java.util.UUID;

public interface ProjectProcessService {

    ProjectProcess save(ProjectProcess projectProcess);

    ProjectProcess findById(UUID id);

    ProjectProcess update(UUID id, ProjectProcess project);

    void delete(UUID id);
}
