package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 在请求之前执行还是之后执行
     * 之前: pre
     * 之后: post
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 多个过滤器之间执行的顺序(数字越小越先执行)
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否开启
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的内容
     * return 任何值都表示继续执行
     * setsendzuulResponse(false)表示不再执行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 获取request域
        HttpServletRequest request = currentContext.getRequest();
        //任何经过网关的请求都是两次，第一次是访问负责分发的请求，OPTIONS这个方法直接放行就可以
        if (request.getMethod().equals("OPTIONS")) {
            return null;
        }
        // 登录页面不需要判断
        if (request.getRequestURL().toString().indexOf("login") > 0) {
            return null;
        }

        // 得到头信息
        String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header)) {
            if (header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String role = (String) claims.get("roles");
                    if (role.equals("admin")) {
                        // 把头信息转发下去， 并且放行
                        currentContext.addZuulRequestHeader("Authorization", header);
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    currentContext.setSendZuulResponse(false);  // 终止运行
                }
            }
        }
        currentContext.setSendZuulResponse(false);      // 终止运行
        currentContext.setResponseStatusCode(403);      // 权限不足
        currentContext.setResponseBody("权限不足");
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }
}
