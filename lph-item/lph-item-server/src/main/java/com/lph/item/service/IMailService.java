package com.lph.item.service;

public interface IMailService {


    /**
     * 发送邮件
     * @param senter 发件人
     * @param receiver 收件人
     * @param title 标题
     * @param content 内容
     * @param cc 抄送人
     */
    public void sendMail(String senter,
                         String receiver,
                         String title,
                         String content,
                         String cc
                         );

}
