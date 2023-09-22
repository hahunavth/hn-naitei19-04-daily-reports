package com.example.G4_DailyReport.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity{
    private String userName;

    private String password;

    private String name;

    private String roles;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String avatar;

    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name="department_id"
            , nullable=true
    )
    private Department department;

    @ManyToOne
    @JoinColumn(name="position_id"
//            , nullable=false
    )
    private Position position;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Report> reports;

    @OneToMany(mappedBy = "user")
    private List<ProjectMember> projectMembers;

    @OneToMany(mappedBy = "user")
    private List<MemberJobsProgress> memberJobsProgresses;
}