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

import java.util.List;

@SpringBootTest
class G4DailyReportApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    Department savedDepartment;
    Position savedPosition;
    User savedUser;

    @Test
    void init() {
        Department department = new Department();
        department.setName("department 17");
        savedDepartment = departmentRepository.save(department);
        System.out.println("save department");
        Position position = new Position();
        position.setName("position 17");
        savedPosition = positionRepository.save(position);
        System.out.println("save position");
        User user = new User();
        user.setName("huy linh 17");
        user.setPosition(position);
        user.setDepartment(savedDepartment);
        savedUser = userRepository.save(user);
        System.out.println("save user");
    }

    @Test
    void clean() {
        System.out.println("aftereach");
        userRepository.delete(savedUser);
        positionRepository.delete(savedPosition);
        departmentRepository.delete(savedDepartment);
    }


    @Test
    @Transactional
    void departmentUsers() {
        System.out.println("test");
        List<Department> departmentList = departmentRepository.findAll();
        Department department = departmentList.get(0);
        List<User> users = department.getUsers();
        System.out.println(users.size());
        for (User user : users)
        {
            System.out.println(user.getName());
        }
    }


}
