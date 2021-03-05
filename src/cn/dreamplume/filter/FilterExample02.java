package cn.dreamplume.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Classname FilterExemple02
 * @Description TODO
 * @Date 2021/3/5 16:36
 * @Created by 翊
 * 1.在原先 FilterExample01 对 FilterIntroductionServlet 目的资源的释放放行的控制上
 *   还可以创建 FilterExample02 来对 FilterIntroductionServlet 该目的资源再加上一个是否放行的控制
 * 2.再 init() 方法里面可以对 Filter 对象进行初始化，和获取 web.xml 文件中的配置数据参数信息
 */
public class FilterExample02 implements Filter {

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
//        System.out.println("===================");
//        System.out.println("filter name is "+filterConfig.getFilterName());
//        System.out.println("filter init name value is "+filterConfig.getInitParameter("name"));
//        System.out.println("servletContext is "+filterConfig.getServletContext());
//        System.out.println("===================");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("FilterExample02 is  running....");
    }

    @Override
    public void destroy() {

    }
}
