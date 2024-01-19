package com.demo.productservice.platform.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Define open API configuration.
 */
@Configuration
public class OpenApiConfig {

  /**
   * Create and define all information required for the OpenAPI definition.
   *
   * @return a OpenAPI instance.
   */
  @Bean
  public OpenAPI customOpenApi() {
    // Define the information needed for open api.
    Info info = new Info();
    info.setTitle("Product service APIs");
    info.setDescription("Product service API Docs.");
    info.setVersion("0.0.1");

    return new OpenAPI().components(new Components()).info(info);

  }

}
