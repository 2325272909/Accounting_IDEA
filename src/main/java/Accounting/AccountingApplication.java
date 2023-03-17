package Accounting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName:AccountingApplication
 * @Description:记账APP项目后端程序启动类
 * @Author:liumengying
 * @Date: 2023/3/5 9:56
 * Version v1.0
 */
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class AccountingApplication {
    public static void main(String[] args){
        SpringApplication.run(AccountingApplication.class,args);
        log.info("todo项目启动成功...");
    }
}
