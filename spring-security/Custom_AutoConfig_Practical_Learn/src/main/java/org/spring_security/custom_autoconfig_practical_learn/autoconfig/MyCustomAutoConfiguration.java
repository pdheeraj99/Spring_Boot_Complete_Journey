package org.spring_security.custom_autoconfig_practical_learn.autoconfig;

import org.spring_security.custom_autoconfig_practical_learn.custom.MySecurityAwareService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass() // Activates if Spring Security is present
public class MyCustomAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MySecurityAwareService mySecurityAwareService() {
        return new MySecurityAwareService();
    }
}