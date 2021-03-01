package com.lph.item.nacosproperties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    /**
     * 发件人
     */
    private String senter;
    /**
     * 收件人
     */
    private String receiver;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 抄送人
     */
    private String cc;

}
