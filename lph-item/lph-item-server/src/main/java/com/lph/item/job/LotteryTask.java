package com.lph.item.job;

import com.lph.item.beanutil.SpringUtil;
import com.lph.item.service.ILotteryTaskService;
import com.lph.item.service.impl.LotteryTaskServiceImpl;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class LotteryTask extends IJobHandler {

    private ILotteryTaskService lotteryTaskService= SpringUtil.getBean(LotteryTaskServiceImpl.class);

    @XxlJob("LotteryTask")
    @Override
    public ReturnT<String> execute(String s) {
        lotteryTaskService.sendLotteryNo(s);
        return ReturnT.SUCCESS;
    }
}

