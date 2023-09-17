package com.example.G4_DailyReport.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Department implements Serializable {
    private UUID id;
    private String nane;
    private String description;
    // private User createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Department() {}
    public Department(UUID id, String name, String description) {
        this.id = id;
        this.nane = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Department(String name, String description) {
        this(UUID.randomUUID(), name, description);
    }
}
