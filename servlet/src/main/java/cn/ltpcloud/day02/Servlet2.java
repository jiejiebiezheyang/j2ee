package cn.ltpcloud.day02; /**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/10/11/9:48
 * @Description: 转发的目的端
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/servlet2")
public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("servlet2操纵");
        String username = request.getParameter("username");
        System.out.println(username);

        Object money = request.getAttribute("money");
        System.out.println(money);

        // 转发到WEB-INF目录下
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/hello.html");
        requestDispatcher.forward(request, response);
    }
}
