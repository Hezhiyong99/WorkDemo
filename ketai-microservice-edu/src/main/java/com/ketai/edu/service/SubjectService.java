package com.ketai.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ketai.edu.pojo.Subject;
import com.ketai.edu.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
public interface SubjectService extends IService<Subject> {
    //：获取Excel记录并逐条导入
    List<String> batchImport(MultipartFile file) throws Exception;

    List<SubjectNestedVo> nestedList();

    //一级分类
    List<Subject> getOne();

    //二级分类
    List<Subject> getTwo(String parent_id);
}
