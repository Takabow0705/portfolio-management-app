package app.commons.config;

import app.commons.properties.GrpcCalculatorProperty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class GrpcProdConfig  {

    @Autowired
    private GrpcCalculatorProperty property;

    @Bean(name = "calculator")
    public ManagedChannel managedChannel(){
        String hostname = property.getHostname();
        int port = Integer.parseInt(property.getPort());
        return ManagedChannelBuilder.forAddress(hostname, port)
                .useTransportSecurity()
                .build();
    }

}
