package com.lph.item.api.fegin;


import com.lph.common.response.across.AcrossServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "lph-spike")
public interface SpikeUserFeginClient {

    @PostMapping(value = "/user/fegin_test")
    AcrossServiceResponse<String> feginTest(String name);

}
