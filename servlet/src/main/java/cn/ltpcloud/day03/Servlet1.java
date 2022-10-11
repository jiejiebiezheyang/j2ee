package cn.ltpcloud.day03; /**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/10/11/10:26
 * @Description:
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/servlet2-1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("当前窗口不处理业务，请到servlet2-2进行处理");
        // 响应状态码302(表示重定向)
        response.setStatus(302);
        // 响应新资源路径
        response.setHeader("Location","/servlet/servlet2-2");
    }
}
