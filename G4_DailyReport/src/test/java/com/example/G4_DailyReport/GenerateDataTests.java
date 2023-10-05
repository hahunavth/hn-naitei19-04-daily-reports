package com.example.G4_DailyReport;

import com.example.G4_DailyReport.enums.ProjectStatus;
import com.example.G4_DailyReport.enums.Role;
import com.example.G4_DailyReport.model.*;
import com.example.G4_DailyReport.repository.*;
import com.example.G4_DailyReport.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class GenerateDataTests {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ProjectProcessRepository projectProcessRepository;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectMemberRepository projectMemberRepository;

    @Test
//    @Transactional
    public void testGenerateData() {
        // reset database
        reportRepository.deleteAll();
        projectMemberRepository.deleteAll();
        projectProcessRepository.deleteAll();
        projectRepository.deleteAll();
        userRepository.deleteAll();
        departmentRepository.deleteAll();
        positionRepository.deleteAll();

        // load data
        List<Department> departments = this.loadDepartment();
        List<Position> positions = this.loadPosition();
        List<User> users = this.loadUserData();
        List<Project> projects = this.loadProject();
    }

    private Position createPosition(String name) {
        Position position = new Position();
        position.setName(name);
        position = positionRepository.save(position);
        return position;
    }

    private Department createDepartment(String name) {
        Department department = new Department();
        department.setImage("https://www.w3schools.com/howto/img_avatar.png");
        department.setName(name);
        department.setDescription("This is " + name + " department");
        department = departmentRepository.save(department);
        return department;
    }

    private ProjectMember createProjectMember(Project project, User user) {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProject(project);
        projectMember.setUser(user);
//        projectMemberRepository.flush();
        projectMemberRepository.save(projectMember);
        return projectMember;
    }

//    private ProjectJob createProjectJob(ProjectProcess projectProcess, String name, String description, int order) {
//        ProjectJob projectJob = new ProjectJob();
//        projectJob.setProjectProcess(projectProcess);
//        projectJob.setCreatedBy(projectProcess.getCreatedBy());
//        projectJob.setUpdatedBy(projectProcess.getCreatedBy());
//        return projectJob;
//    }

    private ProjectProcess createProjectProcess(Project project, String name, String description, int order) {
        ProjectProcess projectProcess = new ProjectProcess();
        projectProcess.setProject(project);
        projectProcess.setName(name);
        projectProcess.setDescription(description);
        projectProcess.setCreatedBy(project.getCreatedBy());
        projectProcess.setUpdatedBy(project.getCreatedBy());
//        projectProcess.setProjectJobs(List.of(
//                this.createProjectJob(projectProcess, "Job 1", "Description 1", 1),
//                this.createProjectJob(projectProcess, "Job 2", "Description 2", 2),
//                this.createProjectJob(projectProcess, "Job 3", "Description 3", 3)
//        ));
        projectProcess = projectProcessRepository.save(projectProcess);
        return projectProcess;
    }

    private Project createProject(String name, String createdByUsername, List<String> memberUsernames) {
        Project project = new Project();
        project.setName(name);
        project.setCreatedBy(createdByUsername);
        project.setUpdatedBy(createdByUsername);
        project.setStartDate(LocalDate.of(2021, 1, 1));
        project.setEndDate(LocalDate.of(2023, 12, 31));
        project.setStatus(ProjectStatus.IN_PROGRESS);
        project = projectRepository.save(project);

        this.createProjectMember(project, userRepository.findByUserName(createdByUsername).orElseThrow());
        for (String username : memberUsernames) {
            User user = userRepository.findByUserName(username).orElseThrow();
            this.createProjectMember(project, user);
        }
        return project;
    }

    private User createUser(Role role, String name, String userName, String password, String avatar, LocalDate dateOfBirth, String departmentName, String positionName) {
        User user = new User();
        user.setName(name);
        user.setUserName(userName);
        user.setPassword(passwordEncoder.encode(password));
        user.setAvatar(avatar);
        user.setDateOfBirth(dateOfBirth);
        user.setRole(role);
        user.setDepartment(departmentRepository.findByName(departmentName));
        user.setPosition(positionRepository.findByName(positionName));
        userRepository.save(user);
        return user;
    }

    private List<Position> loadPosition() {
        return List.of(
                this.createPosition("Internship"),
                this.createPosition("Manager"),
                this.createPosition("Leader"),
                this.createPosition("Member"),
                this.createPosition("Tester"),
                this.createPosition("Developer"),
                this.createPosition("Designer"),
                this.createPosition("Accountant"),
                this.createPosition("HR"),
                this.createPosition("CEO"),
                this.createPosition("CTO"),
                this.createPosition("CFO")
        );
    }

    private List<Department> loadDepartment() {
        return List.of(
                this.createDepartment("IT"),
                this.createDepartment("HR"),
                this.createDepartment("Accounting"),
                this.createDepartment("Marketing"),
                this.createDepartment("Sales"),
                this.createDepartment("Management")
        );
    }

    private List<User> loadUserData() {
        return List.of(
                this.createUser(Role.ROLE_ADMIN, "Vu Thanh Ha", "vu.thanh.ha@sun-asterisk.com", "123456", "https://www.w3schools.com/howto/img_avatar.png", LocalDate.of(2004, 1, 2), "It", "Leader"),
                this.createUser(Role.ROLE_MANAGER, "Vu Thanh Ha Hust", "ha.vt194547@sis.hust.edu.vn", "123456", "https://www.w3schools.com/howto/img_avatar.png", LocalDate.of(2004, 1, 2), "It", "Leader"),
                this.createUser(Role.ROLE_MANAGER, "Pham Nhat Sang", "pham.nhat.sang@sun-asterisk.com", "123456", "https://www.w3schools.com/howto/img_avatar.png", LocalDate.of(1990, 2, 1), "It", "Manager"),
                this.createUser(Role.ROLE_MANAGER, "Nguyen Huy Linh", "nguyen.huy.linh@sun-asterisk.com", "123456", "https://www.w3schools.com/howto/img_avatar.png", LocalDate.of(2002, 1, 1), "It", "Developer"),
                this.createUser(Role.ROLE_USER, "Le Trong Nghia", "le.trong.nghia@sun-asterisk.com", "123456", "https://www.w3schools.com/howto/img_avatar.png", LocalDate.of(2000, 1, 1), "It", "Accountant"),
                this.createUser(Role.ROLE_USER, "Vu Thanh Ha 2", "vuthanhha.2001@gmail.com", "123456", "https://www.w3schools.com/howto/img_avatar.png", LocalDate.of(2001, 1, 1), "It", "Internship"),
                this.createUser(Role.ROLE_USER, "Nguyen Van A", "nguyenvana@gmail.com", "123456", "https://www.w3schools.com/howto/img_avatar.png", LocalDate.of(2001, 1, 1), "It", "Internship"),
                this.createUser(Role.ROLE_USER, "Nguyen Van B", "nguyenvanb@gmail.com", "123456", "https://www.w3schools.com/howto/img_avatar.png", LocalDate.of(2001, 1, 1), "It", "Internship"),
                this.createUser(Role.ROLE_USER, "Nguyen Van C", "nguyenvanc@gmail.com", "123456", "https://www.w3schools.com/howto/img_avatar.png", LocalDate.of(2001, 1, 1), "It", "Internship")
        );
    }

    private List<Project> loadProject() {
        return List.of(
                this.createProject("Sang's project", "pham.nhat.sang@sun-asterisk.com",
                        List.of("le.trong.nghia@sun-asterisk.com",
                                "vuthanhha.2001@gmail.com",
                                "nguyenvana@gmail.com"
                        )),
                this.createProject("Linh's Project", "nguyen.huy.linh@sun-asterisk.com",
                        List.of("le.trong.nghia@sun-asterisk.com",
                                "vuthanhha.2001@gmail.com",
                                "nguyenvana@gmail.com"
                        )),
                this.createProject("Ha's Project", "ha.vt194547@sis.hust.edu.vn",
                        List.of("le.trong.nghia@sun-asterisk.com",
                                "vuthanhha.2001@gmail.com",
                                "nguyenvana@gmail.com"
                        ))
                );
    }
}
