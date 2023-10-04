package com.example.G4_DailyReport.model;

import com.example.G4_DailyReport.enums.JobStatus;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
@Table(name = "member_jobs_progresses")
public class MemberJobsProgress extends BaseEntity{
    private boolean tempStatus;
    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_jobs_id")
    private ProjectJob projectJob;
}
