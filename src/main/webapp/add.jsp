<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page
	import="java.util.List,java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
<title>Insert title here</title>
<script type="text/javascript">
	function check(form) {
		//document.forms.form1.username.value取得form1中Username的值 并判断是否为空
		if (document.forms.LoginForm.first_name.value == "") {
			//如果 为""则弹出提示
			alert("请输入first_name！");
			//将输入焦点定位到没有输入的地方
			document.forms.LoginForm.first_name.focus();
			//返回错误
			return false;
		}else if (document.forms.LoginForm.password.value == "") {
			//如果 为""则弹出提示
			alert("请输入last_name！");
			//将输入焦点定位到没有输入的地方
			document.forms.LoginForm.last_name.focus();
			//返回错误
			return false;
		}else if (document.forms.LoginForm.email.value == "") {
			//如果 为""则弹出提示
			alert("请输入email！");
			//将输入焦点定位到没有输入的地方
			document.forms.LoginForm.email.focus();
			//返回错误
			return false;
		}else if (document.forms.LoginForm.address.value == "") {
			//如果 为""则弹出提示
			alert("请输入address！");
			//将输入焦点定位到没有输入的地方
			document.forms.LoginForm.address.focus();
			//返回错误
			return false;
		}
		
	}
	</script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2 table-bordered" style="margin-top: 20%;background-color: #f8f8f8;">
            <div class="row table-bordered"><h3 class="col-md-offset-1"> 基本信息</h3></div>
            <form class="form-horizontal" style="margin-top: 10px" action="<%= request.getContextPath() %>/AddServlet" method="post" name="LoginForm">
                <div class="form-group has-feedback">
                    <label class="col-sm-2 control-label" for="first_name">first_name</label>
                    <div class="col-sm-2">
                        <input class="form-control" type="text" id="first_name" placeholder="first_name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="last_name">last_name</label>
                    <div class="col-sm-2">
                        <input class="form-control" type="password" id="last_name" placeholder="last_name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="email">email</label>
                    <div class="col-sm-2">
                        <input class="form-control" type="email" id="email" placeholder="email">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="address">address</label>
                    <div class="col-sm-2">
                        <select name="address">
						<%
							List<String> list = (List<String>) request.getAttribute("list");
							for (int i = 0; i < list.size(); i++) {
						%>
						<option><%=list.get(i)%></option>
						<%
							}
						%>

				</select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 col-sm-offset-2">
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