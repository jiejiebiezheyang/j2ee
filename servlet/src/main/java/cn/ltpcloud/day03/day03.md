## 1、HttpServletResponse

`每次只要请求进入到Tomcat服务器，Tomcat服务器就会创建一个响应对象，可以通过response对象将客户端需要的信息进行响应`

## 常用方法

- 响应行
  - response.setStatus(状态码) 设置状态码
- 响应头
  - response.setHeader(name,value) 设置响应体
- 响应体
  - 通过获取流对象给客户端响应数据 
  - response.getOutputStream()
  - response.getWriter()
  - 响应乱码的解决
    - response.setContentType("text/html;charset=utf-8")

## 请求重定向

指客户端给服务器发送请求，然后服务器告诉客户端，我这边不处理了，并给出了新的处理地址
- 特点
  - 浏览器地址栏发生了改变
  - 包含了两次请求
  - 不能恭喜request域对象中的设置的数据
  - 不能访问WEB-INF内的资源
  - 可以访问工程以外的资源

## 路径的说明

- 相对路径
  - ./xx/xx
  - ../xx/xx
  - xx/xx
- 绝对路径
  - http://ip:port/[虚拟路径]/资源路径
  - /资源路径
  - /[虚拟路径]/资源路径
- "/" 的含义
  - 在浏览器中被解析为:http://ip:port/
  - 在服务器中被解析为:http://ip:port/[虚拟路径]
- /js
  - 表示js目录下文件和文件夹
- /js/
  - 表示js内所有的文件和文件夹(包含子文件夹)