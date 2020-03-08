package app.commons.config;

import app.commons.properties.GrpcCalculatorLocalProperty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"local","default"})
public class GrpcLocalConfig {

    @Autowired
    private GrpcCalculatorLocalProperty property;

    @Bean(name = "calculator")
    public ManagedChannel managedChannel(){
        String hostname = property.getHostname();
        int port = Integer.parseInt(property.getPort());
        return ManagedChannelBuilder.forAddress(hostname, port)
                .usePlaintext()
                .build();
    }
}
