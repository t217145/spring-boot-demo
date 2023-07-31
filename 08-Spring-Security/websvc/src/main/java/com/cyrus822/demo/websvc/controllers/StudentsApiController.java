package com.cyrus822.demo.websvc.controllers;

import com.cyrus822.demo.common.apis.StudentsApi;
import com.cyrus822.demo.common.domains.Students;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cyrus822.demo.websvc.repositories.StudentsRepo;

@RestController
@RequestMapping(value = "/api/students")
public class StudentsApiController implements StudentsApi {

    @Autowired
    private StudentsRepo repo;

    @Override
    public List<Students> getAllStudetns() {
        return repo.findAll();
    }

    @Override
    public Optional<Students> retrieve(Integer stdId) {
        return repo.findById(stdId);
    }

    @Override
    public Students create(Students newStudents) {
        return repo.save(newStudents);
    }
    
}
