package kr.hi.community.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${file.upload-dir}")
	String uploadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//URL이 /download/로 시작되면 C:/upload/ 폴더를 확인하여 파일을 연결
		registry
			// URL이 /download/로 시작되면
			.addResourceHandler("/download/**") 
			//C:/upload/폴더를 확인하여 파일을 연결 
			.addResourceLocations("file:///" + uploadPath);	//file:/// 슬러시3개는 프로젝트 폴더가아닌 외부폴더 접근할때 사용함
		
	}

}
