//package com.example.demo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Service
//public class WebClientService {
//
//    private final WebClient.Builder webClientBuilder;
//
//    @Autowired
//    public WebClientService(WebClient.Builder webClientBuilder) {
//        this.webClientBuilder = webClientBuilder;
//    }
//
//    public void sendHttpRequestWithId(String id) {
//        WebClient client = webClientBuilder.baseUrl("http://other-application-url").build();
//        
//        client.get()
//              .uri(uriBuilder -> uriBuilder.path("/api/endpoint")
//                                            .queryParam("id", id)
//                                            .build())
//              .retrieve()
//              .bodyToMono(String.class)
//              .subscribe(response -> {
//                  // Handle the response from the other application
//                  System.out.println("Response from other application: " + response);
//              });
//    }
//
//}
