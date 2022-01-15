package com.zhd.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhd.pojo.Journey;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 行程记录表 Mapper 接口
 * </p>
 *
 * @author zyg
 * @since 2018-02-05
 */
public interface JourneyMapper extends BaseMapper<Journey> {

    @Select("select <include refid='Base_Column_List'> from journey where user_id = #{0} order by start_time desc")
    List<Journey> selectByUser(String userId);

}
