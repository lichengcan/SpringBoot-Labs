package com.cc.resttemplate.controller;

import com.cc.resttemplate.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: lichengcan
 * @date: 2023-12-28 11:52
 * @description
 **/
@RestController
@RequestMapping("/exchange")
public class ExchangeController {


    @Autowired
    RestTemplate restTemplate;


    //<T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType) throws RestClientException;
    @RequestMapping("/getUserKeyValue")
    public User getUserKeyValue(Integer id,String name) {
        final String url = "http://localhost:8080/userKeyValue?id={id}&name={name}";
        return restTemplate.exchange(url, HttpMethod.GET, null, User.class, id, name).getBody();
    }
}
