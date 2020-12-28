package com.lph.item.service.impl;

import com.lph.common.response.across.AcrossServiceResponse;
import com.lph.item.service.IItemUserService;
import com.lph.item.api.fegin.SpikeUserFeginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemUserServiceImpl implements IItemUserService {

    @Autowired
    private SpikeUserFeginClient spikeUserFeginClient;

    @Override
    public String feginTest(String name) {
        AcrossServiceResponse<String> userResponse = spikeUserFeginClient.feginTest(name);
        return userResponse.obtainResponse();
    }
}
