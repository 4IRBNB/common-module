package com.fourirbnb.common.config;



import com.fourirbnb.common.domain.AuditorAwareImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Configuration
@EnableJpaAuditing
@ConditionalOnProperty(
    prefix = "common.jpa",
    name = "enabled",
    havingValue = "true",
    matchIfMissing = true
)
public class JpaConfig {
  @Bean
  public AuditorAware<Long> auditorAware() {
    return new AuditorAwareImpl();
  }
}