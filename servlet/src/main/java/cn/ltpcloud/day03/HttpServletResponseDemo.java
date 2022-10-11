package cn.ltpcloud.day03; /**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/10/11/10:07
 * @Description:
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/responseDemo")
public class HttpServletResponseDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置状态码
        // response.setStatus(201,"oka");
        // 设置响应头
        // response.setHeader("Content-Length","1024");
        // 获取输出流,给客户端发送响应数据
        // ServletOutputStream servletOutputStream = response.getOutputStream();
        // servletOutputStream.write("haha".getBytes());
        // servletOutputStream.close();

        // 解决响应乱码问题
        // response.setCharacterEncoding("UTF-8");
        // response.setHeader("Content-Type","text/html;charset=utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.write("你好吗");
        printWriter.close();
    }
}
