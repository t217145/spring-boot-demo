package com.cyrus822.demo.GraphQL.Basic.backend.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cyrus822.demo.GraphQL.Basic.backend.domains.Items;
import com.cyrus822.demo.GraphQL.Basic.backend.domains.OrderDetails;
import com.cyrus822.demo.GraphQL.Basic.backend.domains.Orders;
import com.cyrus822.demo.GraphQL.Basic.backend.repos.ItemsRepo;
import com.cyrus822.demo.GraphQL.Basic.backend.repos.OrderDetailsRepo;
import com.cyrus822.demo.GraphQL.Basic.backend.repos.OrdersRepo;

@RestController
public class OrdersController {
    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private ItemsRepo itemsRepo;

    @QueryMapping
    public Orders getOrdersById(@Argument int orderId){
        Optional<Orders> oOrders = ordersRepo.findById(orderId); 
        return oOrders.isPresent() ? oOrders.get() : null;
    }

    @QueryMapping
    public List<Orders> getOrdersByItemCode(@Argument String itemCode) {
        return ordersRepo.getOrdersbyItemCode(itemCode);
    }

    @SchemaMapping(typeName="OrderDetails", field="item")
    public Items getItems(OrderDetails od){
        Optional<Items> oItems = itemsRepo.findAll().stream().filter(i -> i.getItemCode()
        .equals(od.getItemCode())).findFirst();
        return oItems.isPresent() ? oItems.get() : null;
    }

    @SchemaMapping(typeName="Orders", field="orderDetail")
    public OrderDetails getOrderDetails(Orders o){
        Optional<OrderDetails> oOrderDetails = orderDetailsRepo.findAll().stream().filter(od -> od.getOrderId() == o.getOrderId()).findFirst();
        return oOrderDetails.isPresent() ? oOrderDetails.get() : null;
    }
}