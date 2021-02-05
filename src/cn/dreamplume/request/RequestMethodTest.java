package cn.dreamplume.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/5 13:54
 * @Created by 翊
 */
@WebServlet(name = "RequestMethodTest")
public class RequestMethodTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        // 获取 HTTP 请求消息中的请求方式（get,post...）
        String methodName = request.getMethod();
        // 获取请求行中资源名称部分，即位于URL的主机和端口之后，参数部分之前的部分
        String requestURI = request.getRequestURI();
        // 获取请求行中的参数部分，也就是资源路径后面(?)之后的所有内容
        String queryString = request.getQueryString();
        // 获取请求行中的协议名和版本
        String  protocol = request.getProtocol();
        // 获取请求 URL 中属于 web 应用程序的路径，这个路径以'/'。如果请求行 URL 属于 web 站点的根目录，那么返回的结果为空字符串
        String ContextPath = request.getContextPath();
        // 获取 Servlet 的名称或 Servlet 所映射的路径
        String servletPath = request.getServletPath();
        // 获取请求客户端的 IP 地址
        String remoteAddr = request.getRemoteAddr();
        // 获取客户端的完整主机名，如果无法解析出客户的完整主机名，该方法会返回客户端的IP地址
        String remoteHost = request.getRemoteHost();
        // 获取请求客户端网络连接的端口号
        int remotePort = request.getRemotePort();
        // 获取 web 服务器上接受当前请求网路连接的IP地址
        String localAddr = request.getLocalAddr();
        // 获取 web 服务器上接收当前网络连接的IP所对应的主机名
        String localName = request.getLocalName();
        // 获取连接 web 服务器上接收当前网路连接的端口号
        int localPort = request.getLocalPort();
        // 获取当前请求指向的主机名。即 HTTP 的请求消息中 Host 头字段所对应的主机名部分
        String serverName = request.getServerName();
        // 获取当前请求所连接的服务器端口号，即如果 HTTP 请求消息中的 Host 头字段所对应的端口号部分
        int serverPort = request.getServerPort();
        // 获取请求的协议名 如(http,https,ftp...)
        String scheme = request.getScheme();
        // 获取客户端发出的请求时的完整URL，包括协议，服务器名，端口号，资源路径等信息，但是不包括后面的查询参数部分。
        StringBuffer requestURL = request.getRequestURL();

        // 将获取的数据写入提交页面
        PrintWriter writer = response.getWriter();
        writer.write(methodName+"<br>");
        writer.write(requestURI+"<br>");
        // 为了避免参数值在页面显示乱码。使用  URLDecoder.decode(参数字符串,"utf-8")编码转换
        writer.write(URLDecoder.decode(queryString,"utf-8")+"<br>");
        writer.write(protocol+"<br>");
        writer.write(ContextPath+"<br>");
        writer.write(servletPath+"<br>");
        writer.write(remoteAddr+"<br>");
        writer.write(remoteHost+"<br>");
        writer.write(remotePort+"<br>");
        writer.write(localAddr+"<br>");
        writer.write(localName+"<br>");
        writer.write(localPort+"<br>");
        writer.write(serverName+"<br>");
        writer.write(serverPort+"<br>");
        writer.write(scheme+"<br>");
        writer.write(requestURL+"<br>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
