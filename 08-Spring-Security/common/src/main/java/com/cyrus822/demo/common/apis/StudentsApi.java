package com.cyrus822.demo.common.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;
import com.cyrus822.demo.common.domains.Students;

public interface StudentsApi {
    @GetMapping("/")
    public List<Students> getAllStudetns();

    @GetMapping("/retrieve/{stdId}")
    public Optional<Students> retrieve(@PathVariable("stdId") Integer stdId);        

    @PostMapping("/create")
    public Students create(@RequestBody Students newStudents);
}