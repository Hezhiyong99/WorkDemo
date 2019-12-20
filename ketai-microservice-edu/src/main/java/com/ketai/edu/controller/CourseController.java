package com.ketai.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.common.vo.R;
import com.ketai.edu.pojo.Course;
import com.ketai.edu.service.ChapterService;
import com.ketai.edu.service.CourseService;
import com.ketai.edu.vo.ChapterVo;
import com.ketai.edu.vo.CourseWebVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "课程模块")
@RestController
@RequestMapping("/edu/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    ChapterService chapterService;

    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public R geiList(@ApiParam(name = "page", value = "当前页码", required = true)
                         @PathVariable Long page,

                     @ApiParam(name = "limit", value = "每页记录数", required = true)
                         @PathVariable Long limit){
        Page<Course> pageCourse = new Page<>(page,limit);
        Map<String, Object> map = courseService.pageListWeb(pageCourse);
        return R.ok().data(map);
    }

    @ApiOperation(value = "根据id查询课程")
    @GetMapping(value = "{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseService.selectCourseWebVoById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);

        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }
}
