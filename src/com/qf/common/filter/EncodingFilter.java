package com.qf.common.filter;

/**
 * @author Salted Fish
 * @create 2022-06-14 15:58
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.media.jfxmedia.track.Track.Encoding;

/**
 * 设置字符编码 过滤器
 * @author wanyk
 *
 * 2022年3月30日
 */
//注解开发 替代配置文件
@WebFilter(filterName="encodingFilter",
        urlPatterns="/*",
        initParams={@WebInitParam(name="encoding",value="UTF-8")})
public class EncodingFilter implements Filter{

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset="+encoding);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
