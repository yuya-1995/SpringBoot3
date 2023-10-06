package com.example.samplewebfluxapp;

import org.springframework.stereotype.Controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import org.springframework.web.reactive.function.server.ServerRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.Rendering;
import org.springframework.http.MediaType;

import org.springframework.ui.Model;

@Controller
public class SampleController {
	
	//Laravelでいう「ルーティング」的な？
	@Bean
	public RouterFunction<ServerResponse> routes(){
		return route(GET("/f/hello"), this::hello)
				.andRoute(GET("/f/hello2"), this::hello2)
				.andRoute(GET("/f/flux2"), this::flux2);
	}
	
	Mono<ServerResponse> hello(ServerRequest req){
		return ok().body(Mono.just("Hello Functional routing world!"), String.class);
	}
	
	Mono<ServerResponse> hello2(ServerRequest req){
		return ok().body(Mono.just("関数ルーティングの世界へようこそ！"), String.class);
	}
	
	@RequestMapping("/f/flux")
	Mono<Rendering> flux(Model model){
		model.addAttribute("title",	"Flux/Request Handler");
		model.addAttribute("msg", "これはリクエストハンドラのサンプルです。");
		return Mono.just(Rendering.view("flux").build());
	}
	
	//ルーティン利用時のテンプレートエンジンへの値渡し
	Mono<ServerResponse> flux2(ServerRequest req){
		 Map<String, Object> model = new HashMap<>();
		    model.put("title", "My Page Title");
		    model.put("msg", "Hello, Thymeleaf!");
		return ok().contentType(MediaType.TEXT_HTML).render("flux", model);
	}
	
}
