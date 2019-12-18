package com.ketai.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketai.edu.form.CourseInfoForm;
import com.ketai.edu.form.CoursePublishVo;
import com.ketai.edu.pojo.Course;
import com.ketai.edu.query.CourseQuery;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
public interface CourseService extends IService<Course> {
    // 条件查询
    void pageQuery(Page<Course> pageParam, CourseQuery courseQuery);

    /**
     * 保存课程和课程详情信息
     * @param courseInfoForm
     * @return 新生成的课程id
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    //课程删除
    void removeCourseById(String id);


    //根据id查询课程
    CourseInfoForm getCourseInfoFormById(String id);

    //更新课程
    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    //根据id发布课程
    CoursePublishVo getCoursePublishVoById(String id);

    void publishCourseById(String id);
}
