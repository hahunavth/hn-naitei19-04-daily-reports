package com.example.G4_DailyReport.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "project_jobs")
public class ProjectJob extends BaseEntity {
    private String content;

    @ManyToOne
    @JoinColumn(name = "project_process_id")
    private ProjectProcess projectProcess;

    @OneToMany(mappedBy = "projectJob")
    private List<MemberJobsProgress> memberJobsProgressList;
}
