package com.example.G4_DailyReport.controller;

import com.example.G4_DailyReport.entity.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/departments")
public class DepartmentController implements ICrudController<Object, UUID>{
    @Override
    @GetMapping("")
    public String index(Model model) {
        // todo: query and add list of departments to model
        List<Department> departments = List.of(
                new Department(UUID.randomUUID(), "IT", "IT Department"),
                new Department(UUID.randomUUID(), "HR", "HR Department")
        );
        model.addAttribute("departments", departments);
        return "departments/index";
    }

    @Override
    @GetMapping("/new")
    public String add(Model model) {
        // todo:
        return "departments/new";
    }

    @Override
    @GetMapping("/{id}/edit")
    public String edit(Model model, UUID id) {
        return "departments/edit";
    }

    @Override
    @GetMapping("/{id}/show")
    public String show(Model model, UUID id) {
        return null;
    }

    @Override
    @RequestMapping(
            value = "/{id}",
            method = {RequestMethod.PUT, RequestMethod.PATCH}
    )
    public String update(Model model, Object o) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public String delete(Model model, UUID id) {
        return null;
    }

    @Override
    @PostMapping("/create")
    public String create(Model model, Object o) {
        return null;
    }


}
