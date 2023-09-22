package com.example.G4_DailyReport.bean;

import com.example.G4_DailyReport.model.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class ReportBean {

    //additional information
    private User createdByUser;

    private LocalDate reportDate;

    private String tomorrowPlan;

    private String reasonCannotCompleteWork;

    private String actualWork;

    private User user;

    private Project project;

}
