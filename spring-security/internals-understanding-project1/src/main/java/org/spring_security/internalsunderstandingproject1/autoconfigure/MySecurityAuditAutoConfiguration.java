package org.spring_security.internalsunderstandingproject1.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MySecurityAuditProperties.class)
@ConditionalOnClass(name = "org.springframework.security.config.annotation.web.configuration.EnableWebSecurity")
@ConditionalOnProperty(name = "my.security.audit.enabled", havingValue = "true", matchIfMissing = true)
public class MySecurityAuditAutoConfiguration {

    public MySecurityAuditAutoConfiguration() {
        System.out.println("🚀 MySecurityAuditAutoConfiguration CONSTRUCTOR CALLED!");
        System.out.println("   This means:");
        System.out.println("   ✅ Spring Security is on classpath");
        System.out.println("   ✅ my.security.audit.enabled is true (or not set)");
    }

    @Bean
    @ConditionalOnMissingBean
    public MySecurityAuditService mySecurityAuditService(MySecurityAuditProperties properties) {
        System.out.println("🏭 CREATING MySecurityAuditService bean...");
        System.out.println("   Properties loaded: enabled=" + properties.isEnabled() + ", level=" + properties.getLevel());
        return new MySecurityAuditService(properties.getLevel());
    }

    @Bean
    public MySecurityEventListener mySecurityEventListener(MySecurityAuditService auditService) {
        System.out.println("🏭 CREATING MySecurityEventListener bean...");
        return new MySecurityEventListener(auditService);
    }
}
