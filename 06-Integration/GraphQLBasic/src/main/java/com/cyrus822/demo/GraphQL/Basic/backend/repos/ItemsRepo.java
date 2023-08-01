package com.cyrus822.demo.GraphQL.Basic.backend.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.cyrus822.demo.GraphQL.Basic.backend.domains.Items;

public interface ItemsRepo extends JpaRepository<Items, Integer> {
    @Query("select i from Items i where i.itemCode = :itemCode")
    Optional<Items> getItemsFromItemCode(@Param("itemCde") String itemCode);
}