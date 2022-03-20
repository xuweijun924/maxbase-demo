### Maxbase Demo 接口文档

| 修改人 |  修改日期  | API版本 |   备注   |
| :----: | :--------: | ------- | :------: |
| 徐胜祥 | 2022-03-17 | V1.0.0  | 初次创建 |

### 1. Swagger 文档地址
- 基于 Knife4j UI：http://host:port/doc.html

- 基于 Swagger UI：http://host:port/swagger-ui.html

  

### 2. 源码托管地址

GitHub：https://github.com/xuweijun924/maxbase-demo.git



### 3. Package Structure

```
com.maxnerva.maxbase.demo
	- common：全局而相对低频操作的模块
		- component：被 @Component 标识的 Spring 组件
		- configuration：被 @Configuration 标识的 Spring 组件
		- constant：常量管理
		- context：上下文管理
		- exception：异常处理
		- interceptor：拦截器
		- properties：配置管理
		- swagger：Swagger
		- util：与应用相关的工具类（通用工具类三方有很多，非常建议使用 Hutool）
	- controller：视图层
	- dao：数据库访问层
		- entity：表映射
		- mapper：MyBatis Mapper 接口 & XML 配置
	- delegate：单表业务操作层
	- pojo：一些简单的 Java 对象
		- bo：业务对象
		- converter：MapStruct 对象转换
		- dto：数据传输对象
		- vo：视图对象
	- service：综合业务操作层（含接口声明）
		- impl（接口实现类）
```



### 4 核心开发流程

> 具体，请参考代码！

- 有表操作：controller -> service -> delegate -> dao
- 无表操作：controller -> service