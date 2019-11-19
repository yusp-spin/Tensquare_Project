package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import utils.JwtUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class WebFilter extends ZuulFilter {

    @Resource
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
        //得到request上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 获取request域
        HttpServletRequest request = currentContext.getRequest();
        // 得到头信息
        String header = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(header)) {
            currentContext.addZuulRequestHeader("Authorization", header);
        }
        return null;
    }
}
