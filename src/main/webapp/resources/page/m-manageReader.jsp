<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<c:set var="webRoot" value="<%=basePath%>"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>读者管理</title>
    <script type="text/javascript" src="${webRoot}/resources/layui/layui.js"></script>
    <link rel="stylesheet" href="${webRoot}/resources/layui/css/layui.css">
    <script type="text/javascript" src="${webRoot}/resources/jquery-3.4.1/jquery-3.4.1.js"></script>

</head>
<body>

<%--示例开始--%>
<div class="layui-container" style="margin-top: 100px;">
    <form class="layui-form" id="form_test" action="{% url 'test' %}" method="post">

        <div class="layui-form-item">
            <label class="layui-form-label">Id</label>
            <div class="layui-input-inline">
                <input type="text" id="Eid"   autocomplete="off" readonly="true">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-inline">
                <input type="text" id="Ename"   autocomplete="off" readonly="true">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <select id="Egender" lay-filter="aihao">
                    <option value="0" selected="">男</option>
                    <option value="1" >女</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">联系方式</label>
            <div class="layui-input-inline">
                <input type="text" id="Etele"  placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-inline">
                <input type="text" id="Eaddress"  placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">

            <div class="layui-input-block">
                <!--不要用<button>-->
                <!--<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>-->

                <input class="layui-btn" id="input_test" data-method="input_submit" type="button" value="确定">
            </div>
        </div>
    </form>
</div>
<script src="../static/layui/layui.all.js"></script>
<script>
    //一般直接写在一个js文件中

</script>
<%--示例结束--%>


<script>
    function editUser(data) {
        var $ = layui.jquery;
        var layer = layui.layer;
        var table = layui.table;//加载表格模块
        var form = layui.form;

        var obj = data;
        console.log(obj);

        // layer.open({
        //     type: 1,
        //     title: '修改信息',
        //     content: $("#editForm"),
        //     area: ['400px', '200px'],
        //     success: function (index) {
        //         alert("修改成功");
        //     }
        // });

        layui.use(['layer', 'form'], function () {
            var layer = layui.layer
                , form = layui.form
                , $ = layui.jquery;

            var active = {
                input_submit: function () {
                    layer.open({
                        type: 1
                        , offset: auto
                        , content: '确定提交吗？'
                        , btn: ['确定', '取消']
                        , yes: function () {
                            //使用 ajax 进行提交。 代码还未写出。



                            layer.closeAll();
                        },
                        success: function (layero, index) {
                            //直接设置某个id 的值
                            $("#Eid").val(obj[1]);
                            $('#Ename').val(obj[2]);
                            $('#Egender').val(obj[2]);
                            $('#Etele').val(obj[2]);
                            $('#Eaddress').val(obj[2]);
                        }
                    });
                }
            };

            $('#input_test').on('click', function () {
                var othis = $(this), method = othis.data('method');
                active[method] ? active[method].call(this, othis) : '';
            });

        });
    }
</script>

<!-- 搜索条件开始 -->
<div class="menu">
    <fieldset class="layui-elem-field layui-field-title"
              style="margin-top: 20px;">
        <legend>
            <h1 style="color: green">读者管理</h1>
        </legend>
    </fieldset>
    <form class="layui-form" action="/user/ListAllStu?name=#searchname">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">
                    <p1 style="font-size:16px;">姓名：</p1>
                </label>
                <div class="layui-input-inline">
                    <input class="layui-input" name="name" id="searchname">
                </div>
                <input type="submit" class="layui-btn layui-btn-danger" value="查询">
                <!-- <button type="button" class="layui-btn" lay-event="add" onclick="openAddUser()">添加学生</button> -->

            </div>
        </div>
    </form>
</div>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table lay-filter="demo" id="demo">
    <!-- <div id="userToolBar" style="display:none;" >
          <button type="button" class="layui-btn" lay-event="add" >添加用户</button>
    </div> -->
    <div id="userBar" style="display:none;">
        <a class="layui-btn layui-btn-xs" lay-event="edit">重置密码</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </div>
    <thead>
    <tr>
        <th lay-data="{field:'1',sort:true,align:'center'}">Id</th>
        <th lay-data="{field:'2',align:'center'}">姓名</th>
        <th lay-data="{field:'3',align:'center'}">性别</th>
        <th lay-data="{field:'4',align:'center'}">联系方式</th>
        <th lay-data="{field:'5',align:'center'}">地址</th>
        <th lay-data="{field:'6', toolbar:'#userBar' ,width:200,align:'center', fixed:'right'}">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${SESSION_STU}" var="user">
        <tr>
            <td>${user.Id}</td>
            <td>${user.name}</td>
            <td>${user.gender}</td>
            <td>${user.apartment}</td>
            <td>${user.address}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<!-- 数据表格结束 -->
<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="editForm">
    <form class="layui-form " action="" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">重置密码：</label>
            <div class="layui-input-inline">
                <input type="text" name="1" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="submit" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release"
                        lay-filter="doSubmit" lay-submit="">提交
                </button>
                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh">重置
                </button>
            </div>
        </div>
    </form>
</div>
<!-- 添加和修改的弹出层结束 -->
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

        //监听头部工具栏事件
        // table.on("toolbar(userToolBar)", function (obj) {
        //     var data = obj.data; //获得当前行数据
        //     switch (obj.event) {
        //         case 'edit':
        //             alert("edit");
        //
        //             editUser();
        //
        //             break;
        //     }
        //     ;
        // })

        //监听行工具事件
        table.on('tool(demo)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if (layEvent === 'del') { //删除

                //layer.msg("删除");
                console.log(data[1]);
                var name = data[1];

                var alterObj = {
                    id: name
                };
                $.ajax({
                    url: '${ctx}/user/deleteUser',
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(alterObj),
                    success: function (databack) {
                        console.log(databack);
                        if (databack.deleteUser == "success") {
                            layer.msg("授权成功")
                        }
                        setTimeout(function () {  //使用  setTimeout（）方法设定定时2000毫秒
                            window.location.reload();//页面刷新
                        }, 2000);
                    }
                })
            } else if (layEvent === 'edit') { //编辑
                //layer.msg("编辑");
                //openUpdateUser();
                console.log(data)
                console.log(data[1])
                var id = data[1];
                var alterObj = {
                    id: id,
                    password: "000000",
                }

                editUser(data);

                $.ajax({
                    url:'${ctx}/user/modify',
                    type:'post',
                    contentType:'application/json',
                    data:JSON.stringify(alterObj),
                    success:function (databack) {
                        console.log(databack)
                        if(databack.update=="success"){
                            layer.msg("重置成功")
                        }
                        setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
                            window.location.reload();//页面刷新
                        },2000);
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