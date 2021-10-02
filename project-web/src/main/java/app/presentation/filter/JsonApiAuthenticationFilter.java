package app.presentation.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.flogger.FluentLogger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * SPA用のセッション認証Filter
 * サーバ内でセッション情報を保持してユーザを認証する。
 * Redisにセッション情報を保存するので実質Statelessになるはず。
 */
public class JsonApiAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    ObjectMapper objectMapper = new ObjectMapper();

    private static final String usernameParameter = "username";
    private static final String passwordParameter = "password";
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public JsonApiAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/api/login", "POST"));
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Map<String, Object> requestObject;
        logger.atInfo().log("Receive API Authentication Request. [Host: %s:%s, Locale: %s]", request.getRemoteHost(), request.getRemotePort(), request.getLocale());
        try {
            requestObject = objectMapper.readValue(request.getInputStream(), Map.class);
        } catch (IOException e) {
            requestObject = new HashMap<>();
        }

        String username =
                Optional
                        .ofNullable(requestObject.get(usernameParameter))
                        .map(Object::toString)
                        .map(String::trim)
                        .orElse("");
        String password =
                Optional
                        .ofNullable(requestObject.get(passwordParameter))
                        .map(Object::toString)
                        .orElse("");

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
