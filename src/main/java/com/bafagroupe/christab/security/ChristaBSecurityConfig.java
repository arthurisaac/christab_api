package com.bafagroupe.christab.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ChristaBSecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/findUtilisateurs/**", "/api/createUtilisateur/**", "/api/findTypeFonctions/**", "/api/updatePassword/**", "/api/findPath/**").permitAll()
                .antMatchers("/api/findUtilisateurByEmail/**", "/api/updateUtilisateur/**").hasAnyAuthority(SecurityParams.ADMIN, SecurityParams.USER)
                /*.antMatchers(HttpMethod.POST,"/api/createUtilisateur/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/findTypeFonctions/**").permitAll()*/
                .antMatchers("/utilisateurs/**", "/historiques/**", "/typeFonctions/**",
                        "/avises/**", "/paiements/**", "/demandes/**", "/alertes/**", "/annonces/**",
                        "/itineraires/**", "/engins/**", "/api/findHistoriques/**").hasAnyAuthority(SecurityParams.ADMIN)
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
