package com.tApps.stockservice.kapka;

import com.tApps.basedomainsservice.dto.Order;
import com.tApps.basedomainsservice.dto.OrderEvent;
import com.tApps.stockservice.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.tApps.stockservice.model.Stock;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    private final StockRepository stockRepository;

    public OrderConsumer(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent orderEvent){

        LOGGER.info(String.format("Order event received in stock service => %s", orderEvent.toString()));

        // save the order into database
        Stock stock = new Stock(orderEvent.getOrder().getOrderId(),
                orderEvent.getOrder().getName(),
                orderEvent.getOrder().getQty(),
                orderEvent.getOrder().getPrice());

        stockRepository.save(stock);
    }
}
