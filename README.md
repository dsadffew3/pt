开发环境：
  1.IDE:IntelliJ IDEA 2024.3.1.1
  2.java版本： java version "23.0.1"
  3.包管理器：Maven
  4.框架： springboot 2.4.13
  5.api调试框架： swagger2 2.9.2
  6.ORM框架 ：mybatis-plus 3.4.2
  7.数据库 ：Oracle 19c
  8.数据库驱动： ojdbc8 21.1.0.0
部署流程：
  1.pom.xml中点击Maven的悬浮小图标，即可自动下载（依赖已配好）
  2.resources/application.properties中将orcle1替换为你本地数据库的SID，system和123456同理（确保监听服务已开启）
启动服务器：
  点击右上方的绿色箭头或在启动类中开启（确保8080端口没被其他软件占用）
项目api的调试：
  在浏览器上输入http://localhost:8080/swagger-ui.html可以了解API信息
