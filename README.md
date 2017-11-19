## 简介
  本项目为基于springboot的骨架项目，可在此基础上进行二次开发

## 提供方法
  - [x] 统一的响应结果封装
  - [x] 统一的异常处理
  - [x] 集成MyBatis插件
  - [x] 代码生成器生成基础业务模板
  - [x] 两种模式：RESTful和普通页面
  - [x] 集成Thymeleaf 3.0 模板
  - [x] 集成Swagger2 (http://localhost:1337/swagger-ui.html)
  - [x] 热部署
  - [ ] 集成JPA

## 使用
  1. clone 项目 | 数据表导入(mysql_data.sql)
  2. 使用 ```CodeGenerator```进行基础代码的生成
  3. 修改配置文件，扩展代码
  4. just run it

## 说明
  - ```com.lilikai.app.core```封装了常用的响应格式及Mapper和Service
  - ```src/main/resources```下主要为Thymeleaf模板、静态资源、配置文件及mapper
  - ```src/test/**```下存放了代码生成器及对应的模板
  - Spring-Boot不再推荐使用jsp、打包成war、Tomcat部署等方式
  - 推荐使用前后端分离的开发方式，后端仅提供RESTful API服务，专注于业务逻辑的处理
