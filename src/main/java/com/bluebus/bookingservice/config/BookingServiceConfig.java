package com.bluebus.bookingservice.config;

import jakarta.jms.ConnectionFactory;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableJms
@LoadBalancerClient(name = "booking-service")
public class BookingServiceConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory
            (ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("5-10");
        return factory;
    }
}
