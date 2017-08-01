package com.github.awesomechat.chat.config;

import com.github.awesomechat.chat.ServletCxtListener;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.Set;


@Configuration
@EnableWebMvc
@ComponentScan("com.github.awesomechat.chat")
public class WebConfig extends WebMvcConfigurerAdapter {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/templates/", "classpath: /css/" };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       // registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        //registry.addResourceHandler("/css/**").addResourceLocations("classpath:/resources/static/css/");
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Bean
    public ServletContextListener servletCxtListener() {
        return new ServletCxtListener();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

/*    @Bean
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        Set<IDialect> dialects = new HashSet<>();
        dialects.add(new LayoutDialect());

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        engine.setAdditionalDialects(dialects);
        return engine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setOrder(1);
        resolver.setViewNames(new  String[]{"*", "js/*", "template/*"});
        return resolver;
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }*/
}
