package com.wh.file.utils.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author ylw
 * @Description
 * @date 2021/11/23
 */
@Configuration
public class ThreadConfig {
    @Bean(value = "slowExecutor")
    public ThreadPoolTaskExecutor slowExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(30);
        executor.initialize();
        return executor;
    }
}
