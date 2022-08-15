package com.wh.file.utils.threadpool;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
@Slf4j
public class GenerateThreadExecutor {

    @Getter
    private ExecutorService taskExecutor;
    private int corePoolSize = 50 ;
    public GenerateThreadExecutor() {
        taskExecutor = Executors.newFixedThreadPool(corePoolSize);
    }
    public <T> Future<T> submit(Callable<T> task) {
        return taskExecutor.submit(task);
    }

}
