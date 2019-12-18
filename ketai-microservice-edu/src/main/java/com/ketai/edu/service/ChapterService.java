package com.ketai.edu.service;

import com.ketai.edu.form.CourseInfoForm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ketai.edu.pojo.Chapter;
import com.ketai.edu.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程章节 服务类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
public interface ChapterService extends IService<Chapter> {
    //根据删除对应章节
    void removeChapterById(String id);

    //嵌套章节数据列表
    List<ChapterVo> nestedList(String courseId);
}
