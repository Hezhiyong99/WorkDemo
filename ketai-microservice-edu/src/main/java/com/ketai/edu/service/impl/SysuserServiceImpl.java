package com.ketai.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ketai.edu.mapper.SysuserMapper;
import com.ketai.edu.pojo.Sysuser;
import com.ketai.edu.service.SysuserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
@Service
public class SysuserServiceImpl extends ServiceImpl<SysuserMapper, Sysuser> implements SysuserService {

    @Override
    public Sysuser findUserVoByUsernameAndPassword(Sysuser user) {
        QueryWrapper<Sysuser> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("username", user.getUsername())
                .eq("password", user.getPassword());
        Sysuser SysUser = (Sysuser) baseMapper.selectOne(queryWrapper);
        if (SysUser != null) {
            return SysUser;
        }
        return null;
    }
}
