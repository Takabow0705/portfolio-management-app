package app.commons.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Value("${web.api.version}")
    private String version;

    @Bean
    public OpenAPI openAPI(){
        Info info = new Info().title("Portfolio Management API").version(version);
        Components components = new Components().addSecuritySchemes("SESSION", new SecurityScheme().type(SecurityScheme.Type.HTTP));
        OpenAPI openAPI = new OpenAPI().info(info).components(components);
        return openAPI;
    }
}
