package app.commons.config;

import app.commons.properties.GrpcCalculatorProperty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class GrpcProdConfig  {

    @Autowired
    private GrpcCalculatorProperty property;

    @Bean
    @Qualifier("calculator")
    public ManagedChannel managedChannel(){
        String hostname = property.getHostname();
        int port = property.getPort();
        return ManagedChannelBuilder.forAddress(hostname, port)
                .useTransportSecurity() // ToDo 自己証明書の用意
                .build();
    }

}
