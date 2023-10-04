package com.example.G4_DailyReport.service.impl;

import com.example.G4_DailyReport.exception.IdNotFoundException;
import com.example.G4_DailyReport.model.ProjectJob;
import com.example.G4_DailyReport.model.ProjectProcess;
import com.example.G4_DailyReport.repository.ProjectJobRepository;
import com.example.G4_DailyReport.repository.ProjectProcessRepository;
import com.example.G4_DailyReport.service.ProjectProcessService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectProcessServiceImpl implements ProjectProcessService {
    private final ProjectProcessRepository projectProcessRepository;
    private final ProjectJobRepository projectJobRepository;
    private final ModelMapper modelMapper;
    @Override
    public ProjectProcess save(ProjectProcess projectProcess) {
        return projectProcessRepository.save(projectProcess);
    }

    @Override
    public ProjectProcess findById(UUID id) {
        return projectProcessRepository.findById(id).orElseThrow(()->new IdNotFoundException("Project process id not found!"));
    }

    @Override
    public ProjectProcess update(UUID id, ProjectProcess projectProcess) {
        ProjectProcess existingProcess = projectProcessRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Project id not found" + id));
        modelMapper.map(projectProcess,existingProcess);
        projectProcessRepository.save(existingProcess);
        return existingProcess;
    }

    @Override
    public void delete(UUID id) {
        projectProcessRepository.deleteById(id);
    }

    @Override
    public List<ProjectJob> getProjectJobs(UUID id) {
        ProjectProcess projectProcess = projectProcessRepository.findById(id).orElseThrow(()->new IdNotFoundException("Project process id not found!"));
        return projectProcess.getProjectJobs();
    }

    @Override
    public ProjectJob createJob(UUID id, ProjectJob projectJob) {
        ProjectProcess projectProcess = projectProcessRepository.findById(id).orElseThrow(()->new IdNotFoundException("Project process id not found!"));
        projectJob.setProjectProcess(projectProcess);
        return projectJobRepository.save(projectJob);
    }
}
