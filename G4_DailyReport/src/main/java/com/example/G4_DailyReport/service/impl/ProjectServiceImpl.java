package com.example.G4_DailyReport.service.impl;




import com.example.G4_DailyReport.exception.IdNotFoundException;

import com.example.G4_DailyReport.model.Project;
import com.example.G4_DailyReport.repository.ProjectRepository;
import com.example.G4_DailyReport.service.ProjectService;
import com.example.G4_DailyReport.util.CurrentUserUtil;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

import java.util.UUID;



@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<Project> findPaginated(Pageable pageable, String name) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Project> list;

        List<Project> projects = retrieveProjects(name);

        if (projects.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, projects.size());
            list = projects.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.DESC, "createdAt")), projects.size());
    }

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project findById(UUID id) {
        return projectRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Id not found " + id));
    }

    @Override
    public Project update(UUID id, Project project) {
        Project existingUser = projectRepository.findById(id).orElseThrow(()-> new IdNotFoundException("Project id not found" + id));
        Condition<Object, Object> notNull = ctx -> ctx.getSource() != null;
        modelMapper.getConfiguration().setPropertyCondition(notNull);
        modelMapper.map(project,existingUser);
        projectRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void delete(UUID id) {
        projectRepository.deleteById(id);
    }

    private List<Project> retrieveProjects(String name) {
        //TODO: lấy thông tin project được tạo bởi người dùng đang đăng nhập vào hệ thống
        List<Project> projects = projectRepository.findByIdSorted(CurrentUserUtil.getCurrentUser().getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found " + CurrentUserUtil.getCurrentUser().getUsername()));

        List<Project> projects1 = projects.stream().filter(project -> project.getName().contains(name)).toList();

        return projects1;

    }
}
