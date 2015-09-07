<%@page import="org.apache.struts2.ServletActionContext"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%String path = request.getContextPath(); 
String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="my.js"></script>
<link href="bootstrap.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<link href="style1.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="my.js"></script>
</head>
<body>
	<div class="container" style="">
		<div class="header"
			style="padding: 0px; font-style: normal; color: #473299; height: 60px; position: relative;">
			<div style="position: absolute; left: 10px;">
				<h1>8837 林立鹏</h1>
			</div>
			<div style="position: absolute; right: 10px;">
				用户：<%=session.getAttribute("username")%>
				<%-- <%=ServletActionContext.getRequest().getSession().getAttribute("username")%> --%>
				</div>
		</div>

		<div class="content">
			<div class="col-sm-2" id="left1">
				<div class="btn-group">
					<button type="button" class="btn btn-default  btn-sm ">Customer管理</button>
					<button type="button"
						class="btn btn-default  btn-sm dropdown-toggle"
						data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="customer.jsp">customer管理</a></li>
					</ul>
				</div>
				<br>

				<div class="btn-group" id="left2">
					<button type="button" class="btn btn-default btn-sm ">F i
						l m管理</button>
					<button type="button"
						class="btn btn-default btn-sm dropdown-toggle"
						data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="customer.jsp">Film</a></li>
					</ul>
				</div>
			</div>

			<div style="border-left: 1px solid gray;" class="col-sm-10"
				id="right">
				<form action="<%=path%>/updateAction.action" method="post">
					<table border="1" cellpadding="5" cellspacing="0" bgcolor="silver"
						align="center" width="500">
						<tr>
							<td colspan="4" align="center" bgcolor="#E7899"><a
								href="index.jsp">返回主页面</a></td>
						</tr>

						<tr>
							<td colspan="2" align="center" bgcolor="#E7899">更新customer记录</td>
						</tr>
						<tr style="">
							<td>Customer_id</td>
							<td width="300"><input type="text" name="customer_id"
								value="<%=request.getParameter("customer_id")%>"
								readonly="readonly">不可修改</td>
						</tr>
						<tr>
							<td>firt_name</td>
							<td width="300"><input type="text" name="first_name"
								value="<%=request.getParameter("first_name")%>"></td>
						</tr>
						<tr>
							<td>last_name</td>
							<td width="300"><input type="text" name="last_name"
								value="<%=request.getParameter("last_name")%>"></td>
						</tr>
						<tr>
							<td>email</td>
							<td width="300"><input type="text" name="email"
								value="<%=request.getParameter("email")%>"></td>

						</tr>
						<tr>
							<td>address</td>
							<td width="300"><input type="text" name="address"
								value="<%=request.getParameter("address")%>"></td>

						</tr>
						<tr>
							<td>last_update</td>
							<td width="300"><input type="text" name="last_update"
								value="<%=request.getParameter("last_update")%>"></td>

						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								name="添加"> <input type="reset" name="重置"></td>
						</tr>

					</table>
			</div>

		</div>
	</div>


</body>
</html>