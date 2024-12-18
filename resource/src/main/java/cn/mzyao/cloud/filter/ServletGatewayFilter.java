package cn.mzyao.cloud.filter;


import cn.mzyao.cloud.tools.exception.GatewayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤非法请求
 * 非网关转发请求
 */
@Slf4j
@Component
@WebFilter
public class ServletGatewayFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("resource server filter init.");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String gateway = request.getHeader("gatewayKey");
        if (gateway == null || gateway.equals("") || !gateway.equals("key")) {
            throw new GatewayException(50003, "非法请求");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
