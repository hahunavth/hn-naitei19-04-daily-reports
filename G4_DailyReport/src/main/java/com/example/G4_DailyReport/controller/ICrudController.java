package com.example.G4_DailyReport.controller;

import org.springframework.ui.Model;

import java.util.Optional;

public interface ICrudController<E, I> {
    // ui view
    String index(Model model,
                 Optional<Integer> page,
                 Optional<Integer> size,
                 Optional<String> sort,
                 Optional<String> search);

    String add(Model model);

    String edit(Model model, I id);

    String show(Model model, I id);

    // api
    String update(Model model, E e);

    String create(Model model, E e);

    String delete(Model model, I id);
}
