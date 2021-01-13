package com.lph.item.plugin.nacosproperties;


import com.lph.item.enums.LotteryTypeEnums;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "lotterys")
public class LotteryDayProperties {

    private List<String> brings;

    private List<String> thedoublechromosphere;


    /**
     * 今天是双色球还是大乐透
     * @return
     */
    public LotteryTypeEnums lotteryOfToday(){
        if(brings.contains(LocalDateTime.now().getDayOfWeek().getValue())){
            return LotteryTypeEnums.BRINGS;
        }
        return  LotteryTypeEnums.THEDOUBLECHROMOSPHERE;
    }
}
