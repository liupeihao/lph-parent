package com.lph.item.test.mail;

import com.lph.item.ItemApplication;
import com.lph.item.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest(classes = ItemApplication.class)
@RunWith(SpringRunner.class)
public class MailTest {

    @Autowired
    private IMailService mailService;

    @Test
    public void sendMail(){
        String senter="liupeihaoit@163.com";
        String receiver="286957587@qq.com";
        String title="测试邮件";
        String content="测试邮件内容";
        String cc="liupeihaoit@163.com";
        mailService.sendMail(senter, receiver, title, content, cc);
    }
}
