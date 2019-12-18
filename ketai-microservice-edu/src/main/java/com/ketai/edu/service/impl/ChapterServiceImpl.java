package com.ketai.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ketai.edu.mapper.VideoMapper;
import com.ketai.edu.pojo.Chapter;
import com.ketai.edu.mapper.ChapterMapper;
import com.ketai.edu.pojo.Video;
import com.ketai.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ketai.edu.vo.ChapterVo;
import com.ketai.edu.vo.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程章节 服务实现类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
@Service
@Transactional
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    VideoMapper videoMapper;

    @Override
    public void removeChapterById(String id) {
        //根据章节id 删除对应所有视频
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",id);
        videoMapper.delete(queryWrapper);

        //根据章节id删除章节
        baseMapper.deleteById(id);
    }

    @Override
    public List<ChapterVo> nestedList(String courseId) {
        //最终要的到的数据列表
        ArrayList<ChapterVo> chapterVoArrayList = new ArrayList<>();
        //获取章节信息
        QueryWrapper<Chapter> queryWrapperChapter = new QueryWrapper<>();
        queryWrapperChapter.eq("course_id", courseId);
        queryWrapperChapter.orderByAsc("sort", "id");
        List<Chapter> chapters = baseMapper.selectList(queryWrapperChapter);
        //获取课时信息
        QueryWrapper<Video> queryWrapperVideo = new QueryWrapper<>();
        queryWrapperVideo.eq("course_id", courseId);
        queryWrapperVideo.orderByAsc("sort", "id");
        List<Video> videos = videoMapper.selectList(queryWrapperVideo);
        //填充章节vo数据
        for (int i = 0; i < chapters.size(); i++) {
            Chapter chapter = chapters.get(i);
            //创建章节vo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoArrayList.add(chapterVo);
            //填充视频vo数据
            ArrayList<VideoVo> videoVoArrayList = new ArrayList<>();
            for (int j = 0; j < videos.size(); j++) {
                Video video = videos.get(j);
                if(chapter.getId().equals(video.getChapterId())){
                    //创建视频vo对象
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoArrayList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoArrayList);
        }
        return chapterVoArrayList;
    }
}
