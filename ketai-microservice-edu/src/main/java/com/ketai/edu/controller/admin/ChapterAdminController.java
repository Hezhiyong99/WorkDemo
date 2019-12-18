package com.ketai.edu.controller.admin;


import com.bdqn.common.vo.R;
import com.ketai.edu.pojo.Chapter;
import com.ketai.edu.service.ChapterService;
import com.ketai.edu.vo.ChapterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="课程章节管理")
@RestController
@RequestMapping("/admin/edu/chapter")
public class ChapterAdminController {
    @Autowired
    private ChapterService chapterService;

    @ApiOperation(value = "新增章节")
    @PostMapping
    public R save(@ApiParam(name = "chapter", value = "章节对象", required = true) @RequestBody Chapter chapter){
        chapterService.save(chapter);
        return R.ok();
    }

    @ApiOperation("根据ID查询对应章节")
    @GetMapping("{id}")
    public R getByid(@ApiParam(name = "id", value = "章节ID", required = true) @PathVariable("id")String id){
        Chapter chap = chapterService.getById(id);
        return R.ok().data("item",chap);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PutMapping("{id}")
    public R updateById(@ApiParam(name = "id", value = "章节ID", required = true) @PathVariable String id,
                        @ApiParam(name = "chapter", value = "章节对象", required = true) @RequestBody Chapter chapter){
        chapter.setId(id);
        chapterService.updateById(chapter);
        return R.ok();
    }

    @ApiOperation(value = "根据id删除对应章节")
    @DeleteMapping("{id}")
    public R removeById(@PathVariable("id") String id){
        chapterService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("nested-list/{courseId}")
    public R nestedListByCourseId(@ApiParam(name = "courseId", value = "课程ID", required = true) @PathVariable String courseId){
        System.out.println(courseId+"=========");
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return R.ok().data("items", chapterVoList);
    }
}
