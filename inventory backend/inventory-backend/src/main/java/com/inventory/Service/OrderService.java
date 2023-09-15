package com.inventory.Service;

import com.inventory.dto.OrderRequest;
import com.inventory.model.LocalUser;
import com.inventory.model.WebOrder;

import java.util.List;

public interface OrderService {
    public List<WebOrder> getOrders(LocalUser user);
    public List<WebOrder> getOrdersWithQuantities(LocalUser user);

    public WebOrder createOrder(OrderRequest orderRequest);

    WebOrder getOrderById(Long orderId);

    WebOrder updateOrderById(Long orderId, WebOrder updatedOrder);

    void deleteOrderById(Long orderId);

}
