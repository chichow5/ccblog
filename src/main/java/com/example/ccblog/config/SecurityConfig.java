package com.example.ccblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private BlogConfig blogConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/article/**","/backend/**","/","/comm/**","/page/**","/image/**","/error").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/admin").hasRole("admin");

        http.formLogin()
                .loginPage("/userLogin").permitAll()
                .usernameParameter("name").passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error");

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        http.rememberMe()
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(blogConfig.token_valid_sec)
                .tokenRepository(tokenRepository());
    }

    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl jr = new JdbcTokenRepositoryImpl();
        jr.setDataSource(dataSource);
        return jr;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        String userSQL = "select username,password,valid  from "
                + blogConfig.tb_user + " where username = ?";
        String authoritySQL = "select u.username,a.authority  from "
                + blogConfig.tb_user + " u,"
                + blogConfig.tb_auth + " a,"
                + blogConfig.tb_user_auth + " ua "
                //+ " where ua.user_id=u.id and ua.authority_type=a.id and u.username=?";
                + " where ua.user_id=u.id and ua.authority_type=a.id and u.username=?";
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery(userSQL)
                .authoritiesByUsernameQuery(authoritySQL);
    }
}
