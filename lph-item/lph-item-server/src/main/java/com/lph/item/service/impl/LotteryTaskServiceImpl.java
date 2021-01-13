package com.lph.item.service.impl;

import com.lph.item.plugin.nacosproperties.LotteryDayProperties;
import com.lph.item.plugin.nacosproperties.MailProperties;
import com.lph.item.service.ILotteryService;
import com.lph.item.service.ILotteryTaskService;
import com.lph.item.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryTaskServiceImpl implements ILotteryTaskService {
    @Autowired
    private ILotteryService lotteryService;

    @Autowired
    private LotteryDayProperties lotteryDayProperties;

    @Autowired
    private IMailService mailService;

    @Autowired
    private MailProperties mailProperties;


    @Override
    public void sendLotteryNo(String paramer) {
        List<String> lotteryNos = lotteryService.generatorLotteryNo(lotteryDayProperties.lotteryOfToday(), Integer.valueOf(paramer));
        StringBuilder sb=new StringBuilder();
        for (String no:lotteryNos){
            sb.append(no).append("<br/>");
        }
        mailService.sendMail(mailProperties.getSenter(),
                mailProperties.getReceiver(),
                mailProperties.getTitle(),
                mailProperties.getContent()+sb.toString(),
                mailProperties.getCc());
    }
}
