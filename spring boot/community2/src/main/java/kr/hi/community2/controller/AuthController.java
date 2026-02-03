package kr.hi.community2.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.hi.community2.model.dto.LoginDTO;
import kr.hi.community2.security.jwt.JwtTokenProvider;
import kr.hi.community2.service.MemberDetailService;
import kr.hi.community2.service.UserService;
import kr.hi.community2.util.CustomUser;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
	
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
	private final MemberDetailService userDetailsService;
	
	@PostMapping("/signup")
	public ResponseEntity<Boolean> signup(
			@RequestBody LoginDTO user ){
		// System.out.println(user); 로그인 정보 잘 넘어오는지 확인
		boolean res = userService.signup(user);
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(
			@RequestBody LoginDTO request,
			HttpServletResponse response) {
		 	
	 	//사용자의 아이디, 비번을 시큐리티에 넘겨서 유효한 사용자인지 확인
	 	//->MemberDetailService에서 확인을 진행
        Authentication authentication = authenticationManager.authenticate(
        	//인증이 안된 사용자가 인증을 요청
            new UsernamePasswordAuthenticationToken(
                request.id(), request.pw()
            )
        );
        //위에서 올바른 정보이면 CustomUser객체를 반환하고, 아니면 null을 반환
        CustomUser user = (CustomUser) authentication.getPrincipal();
        //토큰 생성
        String token = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);
		
	    // RefreshToken → HttpOnly Cookie
	    Cookie cookie = new Cookie("refreshToken", refreshToken);
	    cookie.setHttpOnly(true);
	    cookie.setSecure(false); // https면 true
	    cookie.setPath("/");
	    cookie.setMaxAge(7 * 24 * 60 * 60);
	    response.addCookie(cookie);
        //생성한 토큰을 화면에 전달. accessToken으로 전송
        return ResponseEntity.ok(Map.of("accessToken", token));
    }
	
	@GetMapping("/test")
	public ResponseEntity<Boolean> test(
			@AuthenticationPrincipal CustomUser user){
		return ResponseEntity.ok(true);	
	}
	
	@GetMapping("/me")
	public ResponseEntity<Map<String, Object>> me(
			@AuthenticationPrincipal CustomUser user){
		
		return ResponseEntity.ok(Map.of(
				"id", user.getUser().getMe_id(),
				"email", user.getUser().getMe_email(),
				"role", user.getUser().getMe_role()));	
	}
	
	//리프레쉬 토큰을 이용하여 토큰을 재발급
	@PostMapping("/refresh")
	public ResponseEntity<?> refresh(
		//name과 일치하는 쿠키가 있으면 	
	    @CookieValue(name = "refreshToken", required = false) String refreshToken
	) {
		//리프레쉬 토큰이 없으면 인증 실패
	    if (refreshToken == null) {
	        return ResponseEntity.status(401).build();
	    }
	    //토큰이 리프레쉬 토큰이 맞는지 확인. 아니면 인증 실패
	    if (!jwtTokenProvider.isRefreshToken(refreshToken)) {
	        return ResponseEntity.status(401).build();
	    }
	    
	    //토큰에서 정보를 추출
	    Claims claims = jwtTokenProvider.parseClaims(refreshToken);
	
	    //토큰 소유주(아이디 또는 이메일)정보를 가져옴
	    String username = claims.getSubject();
	    //소유주 정보로 회원 정보를 가져옴
	    CustomUser user =
	        (CustomUser) userDetailsService.loadUserByUsername(username);
	    //회원 정보를 이용해서 토큰을 생성 
	    String newAccessToken =
	        jwtTokenProvider.createAccessToken(user);
	    //토큰을 화면에 전달
	    return ResponseEntity.ok(
	        Map.of("accessToken", newAccessToken)
	    );
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse response) {
		
		//쿠키에서 리프레쉬 토큰 제거
	    Cookie cookie = new Cookie("refreshToken", null);
	    cookie.setHttpOnly(true);
	    cookie.setSecure(false); // https면 true
	    cookie.setPath("/");
	    cookie.setMaxAge(0); // 즉시 삭제
	
	    response.addCookie(cookie);
	
	    return ResponseEntity.ok().build();
	}

}
