package kr.hi.community.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.hi.community.model.util.UserRole;
import kr.hi.community.service.MemberDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	MemberDetailService memberDetailService;
	
	//암호화 하는 클래스
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf ->csrf.disable())
            .authorizeHttpRequests((requests) -> requests
            	//로그인한 사용자(USER)만 접근할 수 있는 URL 설정
                .requestMatchers("/post/insert/*").hasAuthority(UserRole.USER.name())
                //로그인한 관리자(ADMIN)만 접근할 수 있는 URL 설정
                .requestMatchers("/admin/**").hasAnyAuthority(UserRole.ADMIN.name()) 
                .anyRequest().permitAll()  // 그 외 요청은 인증 필요
            )
            .formLogin((form) -> form
            	//스프링 시큐리티가 제공하는 기본 록읜 화면 안쓰고 만들어서 쓸 경우
            	//연결할 URL 지정
                .loginPage("/login")  // 커스텀 로그인 페이지 설정
                //모든 사용자가 로그인 페이지에 접근하도록 허용
                .permitAll()
                //로그인 처리할 URL을 지정. method는 post
                .loginProcessingUrl("/login")//
                //로그인 성공 후 URL
                .defaultSuccessUrl("/")
            )
            /*자동로그인은 클라이언트(사용자) 컴퓨터에 쿠키를 만들어서 활용
             * -> 동일한 이름(LC) 쿠키가 있는지 확인해서 있으면 값을 가져와 로그인을 함 
             * */     
            //자동 로그인 구현 (람다식 사용)
            .rememberMe(rm-> rm 
            	//자동로그인이 체크되어 있으면 memberDetailService를 이용해서 로그인
            	.userDetailsService(memberDetailService)
            	//쿠키에 저장할 토큰을 생성할 때 활용할 문자열
            	//이 문자열이 바뀌면 이전에 있던 토큰이 무효화 되어 자동 로그인 취소 
            	//key에 들어가는 문자열은 노출되면 안됨.
            	//application.properties에 작성해서 관리해야 함.
            	.key("abc123") //아무 문자열 OK
            	.rememberMeCookieName("LC")
            	.tokenValiditySeconds(60*60*24*7)
            	)
            
            .logout((logout) -> logout
            	//로그아웃 처리 URL을 지정. 방식은 post 
        		.logoutUrl("/logout")
        		//로그아웃 성공시 이동할 URL을 지정 
        		.logoutSuccessUrl("/")
        		//인증을 비움
        		.clearAuthentication(true)
        		//세션을 만료
        		.invalidateHttpSession(true)
        		//로그아웃으 ㄹ모든 사용자가 접근하도록 허용
        		.permitAll());  // 로그아웃도 모두 접근 가능
        return http.build();
    }

}