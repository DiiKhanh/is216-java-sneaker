package com.projectjavasneaker.backendis216.payload.request;

public class ChangePassword {
    private String currentPass;
    private String password;

    public String getCurrentPass() {
        return currentPass;
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
