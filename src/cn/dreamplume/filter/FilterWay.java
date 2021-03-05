package cn.dreamplume.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Classname FilterWay
 * @Description TODO
 * @Date 2021/3/5 19:02
 * @Created by 翊
 */
public class FilterWay implements Filter {
    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("FilterWay doFilter method is running....<br>");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
