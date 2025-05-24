package com.example.product_service.service;

import com.example.product_service.api.ApiRespon;
import com.example.product_service.respon.DeepRespon;
import com.example.product_service.respon.FeatureRespon;
import com.example.product_service.respon.HomePageDeepRespon;
import jakarta.persistence.Tuple;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "DEEP-SERVICE",url = "http://127.0.0.1:9007")
public interface DeepService {
    @PostMapping(value = "/deep-service/api/v1/test",consumes = "application/json")
    List<DeepRespon> deep_service(@RequestBody ApiRespon<HomePageDeepRespon> _request);
}
