package com.cyrus822.demo.ejb.simple.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import comps368.u6.basic.DemoBeanRemote;

@Controller
public class SimpleController {

    private final DemoBeanRemote helloBean;

    public SimpleController(DemoBeanRemote helloBean) {
        this.helloBean = helloBean;
    }

    @GetMapping({ "", "/", "/index" })
    public String index(ModelMap m) {
        m.addAttribute("message", "");
        return "index";
    }

    @PostMapping({ "", "/", "/index" })
    public String index(ModelMap m, @RequestParam("yourname") String yourname) {
        m.addAttribute("message", helloBean.SayHello(yourname));
        return "index";
    }
}