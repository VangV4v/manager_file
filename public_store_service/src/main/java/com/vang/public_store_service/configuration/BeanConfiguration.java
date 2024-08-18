package com.vang.public_store_service.configuration;

import static com.vang.public_store_service.common.ServiceCommon.*;
import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public MinioClient minioClient() {

        return MinioClient.builder().endpoint(END_POINT).credentials(USERNAME, PASSWORD).build();
    }
}