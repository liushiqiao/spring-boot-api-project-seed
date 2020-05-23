package com.company.project.interceoter;

import com.company.project.utils.CookieUtils;
import com.company.project.utils.JwtUtils;
import com.company.project.core.ReponseEntry;
import com.company.project.utils.UserInfo;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginIntercepter implements HandlerInterceptor {

    private static final String COOKIENAME = "token";
    private static final Integer COOkIEMAXAGE = 60 * 30; //30分钟过期
    private static final Gson gson = new Gson();

    /**
     * 进入controller之前进行拦截
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = CookieUtils.getCookieValue(request, COOKIENAME);
        if (token != null) {
            Claims claims = JwtUtils.checkJWT(token);
            if (claims != null) {
                Integer userId = (Integer) claims.get("id");
                String name = (String) claims.get("name");
                if (userId == null || StringUtils.isEmpty(name)) {
                    sendJsonMessage(response, ReponseEntry.genLoginResult("请登录"));
                    return false;
                }
                //重新生成token
                String newToken = JwtUtils.geneJsonWebToken(new UserInfo(userId, name));
                // 将token写入cookie,并指定httpOnly为true，防止通过JS获取和修改
                CookieUtils.setCookie(request, response, COOKIENAME,
                        token, COOkIEMAXAGE, true);
                request.setAttribute("userId", userId);
                request.setAttribute("name", name);
                return true;
            }
        }
        sendJsonMessage(response, ReponseEntry.genLoginResult("请登录"));
        return false;
    }


    /**
     * 响应数据给前端
     *
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws IOException {

        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(gson.toJson(obj));
        writer.close();
        response.flushBuffer();
    }


}
