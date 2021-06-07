package com.kareem.springreactive.reactivemongo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class TemplateDatabaseLoader {
    @Bean
    CommandLineRunner initialize(MongoOperations mongoOperations) {
        return args -> {
            mongoOperations.save(new Item("item-1", 20));
            mongoOperations.save(new Item("item-2", 30));
        };
    }
}
