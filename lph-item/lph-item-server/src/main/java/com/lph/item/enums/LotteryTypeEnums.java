package com.lph.item.enums;


import com.lph.common.base.emuns.BaseEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 彩票类型
 */

public enum LotteryTypeEnums implements BaseEnum<Integer> {

    /**
     * 双色球
     */
    THEDOUBLECHROMOSPHERE(0,"双色球"),
    /**
     * 大乐透
     */
    BRINGS(1,"大乐透");

    LotteryTypeEnums(Integer id,String name){
        this.id =id;
        this.name=name;
    }




    /**
     * 彩票类型
     */
    @Getter
    @Setter
    private Integer id;

    /**
     * 彩票名称
     */
    @Getter
    @Setter
    private String name;
}
