package com.ketai.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ketai.ucenter.pojo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-10
 */
@Repository
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    //使用QueryWrapper查询出的用户是非逻辑删除的用户，这里我们想统计所有的用户
    Integer selectRegisterCount(@Param("day") String day);

}
