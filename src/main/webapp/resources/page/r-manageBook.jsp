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
    <title>借阅书籍管理</title>
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
            <h1 style="color: green">借阅书籍管理</h1>
        </legend>
    </fieldset>
</div>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table lay-filter="demo" id="demo">

    <div id="userBar" style="display:none;">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delBook">归还</a>
    </div>
    <thead>
    <tr>
        <th lay-data="{field:'1',sort:true,align:'center'}">id</th>
        <th lay-data="{field:'2',align:'center'}">书名</th>
        <th lay-data="{field:'3',align:'center'}">借阅时间</th>
        <th lay-data="{field:'4',align:'center'}">最晚归还时间</th>

        <th lay-data="{field:'11', toolbar:'#userBar' ,width:200,align:'center', fixed:'right'}">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${borrowBookList}" var="book">
        <tr>

            <td>${book.borrowBookId}</td>
            <td>${book.borrowBookName}</td>
            <td>${book.lendTime}</td>
            <td>${book.returnTime}</td>

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
            , height: 'funll-300' //设置高度
            , page: true//开启分页
            , toolbar: '#userToolBar'

        });


        //监听行工具事件
        table.on('tool(demo)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'delBook') { //删除

                //layer.msg("删除");
                console.log(data);

                var borrowBookId = data[1];
                var borrowPersonId = date[3];

                var alterobj = {
                    borrowBookId : borrowBookId,
                    borrowPersonId : borrowPersonId
                };
                $.ajax({
                    url: '${ctx}/borrowAndRead/deleteBorrowAndRead',
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(alterobj),
                    success: function (databack) {
                        console.log(databack);
                        if (databack.deleteUser == "success") {
                            layer.msg("还书成功")
                        }
                        setTimeout(function () {  //使用  setTimeout（）方法设定定时2000毫秒
                            window.location.reload();//页面刷新
                        }, 2000);
                    }
                })
            }
        });



        //保存
        form.on("submit(doSubmit)", function (obj) {

            //alert(url);
            //这是没得办法，url传不过来
            if (typeof (url) == "undefined") {
                url = "";
            }
            //alert(url);
            //序列化表单数据
            var params = $("#dataFrm").serialize();
            $.post(url, params, function (obj) {
                //alert(params);
                //layer.msg(obj);
                //alert(params);
                //关闭弹出层
                layer.close(mainIndex);
                //刷新数据 表格
                window.location.href = "/market_3x/UserServlet?type=goUser";
                return false;

            })
        });


    });

</script>
</body>
</html>