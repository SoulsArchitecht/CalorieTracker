package ru.sshibko.CalorieTracker.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Calorie Tracker")
                        .version("1.0")
                        .description("API для трекинга калорий и питания")
                        .contact(new Contact()
                                .name("Шибко Сергей Николаевич")
                                .email("sshibkodev@gmail.com")));
    }
}
