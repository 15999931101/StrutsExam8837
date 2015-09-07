<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.SQLException"%>
<%-- <%@page import="com.hand.dao.Imp.ConnectionFactory"%> --%>
<%@page import="com.hand.dao.impl.*"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
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

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<%
int pages = request.getParameter("page")==null?0:Integer.parseInt(request.getParameter("page"))+1;
%>
<%
        Connection conn;
		conn = GetConnetion.getConnection(); 
		/* Connection conn=ConnectionFactory.getInstance().makeConnection(); */
	    /* String sql="select   c.first_name,c.last_name,a.address,c.email,c.customer_id,c.last_update FROM customer c, address a WHERE 
	    		c.address_id=a.address_id "; */  
      String sql ="select c.customer_id,c.first_name,a.address,c.last_name , c.email, c.last_update from customer c, address a WHERE "
      +"c.address_id = a.address_id ORDER BY customer_id  DESC LIMIT 12  ";	
		PreparedStatement ps1 = conn.prepareCall(sql);
		ResultSet rs = ps1.executeQuery(sql);//执行sql语句
	%>
<div class="container">
    <div class="row">
        <div class="col-md-12 bg-success">
        <a href="#" class="pull-left"><h1>8837 林立鹏</h1></a>
        <a href="/StrutsExam8837/logoutAction.action" class="pull-right"><h3>Logout</h3></a>
        </div>
    </div>
    
    <div class="row">
    	
        <div class="col-md-3 bg-primary">          
        
            <div class="panel panel-default" style="margin-top: 10px;">
            
                <div class="panel-heading" role="tab" id="headingFisrt">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFirst" aria-expanded="true" aria-controls="collapseFirst">
                            Customer Manage
                        </a>
                        <span class="caret"></span>
                    </h4>
                </div>
                <div id="collapseFirst" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFirst">
                    <div class="panel-body list-unstyled">
                     <li><a href="index.jsp">customer管理</a></li>
                    </div>
                </div>
            </div>
            <div class="panel panel-default" style="margin-top: 10px;">
                <div class="panel-heading" role="tab" id="headingSecond">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSecond" aria-expanded="true" aria-controls="collapseSecond">
                            Film Manage
                        </a>
                        <span class="caret"></span>
                    </h4>
                </div>
                <div id="collapseSecond" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSecond">
                    <div class="panel-body">
                        <ul class="nav-pills list-unstyled">
                           
                        </ul>
                    </div>
                </div>
            </div>
        </div>
   <div class="content">
   <div><pre> <span>客户管理</span>                    
    </pre>
    </div> 
   <hr>
   <div>
      <table style="boder:2px solid">
      <%
			if (request.getAttribute("return_uri") != null) {
		%>
		<input type="hidden" name="return_uri"
			value="<%=request.getAttribute("return_uri")%>">
		<%
			}
		%>
		<tr>
			<td colspan="2" align="left" >客户列表</td>
			<td colspan="3"><a href="addCustomer.jsp" id="add" style="font-size: 18px;">新建</a></td>
		</tr>
		<tr>
		    <td>操作</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Address</td>
			<td>Email</td>
			<td>Customer_id</td>
			<td>LastUpdate</td>
		</tr>

		<%
			while (rs.next()) {
		%>
		<tr>
		    <td><a id="modify" href="updateCustomer.jsp?customer_id=<%=rs.getLong(1)%>&first_name=<%=rs.getString("first_name")%>&last_name=<%=rs.getString("last_name")%>
			&email=<%=rs.getString("email")%>&address=<%=rs.getString("address")%>&last_update=<%=rs.getString("last_update")%> ">编辑</a>
			<a id="delete" href="<%=path%>/deleteAction.action?customer_id=<%=rs.getLong("customer_id")%> ">删除</a>
			</td>
			<td><%=rs.getString("first_name")%></td>
			<td><%=rs.getString("last_name")%></td>
			<td><%=rs.getString("address")%></td>
			<td><%=rs.getString("email")%></td>
			<td><%=rs.getLong(1)%></td>
			<td><%=rs.getString(6)%></td>
			
		</tr>

		<%
			}
		%>
		<%
		try{
			if(rs!=null)
				rs.close();
			if(conn!=null)
				conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		%>


	</table>
</div>
</div>
                
</div>
<%-- <script type="text/javascript">
	var page="<%=pages%>";
	if(page>10){
		page=page%10;
	}
	//alert(page);
    var xmlHttpRequest = null; //声明一个空的对象以接受XMLHttpRequest对象
    function ajaxRequest() {
        if(window.ActiveXObject) {   			//IE的
            xmlHttpRequest = new ActionXObject("Microsoft.XMLHTTP");
        }
        else if(window.XMLHttpRequest) {		//除IE外的
            xmlHttpRequest = new XMLHttpRequest();
        }
        if(xmlHttpRequest != null) {
            xmlHttpRequest.open("GET", "<%=request.getContextPath()%>/AllUaerServlet", true);
            //关联好ajax的回调函数
            xmlHttpRequest.onreadystatechange = ajaxCall;

            //真正向服务器发送请求
            xmlHttpRequest.send();
        }
    }
    function ajaxCall() {
        if(xmlHttpRequest.readyState == 4 ) {  		//完全得到服务器的响应
            if(xmlHttpRequest.status == 200) {		//没有异常
                var text = xmlHttpRequest.responseText;
                //alert(text);
            	var arr = eval(text);
            	var table = document.getElementById("table");
            	for(var i=page*10;i<10+page*10;i++){
                    var tr = document.createElement("tr");
                    tr.innerHTML="<td>"+"<a id='delete' href='/deleteAction?id="+arr[i].customer_id+"'>delete</a>|<a id='update'>update</a>"+"</td>"
                    +"<td>"+arr[i].first_name+"</td>"
                    +"<td>"+arr[i].last_name+"</td>"
                    +"<td>"+arr[i].address+"</td>"
                    +"<td>"+arr[i].email+"</td>"
                    +"<td>"+arr[i].customer_id+"</td>"
                    +"<td>"+arr[i].last_update+"</td>";
                    table.appendChild(tr);
            	}
            	table.addcss("table-striped");
            	alert("ajax over");
        }
        }
    }
    ajaxRequest();
</script> --%>
</body>
</html>
