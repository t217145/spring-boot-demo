package com.cyrus822.demo.websvc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cyrus822.demo.common.domains.Students;

public interface StudentsRepo extends JpaRepository<Students, Integer>{
    @Query("select s from Students s where s.id = :id")
    Optional<Students> findStudentsByStdId(Integer id);
}
