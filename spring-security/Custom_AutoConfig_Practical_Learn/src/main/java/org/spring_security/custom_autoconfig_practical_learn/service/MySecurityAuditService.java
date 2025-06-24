package org.spring_security.custom_autoconfig_practical_learn.service;

public class MySecurityAuditService {

    private final String auditLevel;

    public MySecurityAuditService(String auditLevel) {
        this.auditLevel = auditLevel;
        System.out.println("🔥 MySecurityAuditService CREATED with level: " + auditLevel);
    }

    public void logSecurityEvent(String event) {
        System.out.println("🛡️ SECURITY AUDIT [" + auditLevel + "]: " + event);
    }

    public String getAuditLevel() {
        return auditLevel;
    }
}