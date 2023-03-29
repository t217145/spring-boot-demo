package com.cyrus822.jpademo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.cyrus822.jpademo.models.Contacts;
import com.cyrus822.jpademo.repos.ContactsRepo;

@Component
public class App01 implements CommandLineRunner {

    @Autowired
    private ContactsRepo repo;

    @Override
    public void run(String... args) throws Exception {
        //Create
        Contacts newContacts = new Contacts(0, "Cyrus", "12345678", "cyrus@cyrus-sir.com");
        repo.save(newContacts);

        Contacts newContacts2 = new Contacts(0, "Mandy", "987654321", "mandy@cyrus-sir.com");
        repo.save(newContacts2);        

        List<Contacts> allContacts2 = repo.findName("Mandy");
        allContacts2.forEach(System.out::println);

        //Retrieve
        List<Contacts> allContacts = repo.findAll();
        allContacts.forEach(System.out::println);

        //Update
        Optional<Contacts> myContactsOptional = repo.findById(1);
        if(myContactsOptional.isPresent()){
            Contacts myContacts = myContactsOptional.get();
            myContacts.setName("Cyrus Cheng");
            repo.save(myContacts);
        } else {
            System.out.printf("Record with id [%d] not exists! %n", 1);
        }

        //Pause
        System.out.println("Go to H2 DB console to check the update");
        System.in.read();

        //Delete
        repo.deleteById(1);
        System.out.printf("Record with id [%d] deleted! %n", 1);
    }
    
}