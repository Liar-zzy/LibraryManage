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
    <form class="layui-form" action="/user/ListAllStu?name=#searchname">
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
    <div id="userToolBar" style="display:none;">
        <button type="button" class="layui-btn" lay-event="borrow">借阅</button>
    </div>
    <%--    <div id="userBar" style="display:none;">--%>
    <%--        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="borrow">借阅</a>--%>
    <%--    </div>--%>
    <thead>
    <tr>
        <th lay-data="{radio:true,align:'center'}"></th>
        <th lay-data="{field:'1',sort:true,align:'center'}">id</th>
        <th lay-data="{field:'2',align:'center'}">书名</th>
        <th lay-data="{field:'3',align:'center'}">ISBN</th>
        <th lay-data="{field:'4',align:'center'}">作者</th>
        <th lay-data="{field:'5',align:'center'}">种类</th>
        <th lay-data="{field:'6',align:'center'}">价格</th>
        <th lay-data="{field:'7',align:'center'}">出版人</th>
        <th lay-data="{field:'8',align:'center'}">出版时间</th>
        <th lay-data="{field:'9',align:'center'}">登记时间</th>
        <th lay-data="{field:'10',align:'center'}">借阅状态</th>
        <%--        <th lay-data="{field:'11', toolbar:'#userBar' ,width:200,align:'center', fixed:'right'}">操作</th>--%>
    </tr>
    </thead>
    <tbody>
<%--    <jsp:useBean id="bookList" scope="session" type="com.top.pojo.Book"/>--%>
<%--    <c:set var="isLend" scope="session" value="${bookList.lendState}"/>--%>
    <c:forEach items="${SESSION_BOOK}" var="book">
        <c:set var="isLend" scope="session" value="${book.lendState}"/>
        <tr>

            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.ISBN}</td>
            <td>${book.author}</td>
            <td>${book.type}</td>
            <td>${book.price}</td>
            <td>${book.publisher}</td>
            <td>${book.publishTime}</td>
            <td>${book.registerTime}</td>
<%--            <td>${book.lendState}</td>--%>

            <c:if test="${isLend == '1'}">
                已被借阅
            </c:if>
            <c:if test="${isLend != '1'}">
                可借阅
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
            , height: 'funll-300' //设置高度
            , page: true//开启分页
            , toolbar: '#userToolBar'

        });

        监听头部工具栏事件
        table.on("toolbar(userToolBar)", function (obj) {
            var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
            var data = checkStatus.data;  //获取选中行数据

            if (obj.event === 'borrow') {
                console.log("borrow");
                console.log(data);
                var borrowBookId = data[1];
                var borrowBookName = data[2];
                var alterObj = {
                    borrowBookId: borrowBookId,
                    borrowBookName: borrowBookName
                };
                $.ajax({
                    url: '${ctx}/borrowAndRead/addBorrowAndRead',
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(alterObj),
                    success: function (databack) {
                        console.log(databack);
                        if (databack.deleteUser == "success") {
                            layer.msg("借阅成功")
                        }
                        setTimeout(function () {  //使用  setTimeout（）方法设定定时2000毫秒
                            window.location.reload();//页面刷新
                        }, 2000);
                    }
                })
            }
        });

        //监听行工具事件
        table.on('tool(demo)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'borrow') { //删除

                //layer.msg("删除");
                console.log(data);

                var borrowBookId = data[1];
                var borrowBookName = data[2];

                var alterObj = {
                    borrowBookId: borrowBookId,
                    borrowBookName: borrowBookName
                };

                $.ajax({
                    url: '${ctx}/borrowAndRead/addBorrowAndRead',
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(alterObj),
                    success: function (databack) {
                        console.log(databack);
                        if (databack.deleteUser == "success") {
                            layer.msg("借阅成功")
                        }
                        setTimeout(function () {  //使用  setTimeout（）方法设定定时2000毫秒
                            window.location.reload();//页面刷新
                        }, 2000);
                    }
                })
            }
        });

        var url;
        var mainIndex;


        //打开修改页面
        function openUpdateUser(data) {

            mainIndex = layer.open({
                type: 1,
                title: '修改密码',
                content: $("#saveOrUpdateDiv"),
                area: ['400px', '200px'],
                success: function (index) {
                    //alert("ok");
                    form.val("dataFrm", data);
                    url = "/market_3x/UserServlet?type=update";
                }
            });
        }

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