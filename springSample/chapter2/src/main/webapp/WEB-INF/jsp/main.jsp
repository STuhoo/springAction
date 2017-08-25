<%--
  Created by IntelliJ IDEA.
  User: cnn
  Date: 2017/8/16
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>小春论坛</title>
</head>
<body>
<!-- user 参数由LoginController.java中的LoginCheck函数传入(httpServletRequest.getSession().setAttribute("user", user))-->
${user.userName},欢迎您进入小春论坛，您当前积分为${user.credits};
</body>
</html>
