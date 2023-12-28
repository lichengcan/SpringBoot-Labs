/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cc.userdemo.controller;

import com.cc.userdemo.domain.User;
import com.cc.userdemo.domain.Users;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * @author lichengcan
 */
@RestController
@RequestMapping()
public class UserController {


    @GetMapping("/user/{id}")
    public User user(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setName("cc");
        user.setAge(18);
        return user;
    }

    @GetMapping("/userKeyValue")
    public User userKeyValue(@RequestParam(required = false) Long id,
                             @RequestParam(required = false) String name) {
        User user = new User();
        user.setId(id == null ? 1 : id);
        user.setName(name == null ? "cc" : name);
        user.setAge(18);
        return user;
    }

    @PostMapping("/userDomain")
    public User userDomain(@RequestBody(required = false) User user, HttpServletRequest request) {
        user.setAge(188);
        //å°ç¿乱码了
        try {
            System.out.println(new String(request.getHeader("cc").getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @PostMapping("/usersDomain")
    public Users usersDomain(@RequestBody(required = false) Users user, HttpServletRequest request) {
        try {
            System.out.println(new String(request.getHeader("cc").getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

}
