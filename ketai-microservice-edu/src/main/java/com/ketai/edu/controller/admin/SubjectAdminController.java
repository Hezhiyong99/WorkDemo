package com.ketai.edu.controller.admin;

import com.bdqn.common.constants.ResultCodeEnum;
import com.bdqn.common.exception.KetaiException;
import com.bdqn.common.vo.R;
import com.ketai.edu.pojo.Course;
import com.ketai.edu.pojo.Subject;
import com.ketai.edu.service.CourseService;
import com.ketai.edu.service.SubjectService;
import com.ketai.edu.vo.SubjectNestedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
@Api(tags = "课程分类管理")
@RestController
@RequestMapping("/admin/edu/subject")
public class SubjectAdminController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private  CourseService CourseService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("import")
    public R batchImport(@ApiParam(name = "file", value = "Excel文件", required = true)
                         @RequestParam("file") MultipartFile file) {
        try {
            List<String> errorMsg = subjectService.batchImport(file);
            if (errorMsg.size() == 0) {
                return R.ok().message("批量导入成功");
            } else {
                return R.error()
                        .message("部分数据导入失败")
                        .data("errorMsgList", errorMsg);
            }
        } catch (Exception e) {
            //无论哪种异常，只要是在excel导入时发生的，一律用转成KetaiException抛出
            throw new KetaiException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("")
    public R nestedList() {
        List<SubjectNestedVo> subjectNestedVoList =
                subjectService.nestedList();
        return R.ok().data("items", subjectNestedVoList);
    }

    @ApiOperation("一级分类")
    @GetMapping("getOne")
    public R getOne(){
        List<Subject> one = subjectService.getOne();
        return R.ok().data("items",one);
    }

    @ApiOperation("二级分类")
    @GetMapping("getTwo/{parent_id}")
    public R getTwo(@ApiParam(name = "parent_id", value = "一级id", required = true)
                    @PathVariable("parent_id")String parent_id){
        List<Subject> two = subjectService.getTwo(parent_id);
        return R.ok().data("item",two);
    }
    @ApiOperation("ID查询课程")
    @GetMapping("{id}")
    public R getByid(@PathVariable("id") String id){
        Course subject = CourseService.getById(id);
        return R.ok().data("items",subject);
    }

    @ApiOperation(value = "根据ID修改课程信息")
    @PutMapping("{id}")
    @CrossOrigin
    public R updateById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "Course", value = "课程对象", required = true)
            @RequestBody Course course){
        course.setId(id);
        System.out.println("====>"+id);
        CourseService.updateById(course);
        return R.ok();
    }
}
