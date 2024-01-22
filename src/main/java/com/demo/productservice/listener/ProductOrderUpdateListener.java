package com.demo.productservice.listener;

import com.demo.productservice.dto.OrderProductDto;
import com.demo.productservice.service.ProductOrderUpdateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderUpdateListener implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(ProductOrderUpdateListener.class);

    @Autowired
    private ProductOrderUpdateService productOrderUpdateService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            logger.info("Process product order update listener {}", message.getBody());
            ObjectMapper mapper = new ObjectMapper();
            OrderProductDto productDto = mapper.readValue(message.getBody(), OrderProductDto.class);
            productOrderUpdateService.updateProductDetails(productDto);
            logger.info("Message received: {}", productDto.toString());
        } catch (Exception e) {
            logger.error("Error reading message", e);
        }
    }
}
