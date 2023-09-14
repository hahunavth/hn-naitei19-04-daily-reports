package com.example.G4_DailyReport.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "reports")
public class Report extends BaseEntity {
    private LocalDate reportDate;

    @Column(columnDefinition = "text")
    private String tomorrowPlan;

    @Column(columnDefinition = "text")
    private String reasonCannotCompleteWork;

    @Column(columnDefinition = "text")
    private String actualWork;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

}
