package com.inventory.controller;

import com.inventory.Service.OrderService;
import com.inventory.dto.OrderRequest;
import com.inventory.model.LocalUser;
import com.inventory.model.WebOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

    @GetMapping
    public List<WebOrder> getOrder(@AuthenticationPrincipal LocalUser user){

        return orderService.getOrders(user);
    }

    @PostMapping
    public WebOrder createOrder(@RequestBody OrderRequest orderRequest) {
        WebOrder createdOrder = orderService.createOrder(orderRequest);
        return createdOrder;
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<WebOrder> updateOrderById(@PathVariable Long orderId, @RequestBody WebOrder updatedOrder) {
        WebOrder order = orderService.updateOrderById(orderId, updatedOrder);

        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<WebOrder> getOrderById(@PathVariable Long orderId) {
        WebOrder order = orderService.getOrderById(orderId);

        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.noContent().build();
    }

}
