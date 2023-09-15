package com.example.G4_DailyReport.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "departments")
public class Department extends BaseEntity {
    private String name;

    @Column(columnDefinition = "text")
    private String image;

    @Column(columnDefinition = "text")
    private String description;


    @OneToMany(mappedBy = "department")
    private List<User> users;
}
