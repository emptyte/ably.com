package team.emptyte.katharsis.tenant.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantService {
  @Value("${keycloak.server-url}")
  private String serverUrl;
  @Value("${keycloak.realm}")
  private String realm;
  @Value("${keycloak.resource}")
  private String clientId;
  @Value("${keycloak.credentials.secret}")
  private String clientSecret;
}
