package com.inventory.Repository;

import com.inventory.model.WebOrder;
import com.inventory.model.WebOrderQuantities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebOrderQuantitiesRepository extends JpaRepository<WebOrderQuantities, Long> {
    List<WebOrderQuantities> findByOrder(WebOrder order);
}
