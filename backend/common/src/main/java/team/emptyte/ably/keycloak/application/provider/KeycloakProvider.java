package team.emptyte.ably.keycloak.application.provider;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakProvider {

}
