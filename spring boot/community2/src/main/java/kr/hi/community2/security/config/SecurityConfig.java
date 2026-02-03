package kr.hi.community2.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import kr.hi.community2.security.filter.JwtAuthenticationFilter;
import kr.hi.community2.service.MemberDetailService;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	 private final JwtAuthenticationFilter jwtAuthenticationFilter;
	 private final MemberDetailService userDetailsService;
	
	//암호화 하는 클래스
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 초기 개발 시 CSRF 비활성화
            //@CroosOrgin 대신 사용 => security에 의해 동작이 안될 수도 있고,
            //매번 컨트롤러를 추가할 때 마다 어노테이션을 추가하기 번거롭기 때문에.
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            //JWT방식은 세션을 유지하지 않음. => 세션리스 => 세션없이 사용
            .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )	
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/auth/**",
                		"/인증이필요없는URL추가1", "/인증이필요없는URL추가2")
                //인증없이 접근하도록 설정
                .permitAll()
                //다음 URL에 대해
                .requestMatchers("/admin/**")
                //관리자 권한만 접근하도록 설정
                .hasAuthority("ADMIN")
                //위 URL이 아닌 경
                .anyRequest()
                //인증이 필요함
                .authenticated()
            )
            //사용자 정보를 누굴 통해 접근할지를 지정
            //UserDetailService를 구현한 구현 클래스의 객체만 올 수 있음
        	.userDetailsService(userDetailsService)
        	//jwtAuthenticationFilter를 아이디/비번 인증 필터보다 먼저 실행
        	//필터를 추가
        	.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
    //컨트롤러에서 쓸 인증 매니저를 빈에 등록 -> 의존성 주입 가능
    @Bean
    public AuthenticationManager authenticationManager(
           AuthenticationConfiguration config
    )throws Exception {
        return config.getAuthenticationManager();
    }

}