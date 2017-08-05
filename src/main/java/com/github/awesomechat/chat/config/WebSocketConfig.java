package com.github.awesomechat.chat.config;


import com.github.awesomechat.chat.controllers.WebSocketChatHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

/*    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-chat-endpoint");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker("/topic");
    }*/

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketChatHandler(), "/ws-chat");
    }

    @Bean
    public WebSocketHandler webSocketChatHandler() {
        return new WebSocketChatHandler();
    }

/*    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        container.setMaxSessionIdleTimeout(TimeUnit.MINUTES.toMillis(5));
        return container;
    }*/
}
