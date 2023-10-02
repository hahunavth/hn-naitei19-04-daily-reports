package com.example.G4_DailyReport.controller.admin;

import com.example.G4_DailyReport.service.ExcelService;
import com.example.G4_DailyReport.util.Constants;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("")
    public String index() {
        return "admin/users/index";
    }

    @PostMapping(value = "/importExcel", consumes = {"multipart/form-data"})
    public String importExcel(
            @RequestPart("file") MultipartFile file,
            HttpServletResponse response
    ) {
        if (!Constants.FILE_CONTENT_TYPE_ALLOWED.contains(file.getContentType())) {
            return "redirect:/admin/users?error=1";
        }
        int created = excelService.createFromExcel(file, response);
        return "redirect:/admin/users?success=" + "Successfully imported " + created + " user(s)";
    }
}
