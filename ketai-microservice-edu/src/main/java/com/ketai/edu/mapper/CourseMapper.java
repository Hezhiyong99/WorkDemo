package com.ketai.edu.mapper;

import com.ketai.edu.form.CoursePublishVo;
import com.ketai.edu.pojo.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ketai.edu.vo.CourseWebVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */

@Repository
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    //根据ID发布课程
    CoursePublishVo selectCoursePublishVoById(String id);

    //查询课程和讲师信息
    CourseWebVo selectCourseWebVoById(String courseId);
}
