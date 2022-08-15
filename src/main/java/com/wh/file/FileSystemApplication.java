package com.wh.file;

import cn.hutool.log.StaticLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wh.file.mapper")
@ComponentScan(basePackages={"com.wh"})
public class FileSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileSystemApplication.class, args);
        StaticLog.info("FileSystemApplication启动成功!!!");
    }

//    public static void main(String[] args) {
//        int totalMoney = 2000000;
//        int num = 100000;
//        int sum = 0;
//        for (int i = 0;i < 18; i++) {
//
//            if (i!=0) {
//                totalMoney = totalMoney - num;
//            }
//            sum += totalMoney;
//            System.out.println("扣第"+(i+1)+"个潜能果所需银币:"+totalMoney+";总共花费银币:"+sum+";获取经验值为:"+sum*100);
//        }
//        System.out.println("到110级差多少经验值："+(sum -26162075)*100);
//    }
}
