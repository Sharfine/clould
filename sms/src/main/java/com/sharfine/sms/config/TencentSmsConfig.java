package com.sharfine.sms.config;


import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Sharfine
 * @createTime: 2020/10/20 17:24
 */
@Configuration
@Slf4j
@ConditionalOnProperty(name = "tencent.sms.enable", havingValue = "true")
public class TencentSmsConfig {

    @Value("${tencent.sms.regionId:}")
    private String regionId;

    @Value("${tencent.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${tencent.sms.accessKeySecret}")
    private String accessKeySecret;

    @Bean
    public SmsClient smsClient() {

        Credential cred = new Credential(accessKeyId, accessKeySecret);

        return new SmsClient(cred, regionId);
    }
}
