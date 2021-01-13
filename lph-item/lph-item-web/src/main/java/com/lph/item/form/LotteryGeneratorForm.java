package com.lph.item.form;

import com.lph.item.enums.LotteryTypeEnums;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
//@Validated
public class LotteryGeneratorForm  {
    /**
     * 彩票类型
     */
    @NotNull(message = "号码类型不能为空")
    @ApiModelProperty(value = "彩票类型", allowableValues = "0, 1")
    private LotteryTypeEnums lotteryType;


    /**
     * 注数
     */
    @NotNull(message = "注数不能为空")
    @Pattern(regexp= "^([1-9]|[1-9]\\d|100)$",message = "请输入1-100范围内的注数")
    private Integer count;

}
