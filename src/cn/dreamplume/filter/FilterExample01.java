package cn.dreamplume.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Classname FilterExample01
 * @Description TODO
 * @Date 2021/3/5 15:48
 * @Created by 翊
 * 快速入门 Filter,其中要将测试类实现 Filter 接口
 * 注意：
 *      1.该接口的是 javax.servlet 下的 filter 包
 *      2.其中要在 web.xml 文件中对该实现 Filter 测试类对象进行注册
 */
public class FilterExample01 implements Filter {
    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("FilterExample01 is running....");
        // 调用 FilterChain 对象的 doFilter() 方法传入，请求和相应对象参数来对目的资源进行放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
