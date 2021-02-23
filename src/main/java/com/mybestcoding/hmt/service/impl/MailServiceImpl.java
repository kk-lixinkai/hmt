package com.mybestcoding.hmt.service.impl;

import com.mybestcoding.hmt.model.ToEmail;
import com.mybestcoding.hmt.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;

/**
 * @author: lixinkai
 * @description: 邮件服务实现类
 * @date: 2021/2/21 15:07
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;


    /**
     * 发送验证码
     *
     * @param toEmail 收信人信息
     * @return 发送成功标志
     */
    @Override
    public boolean sendCaptcha(ToEmail toEmail) {
        try {
            // 执行发送邮件
            mailSender.send((MimeMessage) getMessage(toEmail));
            return true;
        } catch (MailException | MessagingException e) {
            return false;
        }
    }

    /**
     * 设置邮件信息
     *
     * @param toEmail 邮件信息
     * @return
     * @throws MessagingException
     */
    private Part getMessage(ToEmail toEmail) throws MessagingException {
        // 创建一个 Mime 消息
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        // 设置发信人
        mimeMessageHelper.setFrom(from);
        // 设置收信人
        mimeMessageHelper.setTo(toEmail.getReceivers());
        // 设置邮件主题
        mimeMessageHelper.setSubject(toEmail.getSubject());
        // 设置邮件内容
        mimeMessageHelper.setText(toEmail.getContent(), true);
        return mimeMessage;
    }
}
