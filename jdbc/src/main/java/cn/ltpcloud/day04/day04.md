## 1、DBUtils
`Commons DbUtils是Apache组织提供的一个对JDBC进行简单封装的开源工具类库，使用它能够简化JDBC应用程序的开发，同时也不会影响程序的性能。`

### 常用方法
#### QueryRunner():获取查询运行器
- runner.update(conn,sql,params)
  - :执行通用的“更新”
- runner.query(sql,BeanHandler<T>(Class<T> clazz), params)
  - :查询单条数据，封装成Bean对象
- runner.query(sql,BeanListHandler<T>(Class<T> clazz), params)
  - :查询多条数据，每条记录封装成Bean对象，多个Bean放入List
- runner.query(sql,MapHandler, params)
  - :查询单条数据，返回Map(key：字段名,value：字段值)
- runner.query(sql,MapListHandler, params)
  - :查询多条数据，每条记录封装成一个Map(key：字段名,value：字段值)，多个Map放入List
- runner.query(sql,ScalarHandler<T>, params)
  - :查询单个数据
- DbUtils.closeQuietly(conn,st,rs)
  - :释放资源

## 2、JDBC Template
`Spring 框架提供的对jdbc的简单封装`
#### JdbcTemplate(Datasource ds) 创建JdbcTemplate
- template.update(sql,params)
  - :执行通用的”更新“
- template.queryForObject(sql,Class<T>,params)
  - :查询单个数据
- template.queryForObject(sql,BeanPropertyRowMapper<T>(Class<T>),params)
  - :查询单条数据，封装成Bean对象
- template.query(sql,BeanPropertyRowMapper<T>(Class<T>),params)
  - :查询单条数据，封装成Bean对象,多条Bean放入List
- template.queryForMap(sql,params)
  - :查询单条数据，返回map
- template.queryForList(sql,params)
  :查询多条数据，返回mapList
