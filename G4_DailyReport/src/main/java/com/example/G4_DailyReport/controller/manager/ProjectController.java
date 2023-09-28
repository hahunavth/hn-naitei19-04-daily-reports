package com.example.G4_DailyReport.controller.manager;

import com.example.G4_DailyReport.bean.ReportBean;
import com.example.G4_DailyReport.controller.manager.request.ReportFilter;
import com.example.G4_DailyReport.enums.ProjectStatus;
import com.example.G4_DailyReport.model.Project;
import com.example.G4_DailyReport.service.ProjectService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ProjectController {

    private final ProjectService projectService;


    @PostMapping("/projects")
    public String create(@ModelAttribute("project") Project project, RedirectAttributes redirectAttributes,HttpServletRequest request){
        projectService.create(project);
        redirectAttributes.addFlashAttribute("message", "Data processed successfully!");
        return "redirect:" + request.getRequestURI();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/projects")
    public String index(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("name") Optional<String> name
    ) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        String nameProject = name.orElse("");

        Page<Project> projectPage = projectService.findPaginated(PageRequest.of(currentPage - 1, pageSize), nameProject);
        // TODO: Phân trang chi project -> hiển thị thông tin project với các tiến trình.
        model.addAttribute("page", projectPage);

        int totalPages = projectPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = generatePageNumbers(totalPages,projectPage);
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("name", name.orElse(""));

        //Active link sidbebar
        model.addAttribute("activeLink", "projects");
        //handler form
        model.addAttribute("project", new Project());
        model.addAttribute("statuses", ProjectStatus.values());
        return "managers/pages/projects";
    }

    private List<Integer> generatePageNumbers(int totalPages, Page<Project> projectPage){
        List<Integer> pageNumbers;
        if (totalPages < 11) {
            pageNumbers = IntStream.rangeClosed(2, totalPages - 1).boxed()
                    .collect(Collectors.toList());
        } else if (projectPage.getNumber() < 5) {
//                < 1 2 3 4 5 6 .... 12>
            pageNumbers = IntStream.rangeClosed(2, 6).boxed()
                    .collect(Collectors.toList());
        } else if (projectPage.getNumber() > totalPages - 5) {
//                < 1 .... 8 9 10 11 12 >
            pageNumbers = IntStream.rangeClosed(totalPages - 5, totalPages - 1).boxed()
                    .collect(Collectors.toList());
        } else {
//                < 1 ... 10 11 12 13 .. 20 >
            pageNumbers = IntStream.rangeClosed(projectPage.getNumber() - 1, projectPage.getNumber() + 3).boxed()
                    .collect(Collectors.toList());
        }
        return pageNumbers;
    }
}
