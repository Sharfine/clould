package com.sharfine.validate.utils;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Sharfine
 * @createTime: 2020/8/6 10:20
 */
public class PagingUtil {
    public PagingUtil() {
    }

    public static <T, E> PagingInfo<T> jpaPageConvert(Page<E> page, Function<E, T> refactor) {
        List<T> ret = (List) page.getContent().stream().map(refactor).collect(Collectors.toList());
        return new PagingInfo(ret, page.getNumber() + 1, page.getSize(), page.getTotalElements());
    }

    public static <T, E> PagingInfo<T> mongoPageQuery(MongoTemplate mongoTemplate, Query query, Class<E> entityClass, Function<E, T> refactor, int pageNum, int pageSize) {
        return mongoPageQuery(mongoTemplate, query, entityClass, refactor, pageNum, pageSize, (String) null);
    }

    public static <T, E> PagingInfo<T> mongoPageQuery(MongoTemplate mongoTemplate, Query query, Class<E> entityClass, Function<E, T> refactor, int pageNum, int pageSize, String lastId) {
        long total = mongoTemplate.count(query, entityClass);
        int totalPageNum = (int) Math.ceil((double) total / (double) pageSize);
        if (pageNum > 0 && pageNum <= totalPageNum) {
            Criteria criteria = new Criteria();
            if (!StringUtils.isEmpty(lastId)) {
                if (pageNum != 1) {
                    criteria.and("_id").gt(new ObjectId(lastId));
                    query.addCriteria(criteria);
                }

                query.limit(pageSize);
            } else {
                int skip = pageSize * (pageNum - 1);
                query.skip((long) skip).limit(pageSize);
            }

            List<T> entityList = (List) mongoTemplate.find(query, entityClass).stream().map(refactor).collect(Collectors.toList());
            return new PagingInfo(entityList, pageNum, pageSize, total);
        } else {
            return new PagingInfo(new ArrayList(), pageNum, pageSize, total);
        }
    }

    /*public static <T, K> PagingInfo<T> redisPageQuery(ZSetOperations<K, T> zSetOps, K key, int pageNum, int pageSize) {
        return redisPageQuery(zSetOps, key, (x) -> {
            return x;
        }, pageNum, pageSize);
    }

    public static <T, K, R> PagingInfo<R> redisPageQuery(ZSetOperations<K, T> zSetOps, K key, Function<T, R> refactor, int pageNum, int pageSize) {
        Long total = zSetOps.size(key);
        Set<T> set = zSetOps.range(key, (long)((pageNum - 1) * pageSize), (long)((pageNum - 1 + 1) * pageSize - 1));
        if (set == null || total == null) {
            total = 0L;
            set = new HashSet();
        }

        List<R> entityList = (List)((Set)set).stream().map(refactor).collect(Collectors.toList());
        return new PagingInfo(entityList, pageNum, pageSize, total);
    }

    public static <T, K> PagingInfo<T> redisPageQuery(ListOperations<K, T> zListOps, K key, int pageNum, int pageSize) {
        return redisPageQuery(zListOps, key, (x) -> {
            return x;
        }, pageNum, pageSize);
    }

    public static <T, K, R> PagingInfo<R> redisPageQuery(ListOperations<K, T> zListOps, K key, Function<T, R> refactor, int pageNum, int pageSize) {
        Long total = zListOps.size(key);
        List<T> entityList = zListOps.range(key, (long)((pageNum - 1) * pageSize), (long)((pageNum - 1 + 1) * pageSize - 1));
        if (entityList == null || total == null) {
            total = 0L;
            entityList = new ArrayList();
        }

        List<R> ret = (List)((List)entityList).stream().map(refactor).collect(Collectors.toList());
        return new PagingInfo(ret, pageNum, pageSize, total);
    }*/

    public static <T> PagingInfo<T> listPageQuery(List<T> list, int pageNum, int pageSize) {
        int total = list.size();
        int start = (pageNum - 1) * pageSize;
        if (start > total) {
            return new PagingInfo(new ArrayList(), pageNum, pageSize, (long) total);
        } else {
            int end = Math.min((pageNum - 1 + 1) * pageSize, total);
            List<T> entityList = list.subList(start, end);
            return new PagingInfo(entityList, pageNum, pageSize, (long) total);
        }
    }
}


