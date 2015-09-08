<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页面</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/index_style.css" />
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
function check() {
					//document.forms.form1.username.value取得form1中Username的值 并判断是否为空
					if (document.forms.addCusForm.input_one.value == "") {
						//如果 为""则弹出提示
						alert("请输入first_name！");
						//将输入焦点定位到没有输入的地方
						document.forms.addCusForm.input_one.focus();
						//返回错误
						return false;
					}else if (document.forms.addCusForm.input_two.value == "") {
						//如果 为""则弹出提示
						alert("请输入last_name！");
						//将输入焦点定位到没有输入的地方
						document.forms.addCusForm.input_two.focus();
						//返回错误
						return false;
					}else if (document.forms.addCusForm.input_three.value == "") {
						//如果 为""则弹出提示
						alert("请输入email！");
						//将输入焦点定位到没有输入的地方
						document.forms.addCusForm.input_three.focus();
						//返回错误
						return false;
					}else if (document.forms.addCusForm.input_four.value == "") {
						//如果 为""则弹出提示
						alert("请输入address！");
						//将输入焦点定位到没有输入的地方
						document.forms.addCusForm.input_four.focus();
						//返回错误
						return false;
					}
				}
	$(document).ready(
			function() {
				var arr;
				function ajaxForData() {
					$.ajax({
						method : "Post",
						url : "/StrutsExam8837/getAddress.action",
						async : true,
						data : "",
						dataType : 'json',
						success : function(data) {
							//alert("Data Getted: " + data);
							arr = data;
							var op = '';
			                for(var index=0;index<arr.length;index++){
			                    op = "<option>"+ arr[index] + "</option>";
			                    $("#input_four").append(op);
			                }
						},
						/* timeout : 800, */
						error : function(info, status, error) {
							alert('Error: ' + info.status + ' - ' + error);
						}
					});
				}
				;
				<%
				if(session.getAttribute("user")!=null){
					%>
					ajaxForData();
				
					<%
				}
				%>
				
				 
				
			});
</script>
</head>
<body>
	<div class="body_div">
		<!-- 页面导航栏区域 -->
		<div class="body_title">
			<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<h3>8837 林立鹏</h3>
				</div>
				<div class="collapse navbar-collapse">
					<div class="user_style dropdown">
						<a href="#" class="dropdown-toggle user_a_style"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"> <%=session.getAttribute("user")%> <span
							class="caret"></span></a>
						<ul class="dropdown-menu dropdown-menu-right"
							aria-labelledby="uerdm">
							<li role="presentation"><a
								href="<%=request.getContextPath()%>/Exit.action"
								onclick="return confirm('确定要退出系统吗？')">退出系统</a></li>
						</ul>

					</div>
					<div class="img_style">
						<span><img src="<%=request.getContextPath()%>/image/admin.jpg" /></span>
					</div>
				</div>
			</div>
			</nav>
		</div>
		<!--主要内容-->
		<div class="body_content">
			<div class="row table-bordered">
				<!--左侧菜单栏-->
				<div class="col-lg-2">
					<div class="row">
						<div class="list-group">
							<a href="#" class="list-group-item active"> 应用设置 <span
								class="glyphicon glyphicon-chevron-right pull-right"></span>
							</a> <a href="#" class="list-group-item"> 管理设置 <span
								class="glyphicon glyphicon-chevron-right pull-right"></span>
							</a>
						</div>
					</div>
				</div>
				<!--右侧主界面-->
				<div class="col-lg-10 table-bordered">
					<!--创建客户-->
					<div class="row">
						<div class="col-lg-9">
							<h3>
								<strong>创建客户</strong>
							</h3>
						</div>
					</div>
					<hr />
					<div class="row">
						<div class="col-lg-9">
							<h4>基本信息</h4>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-9">
							<form class="form-horizontal col-lg-9 table-bordered" id="addCusForm"
								name="addCusForm.action" method="POST" action="addAction.action">
								<div class="form-group form-group-md">
									<label class="col-md-2 control-label" for="input_one">FirstName<span
										style="color: red">*</span></label>
									<div class="col-md-10">
										<input class="form-control" type="text" id="input_one"
											name="first_name" placeholder="FirstName">
									</div>
								</div>
								<div class="form-group form-group-md">
									<label class="col-md-2 control-label" for="input_two">LastName<span
										style="color: red">*</span></label>
									<div class="col-md-10">
										<input class="form-control" type="text" id="input_two"
											name="last_name" placeholder="LastName">
									</div>
								</div>
								<div class="form-group form-group-md">
									<label class="col-md-2 control-label" for="input_three">Email</label>
									<div class="col-md-10">
										<input class="form-control" type="email" id="input_three"
											name="email" placeholder="Email">
									</div>
								</div>
								<div class="form-group form-group-md">
									<label class="col-md-2 control-label" for="input_four">Address<span
										style="color: red">*</span></label>
									<div class="col-md-10">
										<select class="form-control input-lg" id="input_four"
											name="address">
										</select>
									</div>
								</div>
								<div class="form-group form-group-md">
									<div class="col-md-10 text-center">
										<button class="btn btn-info" onclick="check()">提交</button>
										<button type="reset" class="btn">取消</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
</body>
</html>