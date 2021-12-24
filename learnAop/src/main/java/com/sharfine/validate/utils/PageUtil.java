package com.sharfine.validate.utils;

import com.sharfine.validate.model.EthBlock;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Sharfine
 * @createTime: 2020/8/12 15:04
 */
public class PageUtil {

    public static <E, T> PagingInfo<T> mongoQuery(MongoTemplate mongoTemplate, Query query, Class<E> entityClass, Function<E, T> function, int pageNum, int pageSize) {

        long count = mongoTemplate.count(new Query(), EthBlock.class);

        int totalPageNum = (int) Math.ceil((float) count / (float) pageSize);

        if (pageNum > 0 && pageNum <= totalPageNum) {

            query.skip(pageNum * (pageSize - 1)).limit(pageSize);

        } else {
            query.limit(pageSize);
        }

        List<T> entityList = mongoTemplate.find(query, entityClass).stream().map(function).collect(Collectors.toList());

        return new PagingInfo<>(entityList, pageNum, pageSize, count);


    }
}