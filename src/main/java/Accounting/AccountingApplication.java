package Accounting;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
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
@ServletComponentScan  //开启针对Servlet容器相关组件（特别是过滤器）的扫描，不开启则不起作用
@EnableTransactionManagement  //开启事务管理
public class AccountingApplication {
    public static void main(String[] args){
        SpringApplication.run(AccountingApplication.class,args);
        log.info("Accounting项目启动成功...");
    }
}
