package com.lph.item.api.fegin.callback;

import com.lph.common.response.across.AcrossServiceResponse;
import com.lph.item.api.fegin.SpikeUserFeginClient;
import org.springframework.stereotype.Component;



@Component
public class SpikUserFeignCallBack implements SpikeUserFeginClient {

    @Override
    public AcrossServiceResponse<String> feginTest(String name) {
        return AcrossServiceResponse.error("The service is down");
    }
}
