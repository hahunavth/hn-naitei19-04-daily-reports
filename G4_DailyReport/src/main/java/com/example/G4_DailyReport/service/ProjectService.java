package com.example.G4_DailyReport.service;
import com.example.G4_DailyReport.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;
public interface ProjectService {
    Page<Project> findPaginated(Pageable pageable, String name);

    Project create(Project project);


    Project findById(UUID id);

    Project update(UUID id, Project project);

    void delete(UUID id);
}
