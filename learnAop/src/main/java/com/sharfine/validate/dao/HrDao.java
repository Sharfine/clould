package com.sharfine.validate.dao;

import com.sharfine.validate.model.Hr;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author: Sharfine
 * @createTime: 2020/7/31 18:15
 */
@Component
public interface HrDao {

    @Select("select * from hr where id = 3")
    Hr get();

}
