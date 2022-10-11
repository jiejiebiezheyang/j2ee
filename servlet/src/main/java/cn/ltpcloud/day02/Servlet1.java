package cn.ltpcloud.day02; /**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/10/11/9:44
 * @Description:
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/servlet1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("servlet1操作。。。");
        String username = request.getParameter("username");
        System.out.println(username);
        // 设置域对象
        request.setAttribute("money", 1000);

        // 获取请求分配器，用来分配下一个资源的访问路径
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/servlet2");

        requestDispatcher.forward(request, response);
    }
}
