package com.jrp.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //This is used to get the username and authentication using query from the database
    @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password,enabled" + " from user_account where username=?")
                .authoritiesByUsernameQuery("select username,role " + " from user_account where username=?")
                .passwordEncoder(bCryptPasswordEncoder);
    }

    //This is used for jdbc authentication it will create the table using the default schema
   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .withDefaultSchema()
                .withUser("myuser")
                .password("pass")
                .roles("USER")
                .and()
                .withUser("vivek")
                .password("pass2")
                .roles("USER")
                .and()
                .withUser("managerUser")
                .password("pass4")
                .roles("ADMIN");
    }*/
    /*//This is used for authentication in memory database
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("myuser")
                .password("pass")
                .roles("USER")
                .and()
                .withUser("vivek")
                .password("pass2")
                .roles("USER")
                .and()
                .withUser("managerUser")
                .password("pass4")
                .roles("ADMIN");

    }*/

    /*@Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/
    // This will be used for authorization ( h2 databasee)
   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/projects/new").hasRole("ADMIN")
                .antMatchers("/employees/new").hasRole("ADMIN")
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/").authenticated()
                .and()
                .formLogin();
       //used only from h2 console database
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }*/

    //Authorization for sql database
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/projects/new").hasRole("ADMIN")
                .antMatchers("/employees/new").hasRole("ADMIN")
                .antMatchers("/employees/save").hasRole("ADMIN")
                .antMatchers("/projects/save").hasRole("ADMIN")
                .antMatchers("/","/**").permitAll()
                .and()
                .formLogin();

    }
}
