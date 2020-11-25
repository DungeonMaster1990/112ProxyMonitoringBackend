package ru.vtb.monitoring.vtb112.dto.api.viewmodels.request;

public class VmPushTokenRequest {
    private String token;
    private String installId;
    private String platform;

    public VmPushTokenRequest(String token, String installId, String platform) {
        this.token = token;
        this.installId = installId;
        this.platform = platform;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInstallId() {
        return installId;
    }

    public void setInstallId(String installId) {
        this.installId = installId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
