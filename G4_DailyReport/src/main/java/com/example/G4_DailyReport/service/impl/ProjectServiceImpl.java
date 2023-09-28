package com.example.G4_DailyReport.service.impl;


import com.example.G4_DailyReport.model.Project;
import com.example.G4_DailyReport.repository.ProjectRepository;
import com.example.G4_DailyReport.service.ProjectService;
import com.example.G4_DailyReport.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

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

    private List<Project> retrieveProjects(String name) {
        //TODO: lấy thông tin project được tạo bởi người dùng đang đăng nhập vào hệ thống
        List<Project> projects = projectRepository.findByIdSorted(CurrentUserUtil.getCurrentUser().getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found " + CurrentUserUtil.getCurrentUser().getUsername()));
        return projects.stream().filter(project -> project.getName().contains(name)).toList();
    }
}
