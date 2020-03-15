package app.commons.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:/grpc.properties")
@ConfigurationProperties(prefix = "grpc.calculator.local")
public class GrpcCalculatorLocalProperty {
    private String hostname;
    private String port;
}
