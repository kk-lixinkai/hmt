package com.mybestcoding.hmt.service;

import com.mybestcoding.hmt.model.ToEmail;

import javax.mail.MessagingException;

/**
 * 邮件服务接口
 */
public interface MailService {
    boolean sendCaptcha(ToEmail toEmail) throws MessagingException;
}
