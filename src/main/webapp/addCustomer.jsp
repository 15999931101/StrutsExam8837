<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hand.dao.impl.*"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="bootstrap.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="bootstrap.min.js"></script>
<script type="text/javascript" src="jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="my.css"></script>
<link href="bootstrap.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="bootstrap.min.js"></script>
<link href="style1.css" type="text/css" rel="stylesheet">

</head>
<body>
	<%
		Connection conn = null;
		conn = GetConnetion.getConnection(); 
		/* Connection conn=ConnectionFactory.getInstance().makeConnection(); */
		String sql = "select address from address";
		PreparedStatement ps1 = conn.prepareCall(sql);
		ResultSet rs = ps1.executeQuery(sql);//执行sql语句
		ArrayList list = new ArrayList();
		while (rs.next()) {
			int i = 0;
			list.add(i++, rs.getString(1));
		}
	%>

<div class="container" style="">
		<div  class="header" style="padding:0px;font-style: normal ;color:#473299;height: 60px;position:relative;">
			<div style="position: absolute;left:10px;"><h1 >8837 林立鹏</h1></div>
			<div style="position: absolute;right:10px;">
			<%
		String flag = "";
		Object obj = session.getAttribute("flag");
		if (obj != null) {
			flag = obj.toString();
		}
		if (flag.equals("login_success")) {
	%>
	<a href="<%=request.getContextPath()%>/logoutAction">退出</a><br/><br/>



	<%
		} else {
	%>
	<a href="<%=request.getContextPath()%>/login.jsp">登录</a><br/><!--  --><br/>
	<%
		}
	%>
			</div> 
		</div>
		
		<div class="content" >
			<div class="col-sm-2" id="left1">
				<div class="btn-group" >
					<button type="button" class="btn btn-default  btn-sm ">Customer管理</button>
					<button type="button" class="btn btn-default  btn-sm dropdown-toggle"
						data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="index.jsp">customer管理</a></li>
					</ul>
				</div>
				<br>

				<div class="btn-group" id="left2">
					<button type="button" class="btn btn-default btn-sm ">F i l m管理</button>
					<button type="button" class="btn btn-default btn-sm dropdown-toggle"
						data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="index.jsp">Film</a></li>
					</ul>
				</div>
			</div>

			<div style=" border-left: 1px solid gray; "  class="col-sm-10" id="right">
			<form action="<%=request.getContextPath()%>/addAction.action" method="post">
			<table border="1" cellpadding="5" cellspacing="0" bgcolor="silver"
				align="center">
				<tr>
					<td colspan="2" align="center" bgcolor="#E7899">添加customer记录</td>
				</tr>
				<tr>
					<td>First_name</td>
					<td><input type="text" name="first_name"></td>
				</tr>
				<tr>
					<td>Last_naem</td>
					<td><input type="text" name="last_name"></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><select id="select1" name="select1">
							<option value="请选择" selected="selected">请选择</option>
							<%
								for (int i = 0; i < list.size(); i++) {
							%>
							<option value="<%=list.get(i).toString()%>">
								<%=list.get(i).toString()%>
							</option>
							<%
								}
							%>
					</select></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" name="添加">
						<input type="reset" name="重置"></td>
				</tr>

			</table>
		
			</div>

		</div>
	</div>
	





</body>
</html>