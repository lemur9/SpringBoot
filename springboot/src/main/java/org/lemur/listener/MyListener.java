package org.lemur.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("/myListener")
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("listener init....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("listener destroyed....");
    }
}
