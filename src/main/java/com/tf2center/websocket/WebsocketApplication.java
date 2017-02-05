package com.tf2center.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@EnableWebSocket
@EnableScheduling
@SpringBootApplication
@EnableAutoConfiguration
public class WebsocketApplication implements WebSocketConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler(), "/ws").setAllowedOrigins("*");
	}

	@Bean
	public WebSocketHandler webSocketHandler() {
		return new TF2CenterWebsocketHandler();
	}

	// CloudFlare will take care of HTTP/2
//	@Bean
//	public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
//		UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
//		factory.addBuilderCustomizers(builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
//		return factory;
//	}

//	@Bean
//	public JedisConnectionFactory connectionFactory() {
//		//Kubernetes service redis-sentinel
//        return new JedisConnectionFactory(new JedisShardInfo("10.111.246.146", 26379));
//    }
}