package edu.icet.config;

import edu.icet.repository.custom.AdminRepository;
import edu.icet.repository.custom.ItemRepository;
import edu.icet.repository.custom.impl.AdminRepositoryImpl;
import edu.icet.repository.custom.impl.ItemRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public ModelMapper getModelMapper () {
        return new ModelMapper();
    }



}
