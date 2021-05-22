package app.commons.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ConfigurationProperties(prefix = "external.calculator")
@Component
public class GrpcCalculatorProperty {
    private String hostname;
    @Min(1)
    @Max(65535)
    private Integer port;

    public GrpcCalculatorProperty() {
    }

    public String getHostname() {
        return this.hostname;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GrpcCalculatorProperty)) return false;
        final GrpcCalculatorProperty other = (GrpcCalculatorProperty) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$hostname = this.getHostname();
        final Object other$hostname = other.getHostname();
        if (this$hostname == null ? other$hostname != null : !this$hostname.equals(other$hostname)) return false;
        final Object this$port = this.getPort();
        final Object other$port = other.getPort();
        if (this$port == null ? other$port != null : !this$port.equals(other$port)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GrpcCalculatorProperty;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $hostname = this.getHostname();
        result = result * PRIME + ($hostname == null ? 43 : $hostname.hashCode());
        final Object $port = this.getPort();
        result = result * PRIME + ($port == null ? 43 : $port.hashCode());
        return result;
    }

    public String toString() {
        return "GrpcCalculatorLocalProperty(hostname=" + this.getHostname() + ", port=" + this.getPort() + ")";
    }
}
