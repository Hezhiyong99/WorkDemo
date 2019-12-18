package com.ketai.edu.service;

import com.ketai.edu.pojo.CourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
public interface CourseDescriptionService extends IService<CourseDescription> {

           void insert(CourseDescription courseDescription);

           void deleteById(String id);
}
