package com.cyrus822.basicform.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.cyrus822.basicform.models.MyObjects;
import com.cyrus822.basicform.repos.MyObjectsRepo;
import jakarta.validation.Valid;

@Controller
public class ObjectController {
    
    @Autowired
    private MyObjectsRepo repo;

    @GetMapping(value = {"", "/", "/index"})
    public String index(ModelMap m){
        m.addAttribute("allCyrus", repo.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(ModelMap m){
        m.addAttribute("newObject", new MyObjects());
        return "create";
    }

    @PostMapping("/create")
    public String create(ModelMap m, @Valid @ModelAttribute("newObject") MyObjects _obj, BindingResult results){
        if(results.hasErrors()){
            m.addAttribute("newObject", _obj);
            return "create";
        }
        repo.save(_obj);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(ModelMap m, @PathVariable("id") Integer id){
        Optional<MyObjects> _myObjectsOptional = repo.findById(id);
        if(!_myObjectsOptional.isPresent()){
            return "redirect:/index";
        }
        m.addAttribute("editObject", _myObjectsOptional.get());
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(ModelMap m, @ModelAttribute("editObject") MyObjects _obj){  
        repo.save(_obj);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        repo.deleteById(id);
        return "redirect:/index";
    }

}
