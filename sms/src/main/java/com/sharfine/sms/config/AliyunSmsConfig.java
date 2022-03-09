package com.sharfine.sms.config;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author abm
 **/
@Configuration
@ConditionalOnProperty(name = "aliyun.sms.enable", havingValue = "true")
public class AliyunSmsConfig {
    @Value("${aliyun.sms.regionId:cn-hangzhou}")
    private String regionId;

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;

    @Bean
    public IAcsClient iAcsClient() {

        DefaultProfile profile = DefaultProfile.getProfile(
                regionId,
                accessKeyId,
                accessKeySecret
        );

        return new DefaultAcsClient(profile);
    }
}
