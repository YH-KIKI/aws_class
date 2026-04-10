
package kr.hi.fast.controller;

import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class APIController {
	
	private final WebClient webClient;

	@PostMapping("/image")
	public String image(@RequestParam("image")MultipartFile file) {
		MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
		//보낼 데이터를 추가
		bodyBuilder.part("msg", "데이터 갔나요?");
		bodyBuilder.part("file", file.getResource());
		
		return webClient.post().uri("/image")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters
						.fromMultipartData(bodyBuilder.build()))
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
	
	@PostMapping("/text")
	public String text(@RequestParam("msg")String msg) {
		MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
		//보낼 데이터를 추가
		bodyBuilder.part("msg", msg);
		
		return webClient.post().uri("/text")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters
						.fromMultipartData(bodyBuilder.build()))
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
	@GetMapping("/movies")
	public String movies() {
		
		//return "[{\"title\" : \"Avatar\"}]";
		return webClient.get().uri("/movies") 
				 .retrieve() 
				 .bodyToMono(String.class)
				 .block();
	}
	
	@GetMapping("/movies/recommend")
	public String movieRecommend(
				@RequestParam("title")String title, 
				@RequestParam("type")String type) {
		MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
		//보낼 데이터를 추가
		bodyBuilder.part("title", title);
		bodyBuilder.part("type", type);

		return webClient.post().uri("/movies/recommend")
				 .contentType(MediaType.MULTIPART_FORM_DATA) 
				 .body(BodyInserters
						 .fromMultipartData(bodyBuilder.build())) 
				 .retrieve()
				 .bodyToMono(String.class) 
				 .block();
 
	}
	@PostMapping("/fashion/predict")
	public String fashionPredict(@RequestParam("image")MultipartFile file) {
		MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
		//보낼 데이터를 추가
		bodyBuilder.part("file", file.getResource());
		
		return webClient.post().uri("/fashion")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters
						.fromMultipartData(bodyBuilder.build()))
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
	@PostMapping("/chatbot")
	public String chatbot(@RequestParam("msg")String msg) {
		MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
		//보낼 데이터를 추가
		bodyBuilder.part("msg", msg);
		
		return webClient.post().uri("/chatbot")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters
						.fromMultipartData(bodyBuilder.build()))
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
}
