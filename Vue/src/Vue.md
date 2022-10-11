## 1、vue

是一套前端框架，简化原生js的dom操作

基于mvvm(Model-View-ViewModel)思想，可以实现数据的双向绑定

双向绑定:页面元素和数据模型进行双向绑定

创建Vue核心对象:

```javascript
new Vue({
    el: "#dv", // 用来指定受到Vue对象管理的元素
    data: function () { // 用于设置数据模型
        return {
            username: "张三"
        };
    }
});
```

## 2、Vue的常用指令

|                               |                                                    |
|-------------------------------|----------------------------------------------------|
| v-bind                        | 为html标签绑定属性值，如设置href，css样式等                        |
| v-model                       | 为表单元素创建双向绑定数据                                      |
| v-on                          | 为HTML绑定事件                                          |
| v-if</br>v-else</br>v-else-if | 条件性的渲染其元素，判定为true时渲染,否则不渲染                         |
| v-show                        | 根据条件展示某元素，区别在于切换的时display属性的值                      |
| v-for                         | 列表渲染，遍历容器的元素或者对象的属性                                |

- v-for = "元素名 in 容器名"
- v-for = "(元素名,索引值) in 容器名" 索引从0开始
1. beforeCreate（创建前）
2. created （创建后）
3. beforeMount (载入前)
4. mounted （载入后）
5. beforeUpdate （更新前）
6. updated   （更新后）
7. beforeDestroy（ 销毁前）
8. destroyed （销毁后）

### mounted:挂载完成
vue对象创建成功，html页面渲染成功.在挂载完成后，可以在该方法中发送异步请求，加载相关数据