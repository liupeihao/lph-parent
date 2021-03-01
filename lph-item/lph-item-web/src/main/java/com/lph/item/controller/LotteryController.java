package com.lph.item.controller;

import com.lph.common.response.form.ServerResponse;
import com.lph.item.form.LotteryGeneratorForm;
import com.lph.item.service.IItemUserService;
import com.lph.item.service.ILotteryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LPH
 * @Description
 * @since 2020-10-10
 * UserController
 */
@Api(value = "Lottery Controller")
@Slf4j
@RestController
@RequestMapping(value = "/lottery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LotteryController {


    @Autowired
    private ILotteryService lotteryService;


    /**
     * 生成彩票号码
     *
     * @param lotteryGeneratorForm
     * @return
     */
    @PostMapping(value = "generator_lottery")
    @ApiOperation(value = "生成彩票的函数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lotteryType", value = "彩票种类：0双色球，1大乐透",  dataType = "Integer"),
            @ApiImplicitParam(name = "count", value = "生成的注数",  dataType = "Integer")
    })
    public ServerResponse<List<String>> generatorLottery(LotteryGeneratorForm lotteryGeneratorForm) {
        List<String> lotteryNos = lotteryService.generatorLotteryNo(lotteryGeneratorForm.getLotteryType(), lotteryGeneratorForm.getCount());
        return ServerResponse.success(lotteryNos);
    }


}