package cn.iocoder.springboot.lab01.shirodemo.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 身份验证
     * 授权
     * @return
     */
    @Bean
    public Realm realm() {
        // 创建 SimpleAccountRealm 对象
        SimpleAccountRealm realm = new SimpleAccountRealm();
        // 添加两个用户。参数分别是 username、password、roles 。
        realm.addAccount("admin", "admin", "ADMIN");
        realm.addAccount("normal", "normal", "NORMAL");
        return realm;
    }

    /**
     * DefaultWebSecurityManager 创建好就完事
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        // 创建 DefaultWebSecurityManager 对象
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置其使用的 Realm
        securityManager.setRealm(this.realm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        // 创建 ShiroFilterFactoryBean 对象，用于创建 ShiroFilter 过滤器
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        // 设置 SecurityManager
        filterFactoryBean.setSecurityManager(this.securityManager());

        // 设置 URL 们
        filterFactoryBean.setLoginUrl("/login"); // 登陆 URL
        filterFactoryBean.setSuccessUrl("/login_success"); // 登陆成功 URL
        filterFactoryBean.setUnauthorizedUrl("/unauthorized"); // 无权限 URL

        // 设置 URL 的权限配置
        filterFactoryBean.setFilterChainDefinitionMap(this.filterChainDefinitionMap());

        return filterFactoryBean;
    }

    /**
     * 设置URL的权限配置
     * @return
     */
    private Map<String, String> filterChainDefinitionMap() {
        Map<String, String> filterMap = new LinkedHashMap<>(); // 注意要使用有序的 LinkedHashMap ，顺序匹配
        filterMap.put("/test/echo", "anon"); // 允许匿名访问
        filterMap.put("/test/admin", "roles[ADMIN]"); // 需要 ADMIN 角色
        filterMap.put("/test/normal", "roles[NORMAL]"); // 需要 NORMAL 角色
        filterMap.put("/logout", "logout"); // 退出
        filterMap.put("/**", "authc"); // 默认剩余的 URL ，需要经过认证
        return filterMap;
    }

//    anon ：AnonymousFilter ：允许匿名访问，即无需登录。
//    authc ：FormAuthenticationFilter ：需要经过认证的用户，才可以访问。如果是匿名用户，则根据 URL 不同，会有不同的处理：
//    如果拦截的 URL 是 GET loginUrl 登录页面，则进行该请求，跳转到登录页面。
//    如果拦截的 URL 是 POST loginUrl 登录请求，则基于请求表单的 username、password 进行认证。认证通过后，默认重定向到 GET loginSuccessUrl 地址。
//    如果拦截的 URL 是其它 URL 时，则记录该 URL 到 Session 中。在用户登录成功后，重定向到该 URL 上。
//    logout ：LogoutFilter ：拦截的 URL ，执行退出操作。退出完成后，重定向到 GET loginUrl 登录页面。
//    roles ：RolesAuthorizationFilter ：拥有指定角色的用户可访问。
//    perms ：PermissionsAuthorizationFilter ：拥有指定权限的用户可以访问。
}
