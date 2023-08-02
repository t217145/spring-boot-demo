package com.cyrus822.demo.GraphQL.Basic.backend.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cyrus822.demo.GraphQL.Basic.backend.domains.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {
    @Query("select o from Orders o, OrderDetails od, Items i where i.itemCode = :itemCode and i.itemCode = od.itemCode and od.orderId = o.orderId")
    List<Orders> getOrdersbyItemCode(@Param("itemCode") String itemCode);
}