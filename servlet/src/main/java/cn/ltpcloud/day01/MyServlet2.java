package cn.ltpcloud.day01;


import jakarta.servlet.*;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/10/09/10:08
 * @Description: servlet 生命周期
 */
public class MyServlet2 implements Servlet {
    public MyServlet2() {
        System.out.println("①创建Servlet对象");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.print("②init...");
        System.out.println("默认第一次访问时servlet的时候执行");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /*
     * service方法用来处理请求和响应
     * */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("③ 执行service...");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("④destroy...");
    }
}
