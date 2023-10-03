package com.example.G4_DailyReport.controller.manager;

import com.example.G4_DailyReport.model.Project;
import com.example.G4_DailyReport.model.ProjectProcess;
import com.example.G4_DailyReport.service.ProjectProcessService;
import groovy.lang.GString;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ProjectProcessController {
    private final ProjectProcessService projectProcessService;

    @GetMapping("/project-processes/{id}")
    public ResponseEntity<ProjectProcess> show(@PathVariable UUID id) {
        ProjectProcess projectProcess = projectProcessService.findById(id);
        return new ResponseEntity<>(projectProcess, HttpStatus.OK);
    }

    @PutMapping("/project-processes/{id}")
    public ResponseEntity<ProjectProcess> update(@PathVariable UUID id, @RequestBody ProjectProcess project) {
        ProjectProcess updatedProjectProcess = projectProcessService.update(id,project);
        return new ResponseEntity<>(updatedProjectProcess,HttpStatus.OK);
    }

    @DeleteMapping ("/project-processes/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        projectProcessService.delete(id);
        return new ResponseEntity<>("Delete process complete",HttpStatus.OK);
    }

}
