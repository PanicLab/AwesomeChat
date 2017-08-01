package com.github.awesomechat.chat;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



@WebListener
public class ServletCxtListener implements ServletContextListener {
    private static ServletContext servletContext;

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
        servletContext = null;
    }

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        servletContext = contextEvent.getServletContext();
    }

    public ServletContext getContext() {
        return servletContext;
    }

    public static ServletContext getApplicationCntx(){
        return servletContext;
    }
}
