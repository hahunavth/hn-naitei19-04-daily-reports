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


    @Test
    @Transactional
    void departmentUsers() {
        System.out.println("test");
        List<Department> departmentList = departmentRepository.findAll();
        Department department = departmentList.get(0);
        List<User> users = department.getUsers();
        System.out.println(users.size());
//        for (User user : users)
//        {
//            System.out.println(user.getName());
//        }
    }


}
