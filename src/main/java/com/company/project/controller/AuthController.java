package com.company.project.controller;

import com.company.project.core.ReponseEntry;
import com.company.project.entry.model.User;
import com.company.project.entry.vo.Result;
import com.company.project.service.UserService;
import com.company.project.utils.CookieUtils;
import com.company.project.utils.JwtUtils;
import com.company.project.utils.MD5Utils;
import com.company.project.utils.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final String COOKIENAME = "token";
    private static final Integer COOkIEMAXAGE = 60 * 30; //30分钟过期

    @Resource
    private UserService userService;

    @PostMapping("/checkLogin")
    public Result checkLogin(@RequestParam(name = "name") String name, @RequestParam(name = "password") String password, HttpServletRequest request,
                             HttpServletResponse response) {

        try {

            User user = userService.queryUser(name, MD5Utils.md5PlusSalt(password));
            // 如果查询结果为null，则直接返回null
            if (user == null) {
                return ReponseEntry.genFailResult("用户名密码错误！");
            }
            // 如果有查询结果，则生成token
            UserInfo userInfo = new UserInfo(user.getId(), user.getName());
            String token = JwtUtils.geneJsonWebToken(userInfo);
            if (StringUtils.isBlank(token)) {
                //token生成失败
                return ReponseEntry.genFailResult("登录校验失败");
            }
            // 将token写入cookie,并指定httpOnly为true，防止通过JS获取和修改
            CookieUtils.setCookie(request, response, COOKIENAME,
                    token, COOkIEMAXAGE, true);
            return ReponseEntry.genSuccessResult("登录校验成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReponseEntry.genFailResult("登录校验失败");
    }
}
