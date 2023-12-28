package com.cc.resttemplate.controller;

import com.cc.resttemplate.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lichengcan
 * @date: 2023-12-28 10:38
 * @description
 **/

@RestController
@RequestMapping("/getForObject")
public class GetForObject {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 1 传入一个值，返回一个对象的情况：
     * (列举了常用的，没有面面俱到，可以照猫画虎）
     * <p>
     * 1.1 直接将变量写在 url 中，记得添加注解 @PathVariable
     * 参数拼接在url上
     *
     * @param id
     * @return
     */
    @GetMapping("/findUserById/{id}")
    public User findUserById(@PathVariable Integer id) {
        String url = "http://localhost:8080/user/";
        return restTemplate.getForObject(url + id, User.class);
    }

    /**
     * 1.2 将变量通过key=word形式传递，
     *
     * 通过 HttpServletRequest 获取参数
     * 地址栏 key=value 形式传参
     * @param request
     * @return
     */
    @GetMapping("/findAUser")
    public User findAUser(HttpServletRequest request) {
        String id = request.getParameter("id");
        String url = "http://localhost:8080/userKeyValue?id=";
        return restTemplate.getForObject(url + id, User.class);
    }



    /**
     * 1.3 通过占位符：
     * 参数的不同传法 数字占位符
     * @param id
     * @return
     */
    @GetMapping("/findOneUser/{id}")
    public User findOneUser(@PathVariable Integer id){
        String url = "http://localhost:8080/user/{0}";
        return restTemplate.getForObject(url,User.class, id);
    }



    /**
     * 1.4 通过占位符，结合 Map：
     * 参数的不同传法，Map类型
     * @param id
     * @return
     */
    @GetMapping("/one/{id}")
    public User findOneUser(@PathVariable Long id){
        Map<String, Long> map = new HashMap<String, Long>(2);
        map.put("id",id);
        return restTemplate.getForObject("http://localhost:8080/user/{id}", User.class, map);
    }
}
