package com.inventory.repo;


import com.inventory.dto.GetInventoryDto;
import com.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventroryRepo extends JpaRepository<Inventory, Long> {

    @Query("SELECT i.id as id, i.name as name, i.quantity as quantity, c.name as categoryName, s.name as shopName " +
            "FROM Inventory i " +
            "JOIN i.categoryId c " +
            "JOIN i.shopId s " +
            "WHERE i.id=:id")
    GetInventoryDto findDetailsById(@Param("id") Long id);


    @Query("SELECT i.id as id, i.name as name, i.quantity as quantity, c.name as categoryName, s.name as shopName " +
            "FROM Inventory i " +
            "JOIN i.categoryId c " +
            "JOIN i.shopId s")
    List<GetInventoryDto> findAllDetails();
}
