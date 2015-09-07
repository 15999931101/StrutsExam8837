$(function(){ 

$("#left1").click(function(){ 
$("#right").load("customer.jsp"); 
return false;//禁止按钮提交 
}); 
$("#left2").click(function(){ 
	$("#right").load("FilmIndex.jsp"); 
	return false;//禁止按钮提交 
	});

}); 



$(function(){ 
	
	$("#delete").click(function(){ 
		
	alter("确认要删除"); 
	});
});
