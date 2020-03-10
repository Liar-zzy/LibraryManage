<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="webRoot" value="<%=basePath%>" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Library-Manage</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${webRoot}/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${webRoot}/resources/css/public.css" media="all" />
	<script type="text/javascript" src="${webRoot}/resources/jquery-3.4.1/jquery-3.4.1.js"></script>

</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-bg-green">
		<div id="nowTime"></div>
	</blockquote>
	<div class="layui-row layui-col-space10">
		<div class="layui-col-lg6 layui-col-md6">

			<img src="../images/welcome.png"  alt="welcome" />
<!--			<blockquote class="layui-elem-quote title">最新文章 <i class="layui-icon layui-red">&#xe756;</i></blockquote>-->
<!--			<table class="layui-table mag0" lay-skin="line">-->
<!--				<colgroup>-->
<!--					<col>-->
<!--					<col width="110">-->
<!--				</colgroup>-->
<!--				<tbody class="hot_news"></tbody>-->
<!--			</table>-->

		</div>
	</div>
	<script type="text/javascript" src="../layui/layui.js"></script>
	<script type="text/javascript" src="../js/main.js"></script>
</body>
</html>