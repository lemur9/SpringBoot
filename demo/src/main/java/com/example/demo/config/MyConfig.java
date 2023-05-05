package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 配置线程池
 */
@Configuration
public class MyConfig {
    /*@Bean
    public ThreadPoolTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(16);
        // 配置最大线程数
        executor.setMaxPoolSize(64);
        //配置队列大小
        executor.setQueueCapacity(9999);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");
        //rejection-policy:当pool已经达到max pool size的时候,如何处理新任务
        //CALLER_RUNS:不在新线程中执行任务,而是由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }*/
}
