package kr.hi.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.hi.boot.model.util.UserRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//암호화 하는 클래스를 @Bean에 등록 -> @Autowired를 이용하여 객체 생성 가능
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	 http.csrf(csrf ->csrf.disable())
         .authorizeHttpRequests((requests) -> requests
        	//URL이 /post/insert/*이면 로그인한 회원 중 역할이 USER인 회원만 접근
             .requestMatchers("/post/insert").hasAuthority(UserRole.USER.name())
             //URL이 /admin/으로 시작하는 URL들은 로그인한 회원 중 역할이 ADMIN인 회원만 접근 
             .requestMatchers("/admin/**").hasAnyAuthority(UserRole.ADMIN.name())
             //그 외 URL은 아무나 접근 가능
             .anyRequest().permitAll()  // 그 외 요청은 인증 필요
             //.anyRequest() - 아무 요청 / permitAll - 다 허용
         )
         .formLogin((form) -> form
             .loginPage("/login")  // 커스텀 로그인 페이지 설정
             .permitAll()           // 로그인 페이지는 접근 허용
             //URL은 login이고 post방식일 때 로그인 진행 
             .loginProcessingUrl("/login")
             //로그인 성공하면 메인 페이지로 이동
             .defaultSuccessUrl("/")
         )
         .logout((logout) -> logout
         		.logoutUrl("/logout")	//로그아웃 처리할 URL 지정
         		.logoutSuccessUrl("/")	// 로그아웃 성공하면 메인 페이지로 이동
         		.clearAuthentication(true) 	//권한을 초기화
         		.invalidateHttpSession(true)//세션을 만료
         		.permitAll());  // 로그아웃도 모두 접근 가능
     return http.build();
    }
}