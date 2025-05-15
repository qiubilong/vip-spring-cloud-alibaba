package com.tuling.malluserloadbalancerdemo.controller;

import com.tuling.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**

 * @author fox
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/findOrderByUserId/{id}")
    public R  findOrderByUserId(@PathVariable("id") Integer id) {

        //restTemplate需要添加@LoadBalanced注解
        String url = "http://mall-order/order/findOrderByUserId/"+id;

        R result = restTemplate.getForObject(url,R.class);

        return result;
    }

 /*   @Autowired
    private WebClient webClient;

    @RequestMapping(value = "/findOrderByUserId2/{id}")
    public Mono<R> findOrderByUserIdWithWebClient(@PathVariable("id") Integer id) {

        String url = "http://mall-order/order/findOrderByUserId/"+id;
        //基于WebClient
        Mono<R> result = webClient.get().uri(url).retrieve().bodyToMono(R.class);
        return result;
    }

    @Autowired
    private ReactorLoadBalancerExchangeFilterFunction lbFunction;

    @RequestMapping(value = "/findOrderByUserId3/{id}")
    public Mono<R> findOrderByUserIdWithWebFlux(@PathVariable("id") Integer id) {

        String url = "http://mall-order/order/findOrderByUserId/"+id;
        //基于WebClient+webFlux
        Mono<R> result = WebClient.builder()
                .filter(lbFunction)
                .build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(R.class);
        return result;
    }*/


}
