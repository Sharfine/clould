package com.sharfine.sms.service.impl;


import com.sharfine.sms.dp.Email;
import com.sharfine.sms.dp.TimeOut;
import com.sharfine.sms.service.EmailFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.function.Supplier;

/**
 * @author: Sharfine
 * @createTime: 2022/1/6 14:16
 */
@Service
@Slf4j
public class EmailFacadeImpl extends AuthCodeProvider implements EmailFacade {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mallFrom;

    @Override
    public void sendEmailAuthCode(Email email, TimeOut timeOut, Supplier<String> strategy) {

        String authCode = generateAuthCode(email.getCacheKey(), timeOut, strategy);

        //发送到邮箱
        try {
            //设置为html内容
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //设置发件人Email
            helper.setFrom(mallFrom);
            //设置邮件主题
            helper.setSubject("remind.title");
            //设置邮件主题内容
            helper.setText("remind.verify.code" + authCode, true);
            //设定收件人Email
            helper.setTo(email.getNumber());
            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            log.error("发送邮件失败: ", e);
            throw new RuntimeException("error.commom.email.wrong_send");
        }
    }
}
