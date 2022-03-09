package com.sharfine.sms.service.impl;


import cn.hutool.core.util.StrUtil;
import com.sharfine.sms.constant.CommonConstant;
import com.sharfine.sms.dp.Phone;
import com.sharfine.sms.dp.TimeOut;
import com.sharfine.sms.service.SmsFacade;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import lombok.extern.slf4j.Slf4j;
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
@ConditionalOnProperty(name = "tencent.sms.enable", havingValue = "true")
public class TencentSmsFacadeImpl extends AuthCodeProvider implements SmsFacade {
    @Autowired
    private SmsClient smsClient;

    @Value("${tencent.sms.appid}")
    private String tencentAppid;

    @Value("${tencent.sms.signName}")
    private String signName;

    @Value("${tencent.sms.templateCode}")
    private String templateCode;

    @Value("${tencent.sms.signNameInternational:}")
    private String signNameInternational;

    @Value("${tencent.sms.templateCodeInternational:}")
    private String templateCodeInternational;

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

        SendSmsRequest req = new SendSmsRequest();
        /* 短信应用 ID: 在 [短信控制台] 添加应用后生成的实际 SDKAppID，例如1400006666 */
        req.setSmsSdkAppid(tencentAppid);
        /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，可登录 [短信控制台] 查看签名信息 */
        req.setSign(signName);
        /* 国际/港澳台短信 senderid: 国内短信填空，默认未开通，如需开通请联系 [sms helper] */
        req.setSenderId("");
        /* 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
        String session = "";
        req.setSessionContext(session);
        /* 模板 ID: 必须填写已审核通过的模板 ID，可登录 [短信控制台] 查看模板 ID */
        req.setTemplateID(templateCode);
        /* 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
         * 例如+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号*/
        String[] phoneNumbers = {phone.fullNumber()};
        req.setPhoneNumberSet(phoneNumbers);
        /* 模板参数: 若无模板参数，则设置为空*/
        String[] templateParams = {authCode};
        req.setTemplateParamSet(templateParams);

        try {
            SendSmsResponse res = smsClient.SendSms(req);
            SendStatus[] sendStatusSet = res.getSendStatusSet();
            SendStatus status = sendStatusSet[0];
            String code = status.getCode();
            String successCode = "Ok";
            String msg = status.getMessage();
            if (!successCode.equals(code)) {
                throw new RuntimeException("error.commom.sms.wrong_send");
            }
        } catch (TencentCloudSDKException e) {
            log.error("SendAuthCode error: ", e);
            throw new RuntimeException("error.commom.sms.wrong_send");
        }
    }


}
