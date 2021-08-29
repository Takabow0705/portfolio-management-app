package project.calculator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAutoConfiguration
public class ExecutorConfig {

    @Value("${calculator.thread-pool-executor.core-pool-size}")
    private int corePoolSize;
    @Value("${calculator.thread-pool-executor.max-pool-size}")
    private int maxPoolSize;
    @Value("${calculator.thread-pool-executor.queue-capacity}")
    private int queueCapacity;

    @Bean("asyncJobExecutor")
    public ThreadPoolTaskExecutor asyncJobExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
