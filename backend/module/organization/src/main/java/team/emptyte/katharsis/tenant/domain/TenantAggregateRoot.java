package team.emptyte.katharsis.tenant.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tenants")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class TenantAggregateRoot {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
