package com.cyrus822.demo.web.controllers;

import com.cyrus822.demo.common.domains.Students;
import com.cyrus822.demo.web.services.StudentsApiSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

@Controller
@RequestMapping({"/students","/",""})
public class StudentsController {
    @Autowired
    private StudentsApiSvc svc;

    @GetMapping({"","/","/index"})
    public String index(ModelMap m){
        m.addAttribute("allStudents", svc.getAllStudetns());
        return "index";
    }

    @GetMapping("/create")
    public String create(ModelMap m){
        m.addAttribute("newStudents", new Students());
        return "create";
    }

    @PostMapping("/create")
    public String create(ModelMap m, @Valid @ModelAttribute("newStudents") Students newStudents, BindingResult result){
        if(result.hasErrors()){
            m.addAttribute("newStudents", newStudents);
            return "create";
        }
        svc.create(newStudents);
        return "redirect:/index";
    }
}
