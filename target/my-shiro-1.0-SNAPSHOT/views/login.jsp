<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2018/5/15
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>登录页面</div>
    <form action="/login" method="post" >
        用户名:<input name="username">
        密码:<input name="password" type="password">

        <input type="submit" value="登录">
    </form>
</body>
</html>
