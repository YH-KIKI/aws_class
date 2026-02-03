package kr.hi.community2.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.hi.community2.security.jwt.JwtTokenProvider;
import kr.hi.community2.service.MemberDetailService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberDetailService userDetailsService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        
        /* 로그인 성공 후
         * fetch에서 headers에 아래와 같이 토큰값을 넣어줌
         * headers : {
				"Content-Type" : "application/json",
				"Authorization" : "Bearer 토큰값"
				 }
         * */
        //hearder에 Authorization이 있고, "Bearer "로 시작하면 => 토큰이 있음
        if (header != null && header.startsWith("Bearer ")) {
        	//"Bearer "를 제외한 나머지를 추출 => 토큰
            String token = header.substring(7);
            //토큰에 있는 payload 정보들을 가져옴
            Claims claims = jwtTokenProvider.parseClaims(token);
            
            if ("refresh".equals(claims.get("type"))) {
		        filterChain.doFilter(request, response);
		        return;
		    }
                        
            //가져온 정보중에 subject 정보를 가져옴 => 토큰 소유주
            String username = claims.getSubject();
            
            //아이디(이메일)를 이용하여 db에 정보가 있는지 검증
            UserDetails userDetails =
            		userDetailsService.loadUserByUsername(username);
            
            //userDetails : 누구인지
            //null : 이미 인증이 끝남
            //userDetails.getAuthorities() : 권한 목록
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            
            //현재 요청에 대해 사용자는 로그인 상태라고 등록
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}