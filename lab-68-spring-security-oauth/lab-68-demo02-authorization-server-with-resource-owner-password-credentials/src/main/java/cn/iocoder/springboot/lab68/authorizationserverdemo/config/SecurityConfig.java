package cn.iocoder.springboot.lab68.authorizationserverdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
//启用Web安全模块
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 身份验证管理器（AuthenticationManager）
     * @return
     * @throws Exception
     */
    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 密码编码器
     * @return
     */
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    /**
     * 通过重写configure(AuthenticationManagerBuilder auth)方法
     * 使用内存中的用户详细信息管理器（InMemoryUserDetailsManager）来配置用户信息，并指定密码编码器为上一步注册的NoOpPasswordEncoder。
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                // 使用内存中的 InMemoryUserDetailsManager
                inMemoryAuthentication()
                // 不使用 PasswordEncoder 密码编码器
                .passwordEncoder(passwordEncoder())
                // 配置 yunai 用户
                .withUser("lichengcan").password("1024").roles("USER");
    }

}
