package com.lph.item.service.impl;

import com.lph.common.exception.BaseException;
import com.lph.item.enums.LotteryTypeEnums;
import com.lph.item.enums.ReturnCodeType;
import com.lph.item.service.ILotteryService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * 生成号码Service
 */
@Service
@Slf4j
public class LotteryServiceImpl implements ILotteryService {


    final String SEPARATOR=" ";



    @SneakyThrows
    @Override
    public List<String> generatorLotteryNo(LotteryTypeEnums lotteryType, int count) {
        switch (lotteryType) {
            //生成双色球
            case THEDOUBLECHROMOSPHERE:
                return generatorTheDoubleChromosphere(count);
            //生成大乐透
            case BRINGS:
                return generatorBrings(count);
        }
        throw new BaseException(ReturnCodeType.LOTTERY_TYPE_NOT_EXIST.getCode(), ReturnCodeType.LOTTERY_TYPE_NOT_EXIST.getName());
    }

    /**
     * 生成大乐透
     *
     * @param count
     * @return
     */
    private List<String> generatorBrings(int count) {
        List<String> r_list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            StringBuilder redSb = new StringBuilder();
            while (true) {
                String redNum = generatorNumber(35);
                if (redSb.indexOf(redNum) == -1) {
                    redSb.append(redNum).append(SEPARATOR);
                }
                if (redSb.length() == 15) {
                    sortStr(redSb);
                    break;
                }
            }
            StringBuilder blueSb = new StringBuilder();
            while (true) {
                String blueNum = generatorNumber(12);
                if (blueSb.indexOf(blueNum) == -1) {
                    blueSb.append(blueNum).append(SEPARATOR);
                }
                if (blueSb.length() == 6) {
                    sortStr(blueSb);
                    break;
                }
            }
            r_list.add(redSb.append(",").append(blueSb).toString());
        }
        return r_list;
    }

    /**
     * 生成双色球
     *
     * @param count
     * @return
     */
    private List<String> generatorTheDoubleChromosphere(int count) {
        List<String> r_list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            while (true) {
                String redNum = generatorNumber(33);
                if (sb.indexOf(redNum) == -1) {
                    sb.append(redNum).append(SEPARATOR);
                }
                if (sb.length() == 18) {
                    sortStr(sb);
                    String blueNum = generatorNumber(16);
                    sb.append(",").append(blueNum).append(SEPARATOR);
                    break;
                }
            }
            r_list.add(sb.toString());
            sb.setLength(0);
        }
        return r_list;

    }


    /**
     * 生成每个号码，小于10的则会补0.大于等于10的不补0；
     *
     * @param biggest
     * @return
     */
    private String generatorNumber(int biggest) {
        Random random = new Random();
        int redNum = random.nextInt(biggest);
        if (redNum == 0) {
            return generatorNumber(biggest);
        }
        return fourNumber(redNum, 2);
    }


    /**
     * 数字格式化
     *
     * @param source     源数。
     * @param supplement 前面补的数量。
     * @return
     */
    private String fourNumber(int source, int supplement) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(supplement);
        formatter.setGroupingUsed(false);
        return formatter.format(source);
    }

    /**
     * 字符串排序
     * @param sb
     */
    private void sortStr(StringBuilder sb){
        String[] s = sb.toString().split(SEPARATOR);
        Arrays.parallelSort(s);
        sb.setLength(0);
        for (String s1 : s) {
            sb.append(s1).append(SEPARATOR);
        }
    }

}
