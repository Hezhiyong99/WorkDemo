package com.ketai.edu.mapper;

import com.ketai.edu.pojo.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
@Repository
@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {
    //一级分类
    List<Subject> getOne();

    //二级分类
    List<Subject> getTwo(@Param("parent_id") String parent_id);
}
