package team.emptyte.ably.config.application.security;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
  private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

  @Value("${jwt.auth.converter.resource-id}")
  private String resourceId;

  @Override
  public AbstractAuthenticationToken convert(final @Nonnull Jwt jwt) {
    final Collection<GrantedAuthority> authorities = Stream
      .concat(this.jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractResourceRoles(jwt).stream())
      .collect(Collectors.toSet());
    return new JwtAuthenticationToken(jwt, authorities);
  }

  @SuppressWarnings("unchecked")
  private @Nonnull Collection<? extends GrantedAuthority> extractResourceRoles(final @Nonnull Jwt jwt) {
    final Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

    if (resourceAccess == null || resourceAccess.get(resourceId) == null) {
      return Collections.emptyList();
    }

    final Map<String, Object> clientAccess = (Map<String, Object>) resourceAccess.get(resourceId);
    final Collection<String> clientRoles = (Collection<String>) clientAccess.get("roles");

    return clientRoles.stream()
      .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role)))
      .toList();
  }
}
