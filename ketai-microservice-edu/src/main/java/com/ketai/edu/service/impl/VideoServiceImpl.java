package com.ketai.edu.service.impl;

import com.ketai.edu.form.VideoInfoForm;
import com.ketai.edu.pojo.Video;
import com.ketai.edu.mapper.VideoMapper;
import com.ketai.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public void saveVideoInfo(VideoInfoForm videoInfoForm) {
        Video video = new Video();
        //参数1是要被copy的对象  参数二是要被赋值的对象  对象之间的属性赋值
        BeanUtils.copyProperties(videoInfoForm, video);
        this.save(video);
    }

    @Override
    public VideoInfoForm getVideoInfoFormById(String id) {
        Video video = this.getById(id);
        //创建VideoInfoForm 对象
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(video,videoInfoForm);
        return videoInfoForm;
    }

    @Override
    public void updateVideoInfoById(VideoInfoForm videoInfoForm) {
        //保存课时基本信息
        Video video = new Video();
        BeanUtils.copyProperties(videoInfoForm, video);
        this.updateById(video);
    }

    @Override
    public void removeVideoById(String id) {
        baseMapper.deleteById(id);
    }
}
