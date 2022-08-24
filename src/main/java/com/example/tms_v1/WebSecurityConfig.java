package com.example.tms_v1;

import com.example.tms_v1.security.AuthEntryPoint;
import com.example.tms_v1.security.AuthTokenFilter;
import com.example.tms_v1.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthEntryPoint unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // This Origin header you can see that in Network tab
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
//                login
                .antMatchers("/signin").permitAll()
                .antMatchers("/").permitAll()
//                tokens
                .antMatchers("/tokens/new").hasAnyAuthority("ROLE_PATIENT")
                .antMatchers("/tokens/all").hasAnyAuthority("ROLE_MANAGER")
                .antMatchers("tokens/all/{patientId}").hasAnyAuthority("ROLE_PATIENT", "ROLE_MANAGER")
                .antMatchers(HttpMethod.POST,"/tokens/deactivate").hasAnyAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.POST,"/tokens/delete").hasAnyAuthority("ROLE_MANAGER")
//                users
                .antMatchers(HttpMethod.POST,"/users/new").hasAnyAuthority("ROLE_MANAGER")
                .antMatchers(HttpMethod.POST,"/user/update").hasAnyAuthority("ROLE_PATIENT", "ROLE_MANAGER")
                .antMatchers(HttpMethod.POST,"/user/details").hasAnyAuthority("ROLE_PATIENT", "ROLE_MANAGER")
                .antMatchers(HttpMethod.POST,"user/delete").hasAnyAuthority("ROLE_MANAGER")
//                test
                .antMatchers(HttpMethod.DELETE,"user/delete/{userId}").hasAnyAuthority("ROLE_MANAGER")

                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

