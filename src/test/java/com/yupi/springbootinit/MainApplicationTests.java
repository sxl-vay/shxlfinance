package com.yupi.springbootinit;

import com.yupi.springbootinit.config.WxOpenConfig;
import javax.annotation.Resource;
import javax.mail.MessagingException;

import com.yupi.springbootinit.mail.MailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 主类测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
class MainApplicationTests {

    @Resource
    private WxOpenConfig wxOpenConfig;

    @Resource
    private MailUtil service;

    @Test
    void contextLoads() {
        service.sendSimpleMail("1693847306@qq.com","shxl","test");


    }

}
