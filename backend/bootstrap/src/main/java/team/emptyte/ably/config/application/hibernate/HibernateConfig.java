package team.emptyte.ably.config.application.hibernate;

import jakarta.annotation.Nonnull;
import org.hibernate.cfg.MultiTenancySettings;
import org.springframework.boot.hibernate.autoconfigure.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.emptyte.ably.tenant.infrastructure.persistence.hibernate.TenantIdentifierResolver;

@Configuration
public class HibernateConfig {

  @Bean
  public @Nonnull HibernatePropertiesCustomizer hibernatePropertiesCustomizer(final @Nonnull TenantIdentifierResolver tenantResolver) {
    return hibernateProperties -> hibernateProperties.put(MultiTenancySettings.MULTI_TENANT_IDENTIFIER_RESOLVER, tenantResolver);
  }
}
