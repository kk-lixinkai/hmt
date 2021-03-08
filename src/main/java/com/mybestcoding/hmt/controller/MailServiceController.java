package com.mybestcoding.hmt.controller;

import com.mybestcoding.hmt.constant.ResponseBody;
import com.mybestcoding.hmt.model.ToEmail;
import com.mybestcoding.hmt.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Random;

/**
 * @author: lixinkai
 * @description: 邮件服务控制器
 * @date: 2021/2/21 15:29
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Api(tags = "邮件服务接口")
@RestController
@RequestMapping("/mail")
public class MailServiceController {

    @Autowired
    MailService mailService;

    @PostMapping("/captcha")
    @ApiOperation("获取验证码")
    public ResponseBody sendCaptcha(String[] to) {
        String captchaCode = null;
        try {
            captchaCode = getCaptchaCode();
            mailService.sendCaptcha(createEmail(to, captchaCode));
            return ResponseBody.success("验证码邮件发送成功，验证码如下", captchaCode);
        } catch (MessagingException e) {
            return ResponseBody.error("验证码邮件发送失败!");
        }
    }

    /**
     * 创建邮件信息
     *
     * @param to          收件人
     * @param captchaCode 验证码
     * @return
     */
    private ToEmail createEmail(String[] to, String captchaCode) {
        // 构建邮件内容
        String text = new StringBuilder()
                .append("<html>\n")
                .append("    <body>\n")
                .append("       这是注册验证码: <h3>")
                // 生成验证码
                .append(captchaCode)
                .append("</h3>\n")
                .append("    </body>\n")
                .append("</html>").toString();
        ToEmail email = new ToEmail();
        email.setReceivers(to);
        email.setSubject("注册验证码");
        email.setContent(text);
        return email;
    }

    /**
     * 生成 6 位的随机验证码
     *
     * @return 验证码
     */
    private String getCaptchaCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
