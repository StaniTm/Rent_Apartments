package com.example.myproject.config.beans;


import com.example.myproject.domain.helper.LoggedUser;
import com.example.myproject.utils.Utils;
import jakarta.servlet.MultipartConfigElement;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public LoggedUser loggedUser() {
        return new LoggedUser();
    }

    @Bean
    public Utils utils() {
        return new Utils();
    }


    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(512000000L));
        factory.setMaxRequestSize(DataSize.ofBytes(512000000L));
        return factory.createMultipartConfig();
    }
}
