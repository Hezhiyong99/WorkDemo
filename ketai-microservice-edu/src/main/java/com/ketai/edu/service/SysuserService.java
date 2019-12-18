package com.ketai.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ketai.edu.pojo.Sysuser;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
public interface SysuserService extends IService<Sysuser> {
    Sysuser findUserVoByUsernameAndPassword(Sysuser user);
}
