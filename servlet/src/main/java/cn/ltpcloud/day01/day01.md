## 1、Servlet

- servlet是JavaEE规范(接口)
- JavaEE三大组件:servlet,filter(过滤器),listener(监听器)
- Servlet是运行在服务器上的小程序，可以接受客户端发送过来的请求并响应数据给客户端

## 2、手动实现Servlet

### 步骤 1

1. 编写一个类实现servlet接口
2. 实现servlet方法
3. 在web.xml里进行相关配置

### 步骤 2 实现servlet类

```java
public class MyServlet1 implements Servlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("MyServlet1 被访问了......");

    }
}
```

### 步骤 3 web.xml配置

```xml
<!-- 配置Servlet -->
<servlet>
    <servlet-name>MyServlet1</servlet-name>
    <servlet-class>cn.ltpcloud.day01.MyServlet1</servlet-class>
</servlet>
        <!-- 配置Servlet映射 -->
<servlet-mapping>
<servlet-name>MyServlet1</servlet-name>
<!--
    配置资源的访问路径
    '/' 在服务其中被解析为http://ip:port/虚拟的项目路径
    '/ms1' 在服务器中被解析为http://ip:port/虚拟的项目路径/ms1
 -->
<url-pattern>/ms1</url-pattern>
</servlet-mapping>
```

### 步骤 4

1. 在浏览器上输入
2. 服务器接收到客户端发送的url后，会解析url，获取到servlet访问路径(/ms1
3. 在web.xml文件中查询<url-pattern>,看时候由与上面路径匹配的
4. 如果匹配则查找对应的<servlet-name>，然后通过<servlet-name>查找<servlet-class>最终通过<servlet-class>找到servlet
5. tomcat会加载找到的servlet的类，对该类进行实例化，执行其service方法

## 3、servlet生命周期

1. 加载并实例化servlet
    - Servlet容器启动的时候，读取web.xml，创建Servlet，同时创建ServletConfig对象
2. 执行init方法
    - 当servlet第一次访问的时候执行init(在Service之前执行)
    - 可以在web.xml中配置init方法的执行时机
    - 说明-`加载并实例化Servlet和执行init方法只会执行一次，导致在访问该Servlet的时候只会存在1个Servlet对象`
    - 问题-`如果有多个线程访问同一个Servlet，可能会发生线程安全问题`
    - 解决-`尽量不要在servlet中定义成员变量，改用局部变量，如果非要定义成员变量，也不要对成员变量进行修改`
3. 执行service方法
    - 每次访问Servlet都会执行
4. 执行destroy方法
    - 当服务器关闭时，销毁Servlet，这时候执行destroy方法
    - 只能在服务正常关闭的时候执行
    - 一般用于资源释放

## 4、Servlet继承体系

```java
class GenericServlet implements Servlet {
    private ServletConfig config;
    public void init() {
    }
    public abstract void service(request, response);
    public void destroy() {
    }
}
```

```java
class HttpServlet extends GenericServlet {
    public void service(req, resp) {
        String method = req.getMethod();
        if ("GET".equals(method)) {
            doGet(req, resp);
        } else if ("POST".equals(method)) {
            doPost(req, resp);
        }
    }
   protected void doGet(HttpServletRequest request, HttpServletResponse response) {
   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) {
   }
}
```

```java
public class MyServlet3 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
```
**总结:一般我们创建的Servlet都是继承HttpServlet间接的去实现Servlet,可以通过开发工具创建Servlet类**

## 5、通过注解配置Servlet 

@WebServlet(urlPatterns = "/ms4")</br>
@WebServlet(value = "/ms4")</br>
@WebServlet(资源路径)</br>

说明:
1. 资源路径可以设置多个
2. 路径的定义规则
   - /xxx 单层路径
   - /xxx/xxx 多层路径
   - *.do 扩展名匹配

## 6、HTTP协议

- 协议
  - 双方约定好的必须要遵循的规则成为协议

- HTTP(Hyper Text Transfer Protocol) 超文本传输协议
  - 超文本传输协议
  - 规定了客户端(浏览器)和服务端之间的通信规则

- 特点
  - 基于TCP/IP的高级协议
  - 默认端口80
  - 基于请求响应模型:一次请求对应一次响应
  - 无状态协议:不同请求之间是独立的，不能交互数据

- 历史版本
  - 1.0 每次请求响应都会创建1和新的连接对象
  - 1.1 连接对象可以复用

- 说明
  - HTTP协议:客户端和服务端交互式，规定通信的数据格式
  - HTTP协议中的数据成为报文

## 7、请求的HTTP格式
`请求:客户端给服务端发送数据`</br>
`响应:服务端给客户端发送数据`

请求方式分别为:get 和 post

1. get方式
   - 请求行
     - 请求方式:GET
     - 请求资源url:资源路径[?请求参数列表]
     - 状态码: 200
     - 版本:HTTP 1.1
   - 请求头
     - key: value 
     - 常见的请求头
       - Accept: 客户端可以接收的数据类型
       - Accept-Encoding: 客户端接收的数据的编码(压缩)方式
       - Accept-Language: 客户端可以接受的语言
       - Referer: 表示资源的出处
       - User-Agent: 浏览器信息
2. POST 方式
   - 请求行
     - 请求方式:POST
     - 请求资源url:资源路径
     - 状态码: 200
     - 版本:HTTP 1.1
   - 请求头
     - key: value
   - 请求空行
     - 用于分割请求行和请求体
   - 请求体
     - 客户端发送给服务器的数据

## 8、哪些请求是get，哪些请求是post
get
- 表达提交方式设置为get
- 浏览器地址栏地址栏访问
- 超链接访问
- &lt;link&gt;引入外部的css文件
- &lt;img&gt;引入外部的图片
- &lt;script&gt;引入外部的js文件
- iframe引入html页面
