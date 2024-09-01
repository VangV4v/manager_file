package com.vang.file_service.configuration;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static com.vang.file_service.common.FileCommon.*;

@Configuration
public class BeanConfiguration {

    @Bean
    public MinioClient minioClient() {

        return MinioClient.builder().endpoint(END_POINT).credentials(USERNAME, PASSWORD).build();
    }
}