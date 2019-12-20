package com.ketai.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.common.vo.R;
import com.ketai.edu.pojo.Course;
import com.ketai.edu.pojo.Teacher;
import com.ketai.edu.service.CourseService;
import com.ketai.edu.service.TeacherService;
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


@Api(tags = "讲师模块")
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    CourseService courseService;
    @Autowired
    TeacherService teacherService;
    @ApiOperation("讲师列表")
    @GetMapping
    public R  findAll(){
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        //查询讲师信息
        Teacher teacher = teacherService.getById(id);

        //根据讲师id查询这个讲师的课程列表
        List<Course> courseList = courseService.selectByTeacherId(id);
        System.out.println(courseList);
        return R.ok().data("teacher", teacher).data("courseList", courseList);
    }

    @ApiParam("分页讲师列表")
    @GetMapping("{page}/{limit}")
    public  R pageList(@ApiParam(name = "page", value = "当前页码", required = true)
                           @PathVariable Long page,

                       @ApiParam(name = "limit", value = "每页记录数", required = true)
                           @PathVariable Long limit){

        //分页条件参数存入
        Page<Teacher> pageteacher = new Page<>(page,limit);

        Map<String, Object> map = teacherService.pageListWeb(pageteacher);
        return R.ok().data(map);
    }
}
