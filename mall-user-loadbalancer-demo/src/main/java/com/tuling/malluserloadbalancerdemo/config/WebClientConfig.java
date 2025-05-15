package com.tuling.malluserloadbalancerdemo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
@Configuration
public class WebClientConfig {

  @LoadBalanced
  @Bean
  WebClient.Builder webClientBuilder() {
    return WebClient.builder();
  }


  @Bean
  WebClient webClient() {
    return webClientBuilder().build();
  }

}*/
