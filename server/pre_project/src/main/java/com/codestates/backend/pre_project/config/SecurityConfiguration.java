package com.codestates.backend.pre_project.config;

import com.codestates.backend.pre_project.auth.filter.JwtAuthenticationFilter;
import com.codestates.backend.pre_project.auth.filter.JwtVerificationFilter;
import com.codestates.backend.pre_project.auth.handler.MemberAccessDeniedHandler;
import com.codestates.backend.pre_project.auth.handler.MemberAuthenticationEntryPoint;
import com.codestates.backend.pre_project.auth.handler.MemberAuthenticationFailureHandler;
import com.codestates.backend.pre_project.auth.handler.MemberAuthenticationSuccessHandler;
import com.codestates.backend.pre_project.auth.jwt.JwtTokenizer;
import com.codestates.backend.pre_project.auth.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CorsFilter corsFilter;
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

//    public SecurityConfiguration(JwtTokenizer jwtTokenizer,
//                                 CorsFilter corsFilter,
//                                 CustomAuthorityUtils authorityUtils) {
//        this.jwtTokenizer = jwtTokenizer;
//        this.corsFilter = corsFilter;
//
//        this.authorityUtils = authorityUtils;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().sameOrigin()
            .and()
            .csrf().disable()
            //.cors(withDefaults())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .exceptionHandling()
            .authenticationEntryPoint(new MemberAuthenticationEntryPoint())  // 추가
            .accessDeniedHandler(new MemberAccessDeniedHandler())            // 추가
            .and()
            .apply(new CustomFilterConfigurer())
            .and()
            .authorizeHttpRequests(authorize -> authorize
                    .antMatchers(HttpMethod.POST, "/*/members").permitAll()
                    .antMatchers(HttpMethod.PATCH, "/*/members/**").hasRole("USER")
                    .antMatchers(HttpMethod.GET, "/*/members").hasRole("ADMIN")
//                    .mvcMatchers(HttpMethod.GET, "/*/members").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/*/members/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/*/members/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers(HttpMethod.POST, "/*/questions").hasAnyRole("ADMIN", "USER")
                    .antMatchers(HttpMethod.PATCH, "/*/question/**").hasRole("USER")
                            .antMatchers(HttpMethod.DELETE, "/*/question/**").hasRole("USER")
                    .antMatchers(HttpMethod.GET, "/*/answers/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers(HttpMethod.GET, "/*/answers").permitAll()
                    .antMatchers(HttpMethod.DELETE, "/*/answers/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers(HttpMethod.POST, "/**/answers/**").hasRole("USER")
                    .antMatchers(HttpMethod.PATCH, "/**/answers/**").hasAnyRole("USER", "ADMIN")
                            .antMatchers(HttpMethod.DELETE, "/**/answers/**").hasAnyRole("USER", "ADMIN")
                            .antMatchers(HttpMethod.POST, "/*/profiles").hasRole("USER")
                     .antMatchers(HttpMethod.PATCH, "/*/profiles/**").hasAnyRole("USER", "ADMIN")
                            .antMatchers(HttpMethod.GET, "/*/profiles/**").hasAnyRole("USER", "ADMIN")
                            .antMatchers(HttpMethod.GET, "/**/comment").hasRole("USER")
                            .antMatchers(HttpMethod.OPTIONS,"*/member/*").permitAll()
                    .anyRequest().permitAll()
            );
        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);


            builder
                    .addFilter(corsFilter)
                .addFilter(jwtAuthenticationFilter)
                .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
