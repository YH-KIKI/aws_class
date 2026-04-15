package kr.hi.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ai")
public class AIController {
	
	private final WebClient webClient;
	
	@GetMapping("/ask")
	public String ask(@RequestParam("prompt") String prompt) {
	    String result = webClient.get()
	      .uri(uriBuilder-> uriBuilder
	    		  .path("/ask")
	    		  .queryParam("prompt", prompt)
	    		  .build())
	      .retrieve()
	      .bodyToMono(String.class)
	      .block();
		return result;
	}
	


}
