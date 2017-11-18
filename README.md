## 简介
  本项目为基于springboot的骨架项目，可在此基础上进行二次开发

## 提供方法
  - [x] 统一的响应结果封装
  - [x] 统一的异常处理
  - [x] 集成MyBatis插件
  - [x] 代码生成器生成基础业务模板
  - [x] 两种模式：RESTful和普通页面
  - [x] Thymeleaf 模板引擎
  - [x] 热部署

## 使用
  1. clone 项目 | 数据表导入(mysql_data.sql)
  2. 使用 ```CodeGenerator```进行基础代码的生成
  3. 修改配置文件，扩展代码
  4. just run it

## 说明
  - 基础的魔术常量，可直接使用
    根目录: ```${__SERVER__}```
    当前URL: ```${__URL__}```
    完整URL(带参数): ```${__FULL_URL__}```
    静态资源目录: ```${__STATIC__}```
  - ```com.lilikai.app.core```封装了常用的响应格式及Mapper和Service
  - ```src/main/resources```下主要为配置文件及mapper
  - ```src/test/**```下存放了代码生成器及对应的模板