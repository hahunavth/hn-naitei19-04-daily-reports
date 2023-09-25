package com.example.G4_DailyReport;

import com.example.G4_DailyReport.model.Department;
import com.example.G4_DailyReport.model.Position;
import com.example.G4_DailyReport.model.User;
import com.example.G4_DailyReport.repository.DepartmentRepository;
import com.example.G4_DailyReport.repository.PositionRepository;
import com.example.G4_DailyReport.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class G4DailyReportApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    DepartmentRepository departmentRepository;


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


}
