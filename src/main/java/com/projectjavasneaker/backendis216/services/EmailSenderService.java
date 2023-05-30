package com.projectjavasneaker.backendis216.services;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
}
