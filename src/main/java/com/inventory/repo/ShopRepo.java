package com.inventory.repo;

import com.inventory.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShopRepo extends JpaRepository<ShopEntity, Long> {

}
