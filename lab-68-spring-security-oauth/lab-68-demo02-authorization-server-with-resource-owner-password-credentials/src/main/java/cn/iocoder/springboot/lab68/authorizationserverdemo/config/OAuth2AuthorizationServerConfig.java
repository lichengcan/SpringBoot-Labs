package cn.iocoder.springboot.lab68.authorizationserverdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 授权服务器配置
 */
@Configuration
//启用授权服务器功能
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 用户认证 Manager
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 授权服务器的端点
     * 设置了认证管理器用于支持密码模式的授权码获取
     * 在密码模式中，客户端将提交用户名和密码，授权服务器将使用AuthenticationManager来验证这些凭据是否有效。
     * 将注入的AuthenticationManager对象设置到授权服务器的端点配置中，以便授权服务器能够使用它来处理各种类型的授权请求，确保客户端的身份验证过程安全有效。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    /**
     * 设置授权服务器的安全性规则，确保只有经过身份验证的用户才能使用有效的访问令牌来访问受保护的资源。
     * 确保只有经过成功认证的用户（已登录用户）才能使用有效的访问令牌来进行受保护资源的访问。如果用户没有通过身份验证，即使拥有有效的访问令牌，也无法访问受保护的资源。
     * checkTokenAccess()方法接受一个字符串参数，用于指定访问令牌的安全性规则。在这里，使用的规则是isAuthenticated()，它表示只有经过身份验证的用户才能访问令牌
     * permitAll()，它表示所有用户都被允许访问令牌密钥。
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("isAuthenticated()");
//        oauthServer.tokenKeyAccess("isAuthenticated()")
//                .checkTokenAccess("isAuthenticated()");
//        oauthServer.tokenKeyAccess("permitAll()")
//                .checkTokenAccess("permitAll()");
    }

    /**
     * 配置了客户端的信息、内存存储方式
     * 指定了一个名为 "clientapp" 的客户端，设置了它的密码、授权方式（这里是密码模式）、以及作用域（scope）。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("clientapp").secret("112233")
                // Client 账号、密码。
                .authorizedGrantTypes("password")
                // 密码模式
                .scopes("read_userinfo", "read_contacts")
        // 可授权的 Scope
//                .and().withClient() // 可以继续配置新的 Client
        ;
    }

}
