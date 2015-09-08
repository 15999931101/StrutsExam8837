<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/login_style.css"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<style>
       
        form{
        	margin-top: 10px;
        }
    </style>
    <script type="text/javascript">
	function check(form) {
		//document.forms.form1.username.value取得form1中Username的值 并判断是否为空
		if (document.forms.LoginForm.username.value == "") {
			//如果 为""则弹出提示
			alert("请输入用户名！");
			//将输入焦点定位到没有输入的地方
			document.forms.LoginForm.username.focus();
			//返回错误
			return false;
		}else if (document.forms.LoginForm.password.value == "") {
			//如果 为""则弹出提示
			alert("请输入密码！");
			//将输入焦点定位到没有输入的地方
			document.forms.LoginForm.password.focus();
			//返回错误
			return false;
		}
	}
</script>
</head>
<body  >
<div class="container pull-left">
    <a href="#"><h1>8837 林立鹏</h1></a>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2 table-bordered" style="margin-top: 20%;background-color: #f8f8f8;">
            <div class="row table-bordered"><h3 class="col-md-offset-1">电影租赁管理系统</h3></div>
            <form class="form-horizontal" action="<%=path%>/loginAction.action" method="post" name="LoginForm">
                <% if(request.getAttribute("return_uri") != null) { %>
			<input type="hidden" name="return_uri" value="<%= request.getAttribute("return_uri") %>">
		<% } %>
                <div class="form-group has-feedback">
                    <label class="col-sm-3 control-label" for="account">账号:</label>
                    <div class="col-sm-5">
                        <input name="username" class="form-control" type="text" id="account" placeholder="账号">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="password">密码:</label>
                    <div class="col-sm-5">
                        <input name="password" class="form-control" type="password" id="password" placeholder="密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-3 col-sm-offset-3">
                        <input type="submit" id="submit" value="提交">
                        <input type="reset" id="reset" value="重置">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>