package com.sharfine.common.feign;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: Sharfine
 * @createTime: 2021/9/1 16:11
 */
@FeignClient(
        name = "cloud-account",
        fallbackFactory = AccountClient.ClientFallbackFactory.class
)
public interface AccountClient {

    @RequestMapping(path = "/account/save", method = RequestMethod.POST)
    String save();


    @Slf4j
    @Component
    class ClientFallbackFactory implements FallbackFactory<AccountClient> {

        @Override
        public AccountClient create(Throwable throwable) {
            return new AccountClient() {
                @Override
                public String save() {
                    return throwable.toString();
                }
            };
        }
    }

}
