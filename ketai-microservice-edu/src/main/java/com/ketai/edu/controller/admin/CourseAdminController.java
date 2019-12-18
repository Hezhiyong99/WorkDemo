package com.ketai.edu.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bdqn.common.constants.ResultCodeEnum;
import com.bdqn.common.exception.KetaiException;
import com.bdqn.common.vo.R;
import com.ketai.edu.form.CourseInfoForm;
import com.ketai.edu.form.CoursePublishVo;
import com.ketai.edu.pojo.Course;
import com.ketai.edu.query.CourseQuery;
import com.ketai.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description="课程管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/edu/course")
public class CourseAdminController {

    @Autowired
    private CourseService courseService;
    @ApiOperation(value = "新增课程")
    @PostMapping("save-course-info")
    public R saveCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required=false) @RequestBody CourseInfoForm courseInfoForm){
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("courseId", courseId);
    }

    @ApiOperation(value = "课程管理列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery) {
        if (page <= 0 || limit <= 0) {
            throw new KetaiException(ResultCodeEnum.PARAM_ERROR);
        }
        Page<Course> pageParam = new Page<>(page, limit);
        courseService.pageQuery(pageParam, courseQuery);
        List<Course> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("course-info/{id}")
    public R getByIds(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        CourseInfoForm courseInfoFormById = courseService.getCourseInfoFormById(id);
        return R.ok().data("item",courseInfoFormById);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("update-course-info/{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm,
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {

        courseService.updateCourseInfoById(courseInfoForm);
        return R.ok();
    }
    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("course-publish-info/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        CoursePublishVo courseInfoForm = courseService.getCoursePublishVoById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publish-course/{id}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        courseService.publishCourseById(id);
        return R.ok();
    }
}
