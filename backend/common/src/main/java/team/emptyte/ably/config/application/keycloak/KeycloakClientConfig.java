package team.emptyte.ably.config.application.keycloak;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClientConfig {
  private final String serverUrl = "http://localhost/auth";
  private final String masterRealm = "master";
  private final String clientId = "admin-cli";
  private final String username = "admin";
  private final String password = "admin";

  @Bean(destroyMethod = "close")
  public Keycloak keycloak() {
    return KeycloakBuilder.builder()
      .serverUrl(this.serverUrl)
      .realm(this.masterRealm)
      .clientId(this.clientId)
      .username(this.username)
      .password(this.password)
      .resteasyClient(
        new ResteasyClientBuilderImpl()
          .connectionPoolSize(10)
          .build()
      )
      .build();
  }
}
