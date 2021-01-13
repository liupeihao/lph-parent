package com.lph.item.service.impl;

import com.lph.item.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceImpl implements IMailService {


    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String senter, String receiver, String title, String content, String cc) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 发件人
        message.setFrom(senter);
        // 收件人
        message.setTo(receiver);
        // 邮件标题
        message.setSubject(title);
        // 邮件内容
        message.setText(content);
        if(StringUtils.isNoneBlank(cc)){
            // 抄送人
            message.setCc(cc);
        }
        //发送
        mailSender.send(message);
    }
}
