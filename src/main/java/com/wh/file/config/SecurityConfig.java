package com.wh.file.config;

import com.wh.file.filter.JwtAuthenticationTokenFilter;
import com.wh.file.handler.AccessDeniedHandlerImpl;
import com.wh.file.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 密码加密存储
 *
 * @author WuHao on 13:49 2022/5/18
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    // 创建BCryptPasswordEncoder注入容器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/v2/api-docs-ext/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/doc.html");
    }

    // 2.放行
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                // 因为前后端分离项目，所以得关闭csrf
                csrf().disable()
                // 因为前后端分离项目，所以不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口，anonymous允许匿名访问，即使用户没用登录也可以访问这个接口
                .antMatchers("/user/login", "/user/register").anonymous()
                .antMatchers("/swagger-ui.html").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/doc.html/**").anonymous()
                // 除上面外的所有请求都需要鉴权认证
                .anyRequest().authenticated();

        // 将过滤器添加到UsernamePasswordAuthenticationFilter.class之前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置异常处理器--
        http.exceptionHandling()
                // 认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                // 授权失败处理器
                .accessDeniedHandler(accessDeniedHandler);

        // 允许跨域
        http.cors();
    }

    // 1.认证，
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
