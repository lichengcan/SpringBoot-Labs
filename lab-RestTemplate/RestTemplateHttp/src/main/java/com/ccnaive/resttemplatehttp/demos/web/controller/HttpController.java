package com.ccnaive.resttemplatehttp.demos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class HttpController{

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getTest")
    public String getTest(String url, String name) {
        //1. 有参数，没有请求头，拼接方式
        String result1 = restTemplate.getForObject(url +"?name="+name, String.class);
        return result1;
    }

    /**
     * 测试
     */
    @RequestMapping(value = "/http", method = RequestMethod.GET)
    public String http() {
        String id = "52db70d13ad74b0f85142e39b32164b4";
        String name = "测试";
        //参数
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("id", id);
        param.add("name", name);

        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("accessToken", "3d40e41e9d764d30a9a4d72e61ad61b9");

        //封装请求头
        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<MultiValueMap<String, Object>>(headers);

        try {
            //访问地址
            String url = "http://localhost:8080/testservice/test/get";

            //1. 有参数，没有请求头，拼接方式
            String result1 = restTemplate.getForObject(url + "?id="+id+"&name="+name, String.class);
            //2. 有参数，没有请求头，占位符方式
            String result2 = restTemplate.getForObject(url + "?id={id}&name={name}", String.class, param);
            //3. 有请求头，没参数，result3.getBody()获取响应参数
            ResponseEntity<String> result3 = restTemplate.exchange(url, HttpMethod.GET, formEntity, String.class);
            //4. 有请求头，有参数，result4.getBody()获取响应参数
            ResponseEntity<String> result4 = restTemplate.exchange(url+"?id="+id+"&name="+name, HttpMethod.GET, formEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


}
