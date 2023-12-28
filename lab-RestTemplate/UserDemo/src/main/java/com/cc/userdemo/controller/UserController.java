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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

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
    public User userKeyValue(@RequestParam Long id) {
        User user = new User();
        user.setId(id);
        user.setName("cc");
        user.setAge(18);
        return user;
    }


}
