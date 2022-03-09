package com.sharfine.sms;

import com.sharfine.sms.dp.Email;
import com.sharfine.sms.dp.TimeOut;
import com.sharfine.sms.service.EmailFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class SmsApplicationTests {

    @Autowired
    private EmailFacade emailFacade;
    @Test
    void contextLoads() {
        emailFacade.sendEmailAuthCode(new Email("784751530@qq.com"),
                new TimeOut(1L, TimeUnit.MINUTES),
                null);
    }

}
