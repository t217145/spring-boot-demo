package com.cyrus822.jpademo.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cyrus822.jpademo.models.Contacts;

public interface ContactsRepo extends JpaRepository<Contacts, Integer> {
    @Query("select c from Contacts c where c.name = :myName")
    List<Contacts> findName(@Param("myName") String targetName);

    List<Contacts> findByName(String name);

    List<Contacts> findByPhone(String phone);
}
