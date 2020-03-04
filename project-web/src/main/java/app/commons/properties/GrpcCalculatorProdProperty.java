package app.commons.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "grpc.calculator.prod")
@PropertySource(value = "classpath:/grpc.properties")
public class GrpcCalculatorProdProperty {
    private String hostname;
    private String port;
}
