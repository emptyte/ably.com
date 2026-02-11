package team.emptyte.katharsis.user.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import team.emptyte.ably.tenant.domain.TenantAwareEntity;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserAggregateRoot extends TenantAwareEntity {
  @Id
  private String id;

  @Column(unique = true)
  private String username;
  @Column(unique = true)
  private String email;

  @Column(nullable = false)
  private String firstName;
  @Column(nullable = false)
  private String lastName;
  @Column(nullable = false)
  private String phone;
}
