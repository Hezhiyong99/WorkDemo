package com.ketai.ucenter.service.impl;

import com.ketai.ucenter.mapper.MemberMapper;
import com.ketai.ucenter.pojo.Member;
import com.ketai.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-10
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    MemberMapper MemberMapper;

    @Override
    public Integer countRegisterByDay(String day) {
        return MemberMapper.selectRegisterCount(day);
    }
}
