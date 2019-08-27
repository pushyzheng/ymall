package site.pushy.ymall.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author pushy zheng
 * @since 2019/8/7
 */
@SpringBootApplication(scanBasePackages = "site.pushy.ymall")
@EnableScheduling
@MapperScan("site.pushy.ymall.dao")
public class SeckillApplication implements CommandLineRunner  {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SeckillApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        Thread.currentThread().join();
    }
}
