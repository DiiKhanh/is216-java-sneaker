package com.projectjavasneaker.backendis216.models;

import org.apache.tomcat.websocket.AuthenticationType;

import javax.persistence.*;

@Entity
@Table(name = "usersso")
public class UserSSO {

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_type")
    private AuthenticationType authType;

    public AuthenticationType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthenticationType authType) {
        this.authType = authType;
    }

    // other fields, getters and setters are not shown
}