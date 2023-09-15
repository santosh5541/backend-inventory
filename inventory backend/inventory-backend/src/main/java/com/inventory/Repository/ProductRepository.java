package com.inventory.Repository;

import com.inventory.model.LocalUser;
import com.inventory.model.Product;
import com.inventory.model.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
