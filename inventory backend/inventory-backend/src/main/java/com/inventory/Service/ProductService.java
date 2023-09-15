package com.inventory.Service;

import com.inventory.dto.OrderRequest;
import com.inventory.model.Product;
import com.inventory.model.WebOrder;

import java.util.List;

public interface ProductService {
    public List<Product> getProduct();

}
