package com.cyrus822.camel.rest.processors;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cyrus822.camel.rest.domains.SellRequest;
import com.cyrus822.camel.rest.repos.SellRequestRepo;

@Component
public class RouteHandler {

    @Autowired
    private SellRequestRepo repo;

    public List<SellRequest> getAll() {
        return repo.findAll();
    }

    public SellRequest addSellRequest(SellRequest sell) {
        return repo.save(sell);
    }
}
