package com.tythac.webapierp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "來億ERP API", version = "1.0.0"))
public class WebapierpApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebapierpApplication.class, args);
    }

}
