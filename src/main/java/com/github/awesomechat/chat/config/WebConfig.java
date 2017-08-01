package com.github.awesomechat.chat.config;

import com.github.awesomechat.chat.ServletCxtListener;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ServletComponentScan
public class WebConfig {

    @Bean
    public ServletCxtListener servletCxtListener() {
        return new ServletCxtListener();
    }
}
