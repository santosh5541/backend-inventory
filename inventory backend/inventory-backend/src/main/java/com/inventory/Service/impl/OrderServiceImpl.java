    package com.inventory.Service.impl;

    import com.inventory.Repository.WebOrderQuantitiesRepository;
    import com.inventory.Repository.WebOrderRepository;
    import com.inventory.Service.OrderService;
    import com.inventory.dto.OrderRequest;
    import com.inventory.model.LocalUser;
    import com.inventory.model.WebOrder;
    import com.inventory.model.WebOrderQuantities;
    import jakarta.persistence.EntityNotFoundException;
    import jakarta.transaction.Transactional;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class OrderServiceImpl implements OrderService {

        private WebOrderRepository webOrderRepository;
        private WebOrderQuantitiesRepository webOrderQuantitiesRepository;

        @Autowired
        public OrderServiceImpl(WebOrderRepository webOrderRepository, WebOrderQuantitiesRepository webOrderQuantitiesRepository) {
            this.webOrderRepository = webOrderRepository;
            this.webOrderQuantitiesRepository = webOrderQuantitiesRepository;
        }

        @Override
        @Transactional
        public List<WebOrder> getOrders(LocalUser user) {
          return webOrderRepository.findByUser(user);
        }

        @Override
        @Transactional
        public List<WebOrder> getOrdersWithQuantities(LocalUser user) {
            List<WebOrder> orders = webOrderRepository.findByUser(user);

            for (WebOrder order : orders) {
                List<WebOrderQuantities> quantities = webOrderQuantitiesRepository.findByOrder(order);
                order.setQuantities(quantities);
            }

            return orders;
        }

        @Override
        @Transactional
        public WebOrder createOrder(OrderRequest orderRequest) {
            // Create a new WebOrder instance and set its properties from 'orderRequest'.
            WebOrder order = new WebOrder();
            order.setUser(orderRequest.getUser());
            order.setAddress(orderRequest.getAddress());
            order.setQuantities(orderRequest.getQuantities());

            // Save the order and quantities to the database.
            WebOrder savedOrder = webOrderRepository.save(order);

            // You may need to update the 'order' object with the generated ID and return it.
            return savedOrder;
        }

        @Override
        @Transactional
        public WebOrder getOrderById(Long orderId) {
            return webOrderRepository.findById(orderId).orElse(null);
        }

        @Override
        @Transactional
        public WebOrder updateOrderById(Long orderId, WebOrder updatedOrder) {
            WebOrder existingOrder = webOrderRepository.findById(orderId).orElse(null);

            if (existingOrder != null) {
                // Update the fields of the existing order with the new data
                existingOrder.setUser(updatedOrder.getUser());
                existingOrder.setAddress(updatedOrder.getAddress());

                // Clear the existing quantities and add the updated quantities
                existingOrder.getQuantities().clear();
                existingOrder.getQuantities().addAll(updatedOrder.getQuantities());

                // Save the updated order to the database
                WebOrder savedOrder = webOrderRepository.save(existingOrder);

                return savedOrder;
            } else {
                // Handle the case where the order with the given ID was not found
                throw new EntityNotFoundException("Order with ID " + orderId + " not found");
            }
        }


        @Override
        @Transactional
        public void deleteOrderById(Long orderId) {
            WebOrder existingOrder = webOrderRepository.findById(orderId)
                    .orElseThrow(() -> new EntityNotFoundException("Order with ID " + orderId + " not found"));

            // Remove the order from the associated quantities to prevent constraints
            for (WebOrderQuantities quantities : existingOrder.getQuantities()) {
                quantities.setOrder(null);
            }

            // Delete the order from the database
            webOrderRepository.delete(existingOrder);
        }


    }




