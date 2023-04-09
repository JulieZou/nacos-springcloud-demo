package com.ibicd.nacosconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosConsumerApplication {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }


    @RestController
    class EchoController {
        @RequestMapping(value = "/echo", method = RequestMethod.GET)
        public String echo(@RequestParam String name) {
            try {
                // 调用nacos-provider 的服务：
                String forObject = restTemplate.getForObject("http://localhost:8091/echo/" + name + "", String.class);
                return forObject;
            } catch (RestClientException e) {
                e.printStackTrace();
            }
            return "";
        }
    }


}
