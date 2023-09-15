package com.inventory.Repository;

import com.inventory.model.LocalUser;
import com.inventory.model.WebOrder;
import com.inventory.model.WebOrderQuantities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface WebOrderRepository extends JpaRepository<WebOrder,Long> {
    List<WebOrder> findByUser(LocalUser user);
}
