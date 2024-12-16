package com.sep.backend.service;

/**
 * The interface Email Sender service.
 */
public interface IEmailSenderService {
    void sendEmail(String to, String subject, String message);
}
