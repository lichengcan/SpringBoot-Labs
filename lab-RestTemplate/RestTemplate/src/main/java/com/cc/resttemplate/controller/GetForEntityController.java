package com.cc.resttemplate.controller;

import com.cc.resttemplate.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lichengcan
 * @date: 2023-12-28 11:27
 * @description GetForEntity
 **/

@RestController
@RequestMapping(value = "/getForEntity")
public class GetForEntityController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 使用键值对的形式
     */
    @GetMapping("/userKeyValue")
    public User userKeyValue(@RequestParam Long id, @RequestParam String name) {
        return restTemplate.getForEntity("http://localhost:8080/userKeyValue?id={id}&name={name}", User.class, id,name).getBody();
    }

    /**
     * 使用Map的形式
     */
    @GetMapping("/userByMap")
    public User userByMap(@RequestParam Long id, @RequestParam String name) {
        Map<String, Object> requestParams = new HashMap<>(2);
        requestParams.put("id", id);
        requestParams.put("name", name);
        return restTemplate.getForEntity("http://localhost:8080/userKeyValue?id={id}&name={name}", User.class, requestParams).getBody();
    }

    /**
     * 无请求参数
     * @return
     */
    @GetMapping("/userNoRequestParam")
    public User userNoRequestParam() {
        return restTemplate.getForEntity("http://localhost:8080/userKeyValue", User.class).getBody();
    }

}
