package com.example.G4_DailyReport.controller;

import org.springframework.ui.Model;

public interface ICrudController <E, I> {
    // ui view
    String index(Model model);
    String add(Model model);
    String edit(Model model, I id);
    String show(Model model, I id);
    // api
    String update(Model model, E e);
    String create(Model model, E e);
    String delete(Model model, I id);
}
