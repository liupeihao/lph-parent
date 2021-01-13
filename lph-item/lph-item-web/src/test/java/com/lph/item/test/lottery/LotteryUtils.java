package com.lph.item.test.lottery;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.NumberFormat;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@Slf4j
public class LotteryUtils {


    /**
     * 格式化数字
     */
    @Test
    public void formatNum() {
        int num = 10;
//        if (num < 10) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(2);
        formatter.setGroupingUsed(false);
        log.info("result is {}", formatter.format(num));
//        }
    }


    /**
     * 排序
     */
    @Test
    public void sort() {
       String num="06 05 17 18 14 ";
        String[] s = num.split(" ");
        Arrays.parallelSort(s);
        log.info("result is {}",s );
    }
}
