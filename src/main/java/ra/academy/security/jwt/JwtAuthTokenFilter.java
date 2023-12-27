package ra.academy.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ra.academy.security.principle.UserDetailServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor

public class JwtAuthTokenFilter extends OncePerRequestFilter {
    private final UserDetailServiceImpl userDetailService;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenFromRequest(request);
            if (token != null && jwtProvider.validateToken(token)) {
                String username = jwtProvider.getUserNameFromToken(token);
                UserDetails userDetails = userDetailService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new
                        UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        } catch (Exception e) {
            log.error("Error->>>>>>", e.getMessage());
//            sendErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, "Authentication failed", e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);

    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message, String errorMessage) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
//        errorResponse.put("error", errorMessage);
        new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
    }
    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring("Bearer ".length());
        }
        return null;
    }
}
