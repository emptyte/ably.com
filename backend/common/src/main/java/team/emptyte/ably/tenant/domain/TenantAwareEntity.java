package team.emptyte.ably.tenant.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.TenantId;

@MappedSuperclass
public class TenantAwareEntity {
  @TenantId
  @Column(name = "tenant_id", nullable = false, updatable = false)
  private String tenantId;

  public @Nonnull String tenantId() {
    return this.tenantId;
  }

  public void tenantId(final @NotNull(message = "TenantId cannot be null") String tenantId) {
    this.tenantId = tenantId;
  }
}
