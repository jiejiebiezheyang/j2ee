## 1、jdbc
- 是java语言定义的一套标准(接口),用于和数据库进行通信
- 由不同的数据库厂商根据jdbc提供的不同的实现类(数据库驱动)

## 2、数据库的连接
1. 导入数据库驱动
    `mysql-connector-java.jar`
2. 编写配置文件
    ```
    # 如果ip为当前本地主机，且端口为3306 ，localhost:3306可以省略
    # url = jdbc:mysql://localhost:3306/test
    driver = com.mysql.cj.jdbc.Driver
    url = jdbc:mysql:///test
    user = root
    password = 123
    ```
3. 获取配置数据
4. 注册驱动
    ```
    Class.forName(driver)
    实际上为: DriverManager.registerDriver( new com.mysql.jdbc.Driver())
    ```
5. 获取连接
    `DriverManager.getConnection(url,user,password)`

## 3、Statement
- 用于执行静态的SQL语句并返回她所生成结果的对象
- conn.createStatement()  连接对象创建statement对象
- execute(String sql)     执行更新操作          boolean
- executeUpdate(String sql)   执行更新操作      int
- executeQuery(String sql)    执行查询操作      ResultSet

## 4、ResultSet类-查询得到的结果
- boolean next()  判断是否有下一行数据，如果有返回true,并将指针向下移动,没有返回false
- 通过列索引获取值(列索引从1开始)
    - getInt(columnIndex)
    - getString(columnIndex)
    - getObject(columnIndex)
- 通过列的别名
    - getInt(columnLabel)
    - getString(columnLabel)
    - getObject(columnLabel)

    - ResultSetMetaData   获取结果集元数据
    - metaData.getColumnCount()   获取列数
    - metaData.getColumnLabel(columnIndex)   获取列别名