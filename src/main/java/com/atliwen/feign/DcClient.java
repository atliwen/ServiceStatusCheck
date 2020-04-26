//package com.atliwen.feign;
//
//import com.atliwen.feign.fallback.DcClientFallback;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@FeignClient(name = "eureka-client",url = "http://baidu.com",fallback = DcClientFallback.class)
//public interface DcClient {
//
//    @GetMapping("/dc")
//    String consumer();
//
//}
