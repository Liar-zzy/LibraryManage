<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<c:set var="webRoot" value="<%=basePath%>"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>个人中心</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${webRoot}/resources/layui/css/layui.css" media="all"/>
	<script type="text/javascript" src="${webRoot}/resources/layui/layui.js"></script>
	<script type="text/javascript" src="${webRoot}/resources/jquery-3.4.1/jquery-3.4.1.js"></script>
</head>
<body class="childrenBody">
<form class="layui-form layui-row">
	<div class="layui-col-md6 layui-col-xs12">
		<div class="layui-form-item">
			<label class="layui-form-label">ID</label>
			<div class="layui-input-block">
				<input type="text" value="${SESSION_USER.Id}" id="alterId" disabled class="layui-input layui-disabled" readonly="true">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">姓名</label>
			<div class="layui-input-block">
				<input type="text" value="${SESSION_USER.name}" disabled class="layui-input layui-disabled" readonly="true">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block">
				<input type="text" value="${SESSION_USER.gender}" disabled class="layui-input layui-disabled" readonly="true">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">注册时间</label>
			<div class="layui-input-block">
				<input type="text" value="${SESSION_USER.registerDate}" disabled class="layui-input layui-disabled" readonly="true">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">联系方式</label>
			<div class="layui-input-block">
				<input type="tel" value="${SESSION_USER.tel}" id="alterTel" lay-verify="phone" class="layui-input userPhone">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">地址</label>
			<div class="layui-input-block">
				<input type="text" value="${SESSION_USER.address}" id="alterAddress" lay-verify="email" class="layui-input userEmail">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-block">
				<input type="text" value="${SESSION_USER.password}" id="alterPassword" lay-verify="password" class="layui-input userPassword">
			</div>
		</div>
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn"  id="alterAll" >立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" src="../../layui/layui.js"></script>
<script type="text/javascript" src="userInfo.js"></script>
<script type="text/javascript" src="../../js/cacheUserInfo.js"></script>
<script>
	$('#alterAll').click(function () {
		var alteredId=$('#alterId').val();
		var alteredTel=$('#alterTel').val();
		var alteredAddress=$('#alterAddress').val();
		var alteredPassword= $('#alterPassword').val();


		var alteredObj={

			Id:alteredId,
			tel:alteredTel,
			password:alteredPassword,
			address:alteredAddress

		}
		$.ajax({
			url:'${ctx}/reader/updateReader',
			type:'post',
			contentType:'application/json',
			data:JSON.stringify(alteredObj),
			success:function (data) {
				if(data.update=="success"){
					layer.msg("修改成功")
					setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
						window.location.reload();//页面刷新
					},3000);
				}


			}
		})
		return false;
	});
</script>
</body>
</html>