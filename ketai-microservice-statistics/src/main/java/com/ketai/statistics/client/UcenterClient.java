package com.ketai.statistics.client;

import com.bdqn.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用的客户端
 */

@FeignClient("KETAI-UCENTER")
public interface UcenterClient {

    @GetMapping(value = "/admin/ucenter/member/count‐register/{day}")
    public R registerCount(@PathVariable("day") String day);
}
