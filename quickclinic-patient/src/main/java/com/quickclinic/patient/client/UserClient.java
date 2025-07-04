package com.quickclinic.patient.client;

import com.quickclinic.patient.client.dto.BasicUserInfoDto;
import com.quickclinic.patient.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "USER-AUTH", configuration = FeignClientConfig.class)
public interface UserClient {

    @GetMapping("/users/get-details")
    BasicUserInfoDto getUserDetails();
}
