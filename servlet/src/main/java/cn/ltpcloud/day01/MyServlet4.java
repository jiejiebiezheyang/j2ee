package cn.ltpcloud.day01; /**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/10/09/14:43
 * @Description: 通过注解配置Servlet
 */

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

//@WebServlet(urlPatterns = "/ms4")
//@WebServlet(value = "/ms4")
//@WebServlet("/ms4")
//@WebServlet({"/m1","/m2","/m3"})
//@WebServlet("/hello/ms4")
@WebServlet("*.do")
public class MyServlet4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ms4被访问了....");
    }
}
