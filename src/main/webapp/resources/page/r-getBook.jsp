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
    <meta charset="UTF-8">
    <title>书籍管理</title>
    <script type="text/javascript" src="${webRoot}/resources/layui/layui.js"></script>
    <link rel="stylesheet" href="${webRoot}/resources/layui/css/layui.css">
    <script type="text/javascript" src="${webRoot}/resources/jquery-3.4.1/jquery-3.4.1.js"></script>

</head>
<body>

<script>
    function openAddBook(data) {
        var $ = layui.jquery;
        var layer = layui.layer;
        var table = layui.table;//加载表格模块
        var form = layui.form;

        var obj = data;
        console.log(obj);

        layer.open({
            type: 1,
            title: '修改信息',
            content: $("#saveOrUpdateDiv"),
            area: ['400px', '200px'],
            success: function (index) {
                //alert("修改成功");
            }
        });
    }
</script>

<!-- 搜索条件开始 -->
<div class="menu">
    <fieldset class="layui-elem-field layui-field-title"
              style="margin-top: 20px;">
        <legend>
            <h1 style="color: green">书籍管理</h1>
        </legend>
    </fieldset>
    <form class="layui-form" action="/resources/book/?name=#searchname">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    <p1 style="font-size:16px;">书名：</p1>
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" name="name" id="searchname">
                </div>
                <input type="submit" class="layui-btn layui-btn-danger" value="查询">

                <%--添加书籍功能还未书写--%>
                <%--                <button type="button" class="layui-btn" lay-event="add" onclick="openAddBook()">--%>
                <%--                    添加书籍--%>
                <%--                </button>--%>
            </div>
        </div>
    </form>
</div>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table lay-filter="demo" id="demo">
    <div id="userToolBar" name="userToolBar" style="display:none">
        <button type="button" class="layui-btn" lay-event="borrow">借阅</button>
    </div>
    <%--    <div id="userBar" style="display:none;">--%>
    <%--        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="borrow">借阅</a>--%>
    <%--    </div>--%>
    <thead>
    <tr>
        <th lay-data="{checkbox:true ,fixed:'left'}"></th>
        <th lay-data="{field:'id',align:'center'}">ID</th>
        <th lay-data="{field:'name',align:'center'}">书名</th>
        <th lay-data="{field:'isbn',align:'center'}">ISBN</th>
        <th lay-data="{field:'author',align:'center'}">作者</th>
        <th lay-data="{field:'type',align:'center'}">种类</th>
        <th lay-data="{field:'price',align:'center'}">价格</th>
        <th lay-data="{field:'publisher',align:'center'}">出版社</th>
        <th lay-data="{field:'publishTime',align:'center'}">出版时间</th>
        <th lay-data="{field:'registerTime',align:'center'}">登记时间</th>
        <th lay-data="{field:'lendState',align:'center'}">借阅状态</th>
        <%--        <th lay-data="{field:'11', toolbar:'#userBar' ,width:200,align:'center', fixed:'right'}">操作</th>--%>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${SESSION_BOOK}" var="book">
        <c:set var="isLend" scope="session" value="${book.lendState}"/>
        <tr>
            <td></td>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.isbn}</td>
            <td>${book.author}</td>
            <td>${book.type}</td>
            <td>${book.price}</td>
            <td>${book.publisher}</td>
            <td>${book.publishTime}</td>
            <td>${book.registerTime}</td>
                <%--            <td>${book.lendState}</td>--%>

            <c:if test="${isLend == true}">
                <td>已被借阅</td>
            </c:if>
            <c:if test="${isLend == false}">
                <td>可借阅</td>
            </c:if>

        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 数据表格结束 -->

<!-- 渲染数据表格 -->
<script type="text/javascript">
    //静态表格
    layui.use(['jquery', 'layer', 'form', 'table'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var table = layui.table;//加载表格模块
        var form = layui.form;


        table.init('demo', {
            id: 'demo'
            , height: 'full-300' //设置高度
            , page: true//开启分页
            , toolbar: '#userToolBar'

        });

        //监听头部工具栏事件
        table.on("toolbar(demo)", function (obj) {
            var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
            var data = checkStatus.data;  //获取选中行数据

            if (obj.event == 'borrow') {
                console.log("borrow");
                console.log(data);
                var borrowBookId = data[0].id;
                var borrowBookName = data[0].name;
                var alterObj = {
                    borrowBookId: borrowBookId,
                    borrowBookName: borrowBookName
                };
                $.ajax({
                    url: '${ctx}/resources/borrowAndRead/addBorrowAndRead',
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(alterObj),
                    success: function (databack) {
                        console.log(databack);
                        if (databack.add == "success") {
                            layer.msg("借阅成功")
                        }
                        setTimeout(function () {  //使用  setTimeout（）方法设定定时2000毫秒
                            window.location.reload();//页面刷新
                        }, 2000);
                    }
                })
            }
        });
    });

</script>
</body>
</html>