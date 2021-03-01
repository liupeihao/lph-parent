package com.lph.item.api.fegin;


import com.lph.common.response.across.AcrossServiceResponse;
import com.lph.item.api.fegin.callback.SpikUserFeignCallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lph-spike" ,fallback = SpikUserFeignCallBack.class)
public interface SpikeUserFeginClient {

    @PostMapping(value = "/user/fegin_test")
    AcrossServiceResponse<String> feginTest(String name);

}
