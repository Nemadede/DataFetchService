package org.datafetch.service.schema;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OAuthResponse {
    private String realmId;
    private String code;
    private String state;

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "OAuthResponse{" +
                "realmId='" + realmId + '\'' +
                ", code='" + code + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
