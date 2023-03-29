package com.cyrus822.entitymanagerdemo.services;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.cyrus822.entitymanagerdemo.models.Contacts;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Component
public class App01 implements CommandLineRunner {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public void run(String... args) throws Exception {
        //c
        Contacts newContacts = new Contacts(0, "Cyrus", null, "cyrus@cyrus-sir.com");
        em.persist(newContacts);
        System.out.println("Record created");

        //r
        Query q1 = em.createQuery("select c from Contacts c");
        List<Contacts> allContacts = q1.getResultList();
        allContacts.forEach(System.out::println);


        Query q2 = em.createNamedQuery("findByName");
        q2.setParameter("myname", "%Cyrus%");
        Contacts targetContacts = (Contacts)q2.getSingleResult();
        System.out.println(targetContacts.toString());

        //u
        targetContacts.setPhone("12346578");
        targetContacts.setName("Cyrus Cheng");
        System.out.println("Record updated");

        //d
        em.remove(targetContacts);
        System.out.println("Record deleted");
    }
    
}
