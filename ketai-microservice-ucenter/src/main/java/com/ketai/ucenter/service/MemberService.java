package com.ketai.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ketai.ucenter.pojo.Member;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-10
 */
public interface MemberService extends IService<Member> {

    Integer countRegisterByDay(String day);

}
