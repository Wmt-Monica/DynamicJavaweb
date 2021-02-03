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

/**
 * @Classname ${NAME}
 * @Description TODO
 * @Date 2021/2/3 9:16
 * @Created by 翊
 */
@WebServlet(name = "DownloadFile")
public class DownloadFile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");

        // 获取下载的文件的文件名  设置a标签中 href 的文件参数 filename 值
        String  filename = request.getParameter("filename");

        // 获取要下载文件的文件了类型-----客户端通过文件的 MIME 类型去区分类型
        response.setContentType(this.getServletContext().getMimeType(filename));

        // 告诉客户端文件不是直接解析，而是以附件形式打开（下载）
        response.setHeader("Content-Disposition","attachment;fileName"+filename);

        // 使用 ServletContext 对象获取文件的绝对路径
        String path = this.getServletContext().getRealPath("download/"+filename);

        // 获取文件的输入流
        InputStream input = new FileInputStream(path);

        // 根据响应器对象 response 创建输出流对象
        ServletOutputStream output = response.getOutputStream();

        // 将文件对象写入响应器的输出流中
        int length;
        byte[] rush = new byte[1024];
        while ((length = input.read(rush)) > 0) {
            output.write(rush,0, length);
        }

    }
}
