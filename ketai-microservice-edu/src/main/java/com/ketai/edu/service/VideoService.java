package com.ketai.edu.service;

import com.ketai.edu.form.VideoInfoForm;
import com.ketai.edu.pojo.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author
 * @since 2019-12-02
 */
public interface VideoService extends IService<Video> {

    //新增课时
    void saveVideoInfo(VideoInfoForm videoInfoForm);

    //根据id查询课时
    VideoInfoForm getVideoInfoFormById(String id);

    //修改课时
    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    //删除课时
    void removeVideoById(String id);
}
