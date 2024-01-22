package com.demo.productservice.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The type Auditor config.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditorConfig {

    /**
     * define auditor aware method.
     *
     * @return the auditor aware
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }


}
