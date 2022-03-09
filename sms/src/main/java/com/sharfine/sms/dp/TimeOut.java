package com.sharfine.sms.dp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author: Sharfine
 * @createTime: 2022/1/5 15:09
 */
@Data
@AllArgsConstructor
public class TimeOut {

    private Long duration;

    private TimeUnit timeUnit;
}
