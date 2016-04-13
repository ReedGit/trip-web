<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
<body>  
<h2>文件上传实例</h2>  
  
  
<form action="user/1/change_avatar" method="post" enctype="multipart/form-data">  
    选择文件:<input type="file" name="headImage">
    <input type="text" name="token">  
    <input type="submit" value="提交"> 
    <input type="checkbox" name="autologin" value="1">  
</form>  

<img alt="" src="upload/1/05774a51-c038-47bb-9d85-26ccaec4657f.png">
  
  
</body>  
</html>
