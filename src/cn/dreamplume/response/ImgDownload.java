package cn.dreamplume.response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/2 14:48
 * @Created by 翊
 */
@WebServlet(name = "ImgDownload")
public class ImgDownload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 使用 ServletContext 对象的 getRealPath() 方法获取图片的绝对路径
        String imgPath = this.getServletContext().getRealPath("WEB-INF/img/love.jpg");

        // 通过相应对象 response 创建的输出流对象 ServletOutputStream 对象
        ServletOutputStream serOut = response.getOutputStream();

        // 根据图片对象的绝对路径字符串创建输入流对象
        InputStream input = new FileInputStream(imgPath);

        // 将图片的输入流通过 ServletOutputStream 对象交给响应器对象 response
        int length = 0;
        byte[] rush = new byte[1024];
        while ((length = input.read(rush)) > 0) {
            serOut.write(rush, 0 , length);
        }

        // 关闭字节流
        serOut.close();
        input.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
