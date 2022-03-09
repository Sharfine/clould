package com.sharfine.sms.service.impl;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.sharfine.sms.constant.CommonConstant;
import com.sharfine.sms.dp.Phone;
import com.sharfine.sms.dp.TimeOut;
import com.sharfine.sms.service.SmsFacade;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * @author: Sharfine
 * @createTime: 2022/1/4 21:22
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "aliyun.sms.enable", havingValue = "true")
public class AliyunSmsFacadeImpl extends AuthCodeProvider implements SmsFacade {

    @Value("${aliyun.sms.regionId:cn-hangzhou}")
    private String aliyunRegionId;


    @Value("${aliyun.sms.signName:}")
    private String signName;

    @Value("${aliyun.sms.templateCode:}")
    private String templateCode;

    @Value("${aliyun.sms.signNameInternational:}")
    private String signNameInternational;

    @Value("${aliyun.sms.templateCodeInternational:}")
    private String templateCodeInternational;

    @Autowired
    private IAcsClient iAcsClient;

    @Override
    public void sendPhoneAuthCode(Phone phone, TimeOut timeOut, Supplier<String> strategy) {

        String areaCode = phone.getPhoneAreaCode();

        if (StrUtil.isEmpty(areaCode) || areaCode.equals(CommonConstant.CHINA_AREA_CODE)) {
            sendMessageByArea(phone, signName, templateCode, timeOut, strategy);

        } else {
            sendMessageByArea(phone, signNameInternational, templateCodeInternational, timeOut, strategy);
        }
    }

    public void sendMessageByArea(Phone phone, String signName, String templateCode, TimeOut timeOut, Supplier<String> strategy) {

        String authCode = generateAuthCode(phone.getCacheKey(), timeOut, strategy);

        CommonRequest request = new CommonRequest();
        request.setAction("SendSms");
        request.setVersion("2017-05-25");
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.putQueryParameter("PhoneNumbers", phone.fullNumber());
        //
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("RegionId", aliyunRegionId);
        //
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + authCode + "\"}");
        try {
            CommonResponse response = iAcsClient.getCommonResponse(request);
            val json = JSONObject.parseObject(response.getData());
            String successCode = "OK";
            String code = json.getString("Code");
            String msg = json.getString("Message");
            if (!successCode.equals(code)) {
                throw new RuntimeException("error.commom.sms.wrong_send");
            }
        } catch (ClientException e) {
            log.error("SendAuthCode error: ", e);
            throw new RuntimeException("error.commom.sms.wrong_send");
        }
    }

}
