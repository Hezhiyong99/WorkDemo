package com.ketai.edu.mapper;

import com.ketai.edu.pojo.CourseDescription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程简介 Mapper 接口
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
@Repository
@Mapper
public interface CourseDescriptionMapper extends BaseMapper<CourseDescription> {

}
