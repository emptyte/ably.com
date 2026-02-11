package team.emptyte.ably.tenant.infrastructure.persistence.hibernate;

import jakarta.annotation.Nonnull;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.hibernate.autoconfigure.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;
import team.emptyte.ably.tenant.domain.context.TenantContext;

import java.util.Map;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<String>, HibernatePropertiesCustomizer {
  private final static String DEFAULT_TENANT_ID = "default";

  @Override
  public @Nonnull String resolveCurrentTenantIdentifier() {
    final String tenantId = TenantContext.getTenantId();
    if (tenantId != null) {
      return tenantId;
    }
    return DEFAULT_TENANT_ID;
  }

  @Override
  public boolean validateExistingCurrentSessions() {
    return true;
  }

  @Override
  public void customize(final @Nonnull Map<String, Object> hibernateProperties) {
    hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
  }
}
