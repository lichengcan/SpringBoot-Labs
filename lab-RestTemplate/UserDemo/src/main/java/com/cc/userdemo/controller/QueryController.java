package com.cc.userdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/condition")
public class QueryController {

    @GetMapping("/query")
    public Map query(@RequestParam String org,
                     @RequestParam String condition) {
        HashMap map = new HashMap(2);
        map.put("org", org);
        map.put("condition", condition);
        return map;
    }
}
