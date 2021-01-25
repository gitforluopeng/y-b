package com.lp.config;

import com.lp.security.MyLoginUrlAuthenticationEntryPoint;
import com.lp.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@EnableWebSecurity
@Configuration
/**
 * defaultSuccessUrl 和 successForwardUrl 只需要配置一个即可，具体配置哪个，则要看需求，两个的区别如下：
 *
 * defaultSuccessUrl 有一个重载的方法，我们先说一个参数的 defaultSuccessUrl 方法。如果我们在 defaultSuccessUrl 中指定登录成功的跳转页面为 /index，此时分两种情况，如果你是直接在浏览器中输入的登录地址，登录成功后，就直接跳转到 /index，如果你是在浏览器中输入了其他地址，例如 http://localhost:8080/hello，结果因为没有登录，又重定向到登录页面，此时登录成功后，就不会来到 /index ，而是来到 /hello 页面。
 * defaultSuccessUrl 还有一个重载的方法，第二个参数如果不设置默认为 false，也就是我们上面的的情况，如果手动设置第二个参数为 true，则 defaultSuccessUrl 的效果和 successForwardUrl 一致。
 * successForwardUrl 表示不管你是从哪里来的，登录后一律跳转到 successForwardUrl 指定的地址。例如 successForwardUrl 指定的地址为 /index ，你在浏览器地址栏输入 http://localhost:8080/hello，结果因为没有登录，重定向到登录页面，当你登录成功之后，就会服务端跳转到 /index 页面；或者你直接就在浏览器输入了登录页面地址，登录成功后也是来到 /index
 *
 * 与登录成功相似，登录失败也是有两个方法：
 *
 * failureForwardUrl
 * failureUrl
 * 这两个方法在设置的时候也是设置一个即可。failureForwardUrl 是登录失败之后会发生服务端跳转，failureUrl 则在登录失败之后，会发生重定向。
 */
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //放开这两个权限
                .antMatchers("/auth/un-auth", "/auth/session-time-out").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()//默认开启，这里先显式关闭
                .formLogin()  //内部注册 UsernamePasswordAuthenticationFilter
                //.loginProcessingUrl("/login")//form表单POST请求url提交地址，默认为/login
                .successForwardUrl("/auth/success")  //登录成功跳转地址
                .failureForwardUrl("/auth/failure") //登录失败跳转地址
                //.failureUrl("/auth/fail")
                //.defaultSuccessUrl()//如果用户没有访问受保护的页面，默认跳转到页面
                //.failureUrl()
                //.failureHandler(AuthenticationFailureHandler)
                //.successHandler(AuthenticationSuccessHandler)
                //.failureUrl("/login?error")
                .permitAll()//允许所有用户都有权限访问登录页面
                .and()
                .sessionManagement()
                .invalidSessionUrl("/auth/session-time-out")
                //集成spring security时，因为是前后端分离，所以不能跳转到登陆页面，而是返回未登陆的JSON串
                .and()
                .logout().deleteCookies("JSESSIONID").invalidateHttpSession(false)
                .and().exceptionHandling().authenticationEntryPoint(myLoginUrlAuthenticationEntryPoint());
    }

    @Bean
    public AuthenticationEntryPoint myLoginUrlAuthenticationEntryPoint() {
        return new MyLoginUrlAuthenticationEntryPoint("/auth/un-auth");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置认证管理器
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置userDetailsService ,这是用来查询用户信息的
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
        //设置擦除密码，如果设置成false，那么在spring security中流转的用户认证信息都会带有密码，一般我们都会擦除
        auth.eraseCredentials(true);
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/druid/**", "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs");
    }
}
