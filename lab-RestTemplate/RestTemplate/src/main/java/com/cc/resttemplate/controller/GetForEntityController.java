package com.cc.resttemplate.controller;

import com.cc.resttemplate.domain.QueryCondition;
import com.cc.resttemplate.domain.QueryRequest;
import com.cc.resttemplate.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    @PostMapping("/query")
    public Map query() {
        String url = "http://localhost:8080/condition/query?org={org}&condition={condition}";
        //请求参数
        Map<String,Object> users = new HashMap<>(2);
        users.put("org","12333");
        Map<String,Object> conditionMap = new HashMap<>(8);
        conditionMap.put("1","1");
        conditionMap.put("2","2");
        conditionMap.put("3","3");
        conditionMap.put("tt","ss");
        conditionMap.put("bb","aa");
        users.put("condition",conditionMap);
        return restTemplate.getForEntity(url,Map.class,users).getBody();

    }

    @PostMapping("/query1")
    public Map query1() {
        String url = "http://localhost:8080/condition/query?org={org}&condition={condition}";
        //请求参数
        Map<String,Object> users = new HashMap<>(2);
        users.put("org","12333");
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setItemNum(10); // 设置 ItemNum 属性为 10
        queryRequest.setQueryCount(1); // 设置 QueryCount 属性为 1
        queryRequest.setPageFirstRowNumber(0); // 设置 PageFirstRowNumber 属性为 0
        queryRequest.setPageRowNum(10); // 设置 PageRowNum 属性为 10
        List<QueryCondition> conditionList = new ArrayList<>(); // 创建 QueryCondition 对象列表

        QueryCondition condition1 = new QueryCondition();
        condition1.setQueryType(1);
        condition1.setLogicFlag(1);
        condition1.setQueryData("data1");
        conditionList.add(condition1);

        queryRequest.setCondition(conditionList);
        // 将 QueryRequest 对象放入 Map 中
        //这里将对象放入map中时，Map会调用改对象的toString方法，所以需要重写toString方法
        users.put("condition",queryRequest);
        return restTemplate.getForEntity(url,Map.class,users).getBody();
    }

}
