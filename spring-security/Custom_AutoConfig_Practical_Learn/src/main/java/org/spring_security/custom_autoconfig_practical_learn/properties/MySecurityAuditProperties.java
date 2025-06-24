package org.spring_security.custom_autoconfig_practical_learn.properties;

public class MySecurityAuditProperties {

    private boolean enabled = true;
    private String level = "INFO";
    private String logFormat = "SIMPLE";

    public MySecurityAuditProperties() {
        System.out.println("📋 MySecurityAuditProperties CREATED");
    }

    // Getters and setters
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getLogFormat() { return logFormat; }
    public void setLogFormat(String logFormat) { this.logFormat = logFormat; }
}