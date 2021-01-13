package com.lph.item.service;

import com.lph.item.enums.LotteryTypeEnums;

import java.util.List;

public interface ILotteryService {

    /**
     * 生成号码。
     * @param lotteryType 彩票种类
     * @see com.lph.item.enums.LotteryTypeEnums
     * @param count 数量
     * @return
     */
    List<String> generatorLotteryNo(LotteryTypeEnums lotteryType, int count) ;


}
