## 1、HTTP协议响应格式

- 响应行
    - 协议和版本 HTTP 1.1
    - 状态码 200
    - 状态描述 ok
- 响应头
    - Content-Length 响应体的长度
    - Content-Type 响应体的类型
    - Date 响应的日期
    - Server 服务器信息
- 响应空行
- 响应体
    - 发送给客户端的数据

## 2、常见的响应状态码说明

- 200 表示请求成功，正常进行响应
- 302 表示请求重定向
- 404 表示服务器收到了请求，但是请求的资源不存在
- 500 表示服务器收到请求，但是服务器内部发生了错误

## 3、MIME(Multipurpose Internet Mail Extensions)

`简单来说可以看成是HTTP协议中的数据类型`

格式: "大类型/小类型"，会有一个与之对应的文件扩展名

| 常见的MIME类型(通用型)：  | -----------------------------  |
|------------------|--------------------------------|
| 超文本标记语言文本        | .html text/html                |
| xml文档            | .xml text/xml                  |
| XHTML文档          | .xhtml application/xhtml+xml   |
| 普通文本             | .txt text/plain                |
| RTF文本            | .rtf application/rtf           |
| PDF文档            | .pdf application/pdf           |
| Microsoft Word文件 | .word application/msword       |
| PNG图像            | .png image/png                 |
| GIF图形            | .gif image/gif                 |
| JPEG图形           | .jpeg,.jpg image/jpeg          |
| au声音文件           | .au audio/basic                |
| MIDI音乐文件 mid     | .midi audio/midi,audio/x-midi  |
| RealAudio音乐文件    | .ra, .ram audio/x-pn-realaudio |
| MPEG文件           | .mpg,.mpeg video/mpeg          |
| AVI文件            | .avi video/x-msvideo           |
| GZIP文件           | .gz application/x-gzip         |
| TAR文件            | .tar application/x-tar         |
| 任意的二进制数据         | application/octet-stream       |

## 4、HttpServletRequest

#### 说明

`每次只要请求进入到Tomcat服务器，Tomcat服务器就会将请求过来的HTTP协议信息解析并封装到1个Request对象中，然后传递给service方法的形参，在Service方法中做了请求的分发操作，后来又将请求对象传递给了doGET和doPost方法的形参。最后我们可以通过HttpServletRequest获取所有的请求信息`

#### 常见方法

- request.getMethod() 获取请求方式
- request.getProtocol() 获取请求协议和版本
- request.getRequestURL() 获取URL
- request.getRequestURI()  获取URI
- request.getContextPath() 获取虚拟目录

- URL 统一资源定位符
- URI 统一资源标识符
- 区别-`URI表示的范围比URL大，URL包含在URI里`

- request.getHeader(headerName) 获取指定请求头
- request.getReader或request.getInputStream() 获取请求体
  - `但是是一次性读取整个请求体数据，对于请求参数的获取不是太方便`

#### 其他方法

- 获取请求参数的方法
  - getParameter(ParameterName) 通过请求参数名获取请求参数的值
  - request.setCharacterEncoding("utf-8") 
    - `如果POST请求中 中文 有乱码，可以设置请求编码格式,如果请求方式为get，且tomcat版本为8及以上，就不会存在乱码问题`
  - request.getParameter("username") 获取指定含有单个值的请求参数
  - request.getParameterValues("hobby") 获取指定含有多个值的请求参数
  - request.getParameterMap() key请求参数名 value请求参数值
  - request.getParameterNames() 获取所有参数名

#### 共享数据

域对象:一个有范围的对象，可以在这个范围在共享数据

request是一个域对象，可以在一次请求中共享数据
- request.setAttribute(key,value) 设置数据
- request.getAttribute(key) 获取数据
- request.removeAttribute(key) 删除数据

#### 请求转发

是一种服务器内部的资源跳转方式

指服务器收到客户端请求后，从1个资源跳转到另一个资源的操作就叫请求转发
- request.getRequestDispatcher(转发的资源地址) 获取请求分配器
- requestDispatcher.forward(request, response) 请求转发
- 特性
  - 浏览器地址栏不会发生变化
  - 不论转发多少次，都属于一次请求
  - 可以共享request域对象中设置的数据
  - 可以转发到WEB-INF目录下
  - 不可以访问工程以外的资源

