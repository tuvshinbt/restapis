/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.pre.prob.solstice.challenge.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author tuvshuu
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/restapi/contact**").access("hasRole('ROLE_EXT')")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    /**
     * IN MEMORY authentication application.properties
     *
     * @Bean
     * @Override public UserDetailsService userDetailsService() {
     * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
     * manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
     * return manager; }
     */
}
