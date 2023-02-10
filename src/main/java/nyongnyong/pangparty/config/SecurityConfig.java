package nyongnyong.pangparty.config;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.jwt.JwtAccessDeniedHandler;
import nyongnyong.pangparty.jwt.JwtAuthenticationEntryPoint;
import nyongnyong.pangparty.jwt.JwtTokenFilterConfigurer;
import nyongnyong.pangparty.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity//(debug = true)
public class SecurityConfig {

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtTokenProvider jwtTokenProvider;
    private final CorsFilter corsFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
//                .accessDeniedPage("/account/login")
                .and()
                .apply(new JwtTokenFilterConfigurer(jwtTokenProvider))
                .and()
                .authorizeRequests()
//                .antMatchers("/account/login").permitAll()
//                .antMatchers("/account/register").permitAll()
//                .antMatchers("/account/refreshtoken").permitAll()
//                .antMatchers("/account/verify-email").permitAll()
//                .antMatchers("/account/change-password").permitAll()
//                .antMatchers("/events/*").permitAll()
//                .antMatchers("/stickers").permitAll()
//                .antMatchers("/member/**").hasRole("USER")
//                .anyRequest().authenticated()
                .anyRequest().permitAll() // TODO for testing purposes
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
