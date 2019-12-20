package com.ketai.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketai.edu.pojo.Teacher;
import com.ketai.edu.query.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
public interface TeacherService extends IService<Teacher> {
    // 条件查询
    void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery);

    //Web页面条件查询
   Map<String,Object> pageListWeb(Page<Teacher> pageParam);

}
