## 1、Statement弊端
1. 存在拼串
   - String sql = "insert into account(name,balance) values('" + name + "'," + balance + ")";
2. 存在sql注入
3. 批量插入效率地下
4. 不可以操作blob类型的数据

## 2、PreparedStatement
`表示预编译的SQL语句对象`

`是Statement的子类，可以解决Statement存在的弊端`
```
conn.prepareStatement(sql)  获取PreparedStatement
// 设置占位符的值,parameterIndex:参数(占位符)索引,从1开始
setInt(parameterIndex,value)
setString(parameterIndex,value)
setBlob(parameterIndex,value)
setObject(parameterIndex,value)

execute()     执行更新操作          boolean
executeUpdate()   执行更新操作      int
executeQuery()    执行查询操作      ResultSet
```

## 3、PreparedStatement操作Blob
- setBlob()
- getBlob()
- 注意:如果报 文件太大 错误,需要修改MySQL配置文件my.ini  max_allowed_packet = 16M
- rewriteBatchedStatements = true 开启批处理参数








