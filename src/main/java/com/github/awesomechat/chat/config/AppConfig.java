package com.github.awesomechat.chat.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;


@Configuration
@ComponentScan(basePackages = "com.github.awesomechat.chat",
                excludeFilters = {@ComponentScan.Filter(
                                        type = FilterType.ANNOTATION,
                                        classes = {EnableWebMvc.class, EnableWebSocket.class}
                                )}
                )
public class AppConfig {
}
