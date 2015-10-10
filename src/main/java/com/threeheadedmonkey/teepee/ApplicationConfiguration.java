package com.threeheadedmonkey.teepee;

import com.threeheadedmonkey.teepee.respository.InMemoryRepository;
import com.threeheadedmonkey.teepee.respository.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure the application
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public ItemRepository repository() {
        return new InMemoryRepository();
    }

}
