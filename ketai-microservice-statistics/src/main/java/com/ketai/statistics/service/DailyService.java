package com.ketai.statistics.service;

import com.ketai.statistics.pojo.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-10
 */
public interface DailyService extends IService<Daily> {

    //更新当前信息记录
    void createStatisticsByDay(String day);

    //图表显示信息记录
    Map<String, Object> getChartData(String begin, String end, String type);
}
