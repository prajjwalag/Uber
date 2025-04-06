package com.prajjwal.project.Uber.configuratons;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
