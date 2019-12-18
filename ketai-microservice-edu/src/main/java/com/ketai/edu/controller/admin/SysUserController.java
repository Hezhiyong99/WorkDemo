package com.ketai.edu.controller.admin;

import com.bdqn.common.constants.ResultCodeEnum;
import com.bdqn.common.exception.KetaiException;
import com.bdqn.common.vo.R;
import com.ketai.edu.pojo.Sysuser;
import com.ketai.edu.service.SysuserService;
import com.ketai.edu.util.JwtUtil;
import com.ketai.edu.util.SHAUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 科泰研究院
 * @since 2019-12-02
 */
@Api(tags = "系统用户管理")
@RestController
@RequestMapping("/admin/edu/user")
public class SysUserController {
    @Resource
    private SysuserService userService;

    @PostMapping("login")
    @ApiOperation(value = "用户登录")
    public R login(
            @ApiParam(name = "SysUser", value = "系统用户对象", required = true)
            @RequestBody Sysuser SysUser) {
        String pwd = SHAUtils.encodeData(SysUser.getPassword());
        SysUser.setPassword(pwd);
        Sysuser user =
                userService.findUserVoByUsernameAndPassword(SysUser);
        if (user != null) {
            String token = JwtUtil.getToken(SysUser.getUsername());
            return R.ok().data("token", token);
        }
        return R.error().message("帐号密码错误");
    }

    @GetMapping("info")
    @ApiOperation(value = "获取用户信息")
    public R info(
            @ApiParam(name = "token", value = "令牌", required = true)
            @RequestParam String token) throws ServletException {
        Claims claims = JwtUtil.checkToken(token);
        System.out.println(claims);
        String username = claims.getSubject();
        return R.ok()
                .data("roles", claims.get("roles"))
                .data("name", username)
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("logout")
    @ApiOperation(value = "用户登出")
    public R logout(HttpServletRequest request) throws ServletException {
        String authHeader = request.getHeader("X-Token");
        System.out.println("‐‐‐‐" + authHeader);
        if (authHeader == null) {
            throw new KetaiException(ResultCodeEnum.USERUNLOGIN_ERROR);
        }
        //验证token
        Claims claims = JwtUtil.checkToken(authHeader);
        System.out.println(claims);
        claims.remove(claims.getSubject());
        return R.ok();
    }

}
