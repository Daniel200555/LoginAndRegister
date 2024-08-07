package com.example.demo.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic createUser() {
        return TopicBuilder.name("createuser").build();
    }

    @Bean
    public NewTopic databaseUser() {
        return TopicBuilder.name("databaseuser").build();
    }

}
