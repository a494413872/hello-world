package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     * uri是跳转后的uri。后面会拼上你请求的get或者delay/3
     * delay/3是delay3秒的意思。这时候才会触发断路器
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p.path("/get").filters(f->f.addRequestHeader("hello","world"))
                            .uri("http://httpbin.org:80"))
                .route(p->p.path("/delay/3")
                            .filters(f->f.hystrix(config -> config.setName("mycmd").setFallbackUri("forward:/fallback"))
                            ).uri("http://httpbin.org:80"))
                .build();
    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }
}
