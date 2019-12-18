package com.ketai.edu.service.impl;

import com.ketai.edu.pojo.CourseDescription;
import com.ketai.edu.mapper.CourseDescriptionMapper;
import com.ketai.edu.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
@Service
@Transactional
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

    @Override
    public void insert(CourseDescription courseDescription) {
        baseMapper.insert(courseDescription);
    }

    @Override
    public void deleteById(String id) {
        baseMapper.deleteById(id);
    }
}
