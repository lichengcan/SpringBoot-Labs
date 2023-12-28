package com.cc.resttemplate.controller;

import com.cc.resttemplate.domain.User;
import com.cc.resttemplate.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lichengcan
 * @date: 2023-12-28 11:52
 * @description exchange
 **/
@RestController
@RequestMapping("/exchange")
public class ExchangeController {


    @Autowired
    RestTemplate restTemplate;


    //<T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType) throws RestClientException;
    @GetMapping("/getUserKeyValue")
    public User getUserKeyValue(Integer id, String name) {
        final String url = "http://localhost:8080/userKeyValue?id={id}&name={name}";
        return restTemplate.exchange(url, HttpMethod.GET, null, User.class, id, name).getBody();
    }

    @PostMapping("/getUserDomain")
    public User getUserDomain(@RequestBody(required = false) User user) {
        String url = "http://localhost:8080/userDomain";
        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cc", "小灿12345");
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        //根据您提供的异常信息，可以看出是 HttpClientErrorException 的 UnsupportedMediaType 错误。该错误表示请求的媒体类型不受支持。
        //
        //根据您的错误信息，服务器返回了状态码 415（Unsupported Media Type），并指示请求的媒体类型不受支持。这意味着您发送的请求的内容类型不正确。
        //
        //在使用 RestTemplate 的 exchange 方法发送 POST 请求时，您需要确保请求头中的 Content-Type 字段与服务器期望的媒体类型一致。默认情况下，RestTemplate 使用 application/json 作为请求的默认媒体类型。
        //
        //解决该问题的方法是将请求头的 Content-Type 设置为正确的媒体类型。您可以尝试修改请求头的 Content-Type 字段，将其设置为 application/json，如下所示：
        //
        //java
        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        //// 其他请求头设置...
        //
        //// 发送POST请求
        //String url = "http://localhost:8081/userDomain";
        //HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        //ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        //请注意，上述代码中的 requestBody 是您要发送的请求体数据，可以根据实际情况进行替换。
        //
        //如果您仍然遇到问题，请确保目标服务器能够正确处理 application/json 类型的请求，并且检查请求的路径 /userDomain 是否正确。
        //
        //如果问题仍然存在，请提供更多相关的代码和错误堆栈信息，以便我能够更准确地帮助您解决问题。
        return restTemplate.exchange(url, HttpMethod.POST, request, User.class).getBody();
    }

    @PostMapping("/getUsersDomain")
    public Users getUsersDomain() {
        String url = "http://localhost:8080/usersDomain";
        //请求参数
        Users users = new Users();
        Map<String, Object> user = new HashMap<>(2);
        user.put("param1", "xxcan");
        user.put("param2", "xx12345");
        users.setId(12345L);
        users.setMap(user);
        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cc", "小灿");
        HttpEntity<Users> request = new HttpEntity<>(users,headers);
        return restTemplate.exchange(url, HttpMethod.POST, request, Users.class).getBody();
    }

    @PostMapping("/getUserMap")
    public Map getUserMap() {
        String url = "http://localhost:8080/userMap";
        //请求参数
        Map<String, Object> users = new HashMap<>(2);
        Map<String,Object> user = new HashMap<>(2);
        user.put("name","灿灿");
        user.put("id",555L);
        users.put("param1", "xxcan");
        users.put("param2", "xx12345");
        users.put("user",user);
        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cc", "小灿");
        HttpEntity<Map> request = new HttpEntity<>(users,headers);
        return restTemplate.exchange(url, HttpMethod.POST, request, Map.class).getBody();
    }
}
