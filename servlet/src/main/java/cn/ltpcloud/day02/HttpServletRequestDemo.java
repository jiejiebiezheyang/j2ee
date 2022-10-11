package cn.ltpcloud.day02;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ltpcloud@qq.com
 * @Date: 2022/10/10/10:10
 * @Description: 请求对象常用方法
 */

@WebServlet("/requestDemo")
public class HttpServletRequestDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // getRequestLine(request,response);
        // getRequestHeader(request, response);
        //getRequestBody(request, response);
        getRequestParam(request, response);
    }

    // 获取请求参数
    private void getRequestParam(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // 设置请求编码
        request.setCharacterEncoding("utf-8");
        // 获取指定含有单个值的请求参数
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        // 获取指定含有多个值的请求参数
        String[] hobbies = request.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
        System.out.println("==================================================");
        // key请求参数名 value请求参数值
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "=" + Arrays.toString(entry.getValue()));
        }
        System.out.println("==================================================");
        // 获取所有参数名
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()){
            System.out.println(names.nextElement());
        }
    }

    // 获取请求行
    private void getRequestLine(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求方式
        System.out.println(request.getMethod());
        // 获取协议和版本
        System.out.println(request.getProtocol()); // HTTP/1.1
        // 获取请求的url
        System.out.println(request.getRequestURL()); // http://localhost:8080/servlet/requestDemo
        // 获取请求的uri
        System.out.println(request.getRequestURI()); // /servlet/requestDemo
        // 获取虚拟目录
        System.out.println(request.getContextPath()); // /servlet
        // 获取Servlet资源路径
        System.out.println(request.getServletPath()); // /requestDemo
        // 获取请求参数
        System.out.println(request.getQueryString());
        // 获取客户端的ip
        System.out.println(request.getRemoteAddr()); // 0:0:0:0:0:0:0:1
    }

    // 获取请求头
    public void getRequestHeader(HttpServletRequest request, HttpServletResponse response) {
        // 获取指定的请求头
        System.out.println(request.getHeader("User-Agent"));
        // 获取所有请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            // 请求头的名称
            String headerName = headerNames.nextElement();
            System.out.println(headerName + "=" + request.getHeader(headerName));
        }
    }

    // 获取请求体信息
    public void getRequestBody(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 获取请求体数据的流
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
