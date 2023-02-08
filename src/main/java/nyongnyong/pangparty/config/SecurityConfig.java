package nyongnyong.pangparty.config;

import lombok.RequiredArgsConstructor;
import nyongnyong.pangparty.util.JwtTokenFilterConfigurer;
import nyongnyong.pangparty.util.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity//(debug = true)
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
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
                .exceptionHandling().accessDeniedPage("/account/login")
                .and()
                .apply(new JwtTokenFilterConfigurer(jwtTokenProvider))
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
