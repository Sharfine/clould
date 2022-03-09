package com.sharfine.juc.每人一百块;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: Sharfine
 * @createTime: 2022/3/1 22:05
 */
@Data
@AllArgsConstructor
public class Money {

    private BigDecimal decimal;
}
