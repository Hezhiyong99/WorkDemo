package com.ketai.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdqn.common.util.ExcelImportUtil;
import com.ketai.edu.mapper.SubjectMapper;
import com.ketai.edu.pojo.Subject;
import com.ketai.edu.service.SubjectService;
import com.ketai.edu.vo.SubjectNestedVo;
import com.ketai.edu.vo.SubjectVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    SubjectMapper SubjectMapper;
    /**
     * ：获取Excel记录并逐条导入
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Transactional //添加事务处理
    @Override
    public List<String> batchImport(MultipartFile file) throws Exception {
        //错误消息列表
        List<String> errorMsg = new ArrayList<>();
        //创建工具类对象
        ExcelImportUtil excelHSSFUtil = new ExcelImportUtil(file.getInputStream());
        //获取工作表
        Sheet sheet = excelHSSFUtil.getSheet();
        int rowCount = sheet.getPhysicalNumberOfRows(); //获取工作表行数
        //如果行数没有输出错误消息
        if (rowCount <= 1) {
            errorMsg.add("请填写数据");
            return errorMsg;
        }
        for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
            Row rowData = sheet.getRow(rowNum);
            if (rowData != null) { //行不为空
                //获取一级分类
                String levelOneValue = "";
                Cell levelOneCell = rowData.getCell(0);
                if (levelOneCell != null) {
                    levelOneValue =
                            excelHSSFUtil.getCellValue(levelOneCell).trim();
                    if (StringUtils.isEmpty(levelOneValue)) {
                        errorMsg.add("第" + rowNum + "行一级分类为空");
                        continue;
                    }
                }
                //判断一级分类是否重复 TODO
                Subject subject = getByTitle(levelOneValue);
                String parentId = null;
                if (subject == null) {
                    //将一级分类存入数据库
                    Subject subjectLevelOne = new Subject();
                    subjectLevelOne.setTitle(levelOneValue);
                    subjectLevelOne.setSort(rowNum);
                    baseMapper.insert(subjectLevelOne);//添加
                    parentId = subjectLevelOne.getId();
                } else {
                    parentId = subject.getId();
                }
                //获取二级分类 TODO
                String levelTwoVaule = "";
                Cell levelTwoCell = rowData.getCell(1);
                if (levelTwoCell != null) {
                    levelTwoVaule = excelHSSFUtil.getCellValue(levelTwoCell).trim();
                    if (StringUtils.isEmpty(levelTwoVaule)) {
                        errorMsg.add("第" + rowNum + "行二级分类为空");
                        continue;
                    }
                }
                //判断二级分类是否重复
                Subject subjectSub = getSubByTitle(levelTwoVaule, parentId);
                Subject subjectLevelTwo = null;
                if (subjectSub == null) {
                    //将二级分类存入数据库
                    subjectLevelTwo = new Subject();
                    subjectLevelTwo.setTitle(levelTwoVaule);
                    subjectLevelTwo.setParentId(parentId);
                    subjectLevelTwo.setSort(rowNum);
                    baseMapper.insert(subjectLevelTwo);//添加
                }
            }
        }
        return errorMsg;
    }

    @Override
    public List<SubjectNestedVo> nestedList() {
        //最终要的到的数据列表
        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();
        //获取一级分类数据记录
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<Subject> subjects = baseMapper.selectList(queryWrapper);
        //填充一级分类vo数据
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            Subject subject = subjects.get(i);
            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            //对象复制
            BeanUtils.copyProperties(subject, subjectNestedVo);
            subjectNestedVoArrayList.add(subjectNestedVo);
            //填充二级分类vo数据 TODO
            QueryWrapper<Subject> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("parent_id", subject.getId());
            queryWrapper2.orderByAsc("sort", "id");
            List<Subject> subsubjects =
                    baseMapper.selectList(queryWrapper2);
            int count2 = subsubjects.size();
            if (count2 > 0) {
                for (int j = 0; j < count2; j++) {
                    Subject subsubject = subsubjects.get(j);
                    SubjectVo subjectVo = new SubjectVo();
                    // 对象复制
                    BeanUtils.copyProperties(subsubject, subjectVo);
                    //填充二级分类vo数据 TODO
                    subjectNestedVo.getChildren().add(subjectVo);
                }
            }
        }
        return subjectNestedVoArrayList;
    }


    /**
     * 根据分类名称和父id查询这个二级分类中否存在
     *
     * @param title
     * @return
     */
    private Subject getSubByTitle(String title, String parentId) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", parentId);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据分类名称查询这个一级分类中否存在
     *
     * @param title
     * @return
     */
    private Subject getByTitle(String title) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", "0");//一级分类
        return baseMapper.selectOne(queryWrapper);
    }
    @Override
    public List<Subject> getOne() {
        return SubjectMapper.getOne();
    }

    @Override
    public List<Subject> getTwo(String parent_id) {
        return SubjectMapper.getTwo(parent_id);
    }
}
