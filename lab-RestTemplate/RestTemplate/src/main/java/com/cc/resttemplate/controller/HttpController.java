package com.cc.resttemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class HttpController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getTest")
    public String getTest(String url, String name) {
        //1. 有参数，没有请求头，拼接方式
        String result1 = restTemplate.getForObject(url + "?name=" + name, String.class);
        return result1;
    }


    @GetMapping("/getTest1")
    public String getTest1(String url, String name) {
        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("accessToken", "3d40e41e9d764d30a9a4d72e61ad61b9");

        //封装请求头
        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<MultiValueMap<String, Object>>(headers);
        //4. 有请求头，有参数，result4.getBody()获取响应参数
        ResponseEntity<String> result4 = restTemplate.exchange(url + "?name=" + name, HttpMethod.GET, formEntity, String.class);
        return result4.getBody();
    }

    public void getForObject(String url, String name) {
        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<String, String>(16);
        map.put("name", name);
        map.put("id", "52db70d13ad74b0f85142e39b32");
        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `Content-Type` and `Accept` headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // example of custom header
        headers.set("X-Request-Source", "Desktop");
        // build the request
        HttpEntity request = new HttpEntity(headers);
        // make an HTTP GET request with headers
        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                Map.class,
                map
        );
        // check response
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful.");
            System.out.println(response.getBody());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }
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
            String result1 = restTemplate.getForObject(url + "?id=" + id + "&name=" + name, String.class);
            //2. 有参数，没有请求头，占位符方式
            String result2 = restTemplate.getForObject(url + "?id={id}&name={name}", String.class, param);
            //3. 有请求头，没参数，result3.getBody()获取响应参数
            ResponseEntity<String> result3 = restTemplate.exchange(url, HttpMethod.GET, formEntity, String.class);
            //4. 有请求头，有参数，result4.getBody()获取响应参数
            ResponseEntity<String> result4 = restTemplate.exchange(url + "?id=" + id + "&name=" + name, HttpMethod.GET, formEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


}
