package com.tApps.ordersservice.controller;

import com.tApps.basedomainsservice.dto.Order;
import com.tApps.basedomainsservice.dto.OrderEvent;
import com.tApps.basedomainsservice.request.RequestOrder;
import com.tApps.ordersservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        super();
        this.orderProducer = orderProducer;
    }

    @PostMapping("/order")
    public String placeOrder(@RequestBody RequestOrder requestOrder){
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order in pending state");
        orderEvent.setOrder(new Order(order.getOrderId(), requestOrder.getName(), requestOrder.getQty(), requestOrder.getPrice()));

        orderProducer.sendMessage(orderEvent);

        return String.format("Order placed successfully \n| OrderID: %s | Order Name: %s | Order Price: %.2f | Order Quantity: %d\n",
                order.getOrderId(),
                order.getName(),
                order.getPrice(),
                order.getQty());
    }
}
