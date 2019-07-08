package com.templates.springproject.security;

import com.templates.springproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserService userDetailsService;




   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    /*    http
        .httpBasic()
                .and()
                .authorizeRequests()
                *//*.antMatchers(HttpMethod.GET, "/client/**").hasRole("client")
                .antMatchers(HttpMethod.POST, "/client/**").hasRole("client")
                .antMatchers(HttpMethod.POST, "/admin").hasRole("admin")
                .antMatchers(HttpMethod.GET, "/admin").hasRole("admin")*//*
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable();*/

        http.csrf()
                        .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint() {
                })
                .and()
                .authenticationProvider(getProvider())
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(new AuthentificationLoginSuccessHandler())
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/noauth").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/client/**").authenticated()
//                .antMatchers("/client/**").hasRole("client")
                .antMatchers("/admin/**").hasAuthority("admin");
    }



    private class AuthentificationLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                                            HttpServletResponse response, Authentication authentication) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }


    private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Bean
    public AuthenticationProvider getProvider() {
        AppAuthProvider provider = new AppAuthProvider();
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }



}
