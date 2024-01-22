package com.demo.productservice.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * Define Product order update listener configurations.
 */
@Configuration
public class ProductOrderUpdateListenerConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private ProductOrderUpdateListener productOrderUpdateListener;

    /**
     * Container redis message listener container.
     *
     * @return the redis message listener container
     */
    @Bean
    RedisMessageListenerContainer container() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.addMessageListener(messageListener(), topic());
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }

    /**
     * Message listener message listener adapter.
     *
     * @return the message listener adapter
     */
    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(productOrderUpdateListener);
    }

    /**
     * Topic channel topic.
     *
     * @return the channel topic
     */
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("productUpdateTopic");
    }
}
