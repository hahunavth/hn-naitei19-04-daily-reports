package com.example.G4_DailyReport;

import com.example.G4_DailyReport.config.SecurityConfig;
import com.example.G4_DailyReport.enums.ProjectStatus;
import com.example.G4_DailyReport.model.*;
import com.example.G4_DailyReport.repository.*;
import com.example.G4_DailyReport.util.CurrentUserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.image.PixelGrabber;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class G4DailyReportApplicationTests {
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
    private SecurityConfig securityConfig;

    @Test
    @Transactional
    void departmentUsers() {
        System.out.println("test");
      /*  List<Department> departmentList = departmentRepository.findAll();
        Department department = departmentList.get(0);
        List<User> users = department.getUsers();
        System.out.println(users.size());
//        for (User user : users)
//        {
//            System.out.println(user.getName());
//        }*/
    }
    
    @Test
    @Transactional
    void dataTest() {
        System.out.println("test");
        Position position = new Position();
        position.setName("Manager");
        Position position2 = new Position();
        position2.setName("Employee");
        positionRepository.saveAndFlush(position);
        positionRepository.saveAndFlush(position2);
        Department department = new Department();
        department.setImage("/managers/img/user.jpg");
        department.setDescription("abcdggggg");
        departmentRepository.saveAndFlush(department);
        User user = new User();
        user.setAvatar("/managers/img/user.jpg");
        user.setName("Dragoon");
        user.setDateOfBirth(LocalDate.now());
        user.setDescription("Toi la Dragon");
        user.setPosition(position);
        user.setDepartment(department);

        User user2 = new User();
        user2.setAvatar("/managers/img/user.jpg");
        user2.setName("Dragoon2");
        user2.setDateOfBirth(LocalDate.now());
        user2.setDescription("Toi la Dragon2");
        user2.setPosition(position2);
        user2.setDepartment(department);
        User user3 = new User();
        user3.setAvatar("/managers/img/user.jpg");
        user3.setName("Dragoon3");
        user3.setDateOfBirth(LocalDate.now());
        user3.setDescription("Toi la Dragon3");
        user3.setPosition(position2);
        user3.setDepartment(department);

        User user4 = new User();
        user4.setAvatar("/managers/img/user.jpg");
        user4.setName("Dragoon4");
        user4.setPosition(position2);
        user4.setDateOfBirth(LocalDate.now());
        user4.setDescription("Toi la Dragon4");

        User user5 = new User();
        user5.setAvatar("/managers/img/user.jpg");
        user5.setName("Dragoon5");
        user5.setPosition(position2);
        user5.setDateOfBirth(LocalDate.now());
        user5.setDescription("Toi la Dragon5");
        User user6 = new User();
        user6.setAvatar("/managers/img/user.jpg");
        user6.setName("Dragoon6");
        user6.setPosition(position2);
        user6.setDateOfBirth(LocalDate.now());
        user6.setDescription("Toi la Dragon6");
        User user7 = new User();
        user7.setAvatar("/managers/img/user.jpg");
        user7.setName("Dragoon7");
        user7.setPosition(position2);
        user7.setDateOfBirth(LocalDate.now());
        user7.setDescription("Toi la Dragon7");
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
    }
       
       

    public void seedData() {
//        Seed department data
        Department department1 = new Department();
        department1.setName("Development");
        department1.setDescription("Development description");
        Department department2 = new Department();
        department2.setName("Human resource");
        department2.setDescription("Human resource description");
        departmentRepository.save(department1);
        departmentRepository.save(department2);
//        Seed position data
        Position position1 = new Position();
        position1.setName("Developer");
        Position position2 = new Position();
        position2.setName("CTO");
        positionRepository.save(position1);
        positionRepository.save(position2);
//        Seed user data
//        User user1 = new User();
//        user1.setName("Nguyen Huy Linh");
//        user1.setDescription("HEDSPI K67 VN03)");
//        user1.setUserName("linhhuy");
//        user1.setPassword(passwordEncoder.encode("foo"));
//        user1.setPosition(position1);
//        user1.setDepartment(department1);
//        userRepository.save(user1);
        User user1 = userRepository.findByUserName("linhhuy").orElseThrow(()->new UsernameNotFoundException("User not found exception"));
        User user2 = new User();
        user2.setName("Vu Thanh Ha");
        user2.setDescription("HEDSPU K66 VN04");
        user2.setUserName("trongnghia2");
        user2.setRoles("ROLE_MANAGER");
        user2.setPassword(passwordEncoder.encode("foo"));
        user2.setPosition(position2);
        user2.setDepartment(department2);
        userRepository.save(user2);

        Project project = new Project();
        project.setName("Trainning HEDSPU");
        project.setStartDate(LocalDate.of(2023, 8, 7));
        project.setEndDate(LocalDate.of(2023, 10, 9));
        project.setStatus(ProjectStatus.IN_PROGRESS);
        projectRepository.save(project);

//        Seed report data
//        Report report = new Report();
//        report.setReportDate(LocalDate.of(2023, 9, 20));
        String actualWork = """
                - Annotated controllers trong spring mvc  (8:45 - 11:45)
                - View technologies trong spring mvc (12:45 - 17:45)      
                """;
        String workingTime = """
                - Thời gian làm việc: W ( 8:45 - 17:45)
                - Thời gian học tiếng nhật: 0
                """;
//        report.setReasonCannotCompleteWork("""
//                - N/A
//                """);
//
//        report.setTomorrowPlan("""
//                - Học spring mvc
//                """);
//        report.setActualWork(actualWork);
//        report.setUser(user2);
//        report.setProject(project);

//        seed 100 data
        List<Report> reports = new ArrayList<>();
        Report report2;
        for (int i = 0; i < 5; i++) {
            report2 = new Report();
            report2.setCreatedBy("Nguyen Huy Linh" + i);
            report2.setReportDate(LocalDate.of(2023, 9, 30));
            report2.setTomorrowPlan("""
                    - Học spring mvc
                    """);
            report2.setReasonCannotCompleteWork("""
                    - N/A
                    """);
            report2.setWorkingTime(workingTime);
            report2.setActualWork(actualWork);
            report2.setUser(user1);
            report2.setProject(project);
            reports.add(report2);
        }
        reportRepository.saveAll(reports);
    }



    @Test
    void initUser() {

        Department department = new Department();
        department.setName("department name");
        departmentRepository.save(department);
        Position position = new Position();
        position.setName("position name");
        positionRepository.save(position);

        User user = new User();
        user.setName("Huy Linh4");
        user.setUserName("huylinh4");
        user.setPassword(passwordEncoder.encode("foo"));
        user.setRoles("ROLE_USER,ROLE_MANAGER");
        user.setPosition(position);
        user.setDepartment(department);
        userRepository.save(user);
        User user2 = new User();
        user2.setName("Huy Linh3");
        user2.setUserName("huylinh3");
        user2.setPassword(passwordEncoder.encode("boo"));
        user2.setRoles("ROLE_USER");
        user2.setPosition(position);
        user2.setDepartment(department);
        userRepository.save(user2);

    }

    @Test
    void seedReports() {

    }

}
