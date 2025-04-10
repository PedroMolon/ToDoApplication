package com.pedromolon.ToDo.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {

        Contact contact = new Contact();
        contact.name("Pedro");
        contact.email("pedroh.molonr@gmail.com");

        Info info = new Info();
        info.title("ToDoApplication");
        info.version("v1");
        info.description("Aplicação para cadastro de tarefas");
        info.contact(contact);

        return new OpenAPI().info(info);
    }

}
