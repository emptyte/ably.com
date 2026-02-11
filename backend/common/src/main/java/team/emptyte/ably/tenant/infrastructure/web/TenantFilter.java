package team.emptyte.ably.tenant.infrastructure.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import team.emptyte.ably.tenant.domain.context.TenantContext;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class TenantFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
    final @NonNull HttpServletRequest request,
    final @NonNull HttpServletResponse response,
    final @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    final String requestTenantId = request.getHeader("X-Tenant-ID");
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
      final Jwt jwt = jwtAuthenticationToken.getToken();
      final List<String> allowedTenants = jwt.getClaimAsStringList("tenants") != null
        ? jwt.getClaimAsStringList("tenants")
        : Collections.emptyList();

      if (allowedTenants.contains(requestTenantId)) {
        TenantContext.setTenantId(requestTenantId);
      } else {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access to this tenant is denied");
        return;
      }
    } else {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
      return;
    }

    try {
      filterChain.doFilter(request, response);
    } finally {
      TenantContext.clear();
    }
  }
}
