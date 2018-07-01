<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
	table {
		border: 1px solid black;
	}
	
	table tr th {
		border: 1px solid black;
		text-align: center;
	}
	
	table tr td {
		border: 1px solid black;
		text-align: center;
	}
	/*优化分页按钮鼠标样式*/
	.pagination > li > a{
		cursor: pointer;
	}
	.load-more {
	    text-align: center;
	}
	.load-more > .sp {
	    cursor: pointer;
	}
</style>
	<link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<!-- Bootstrap 分页 JavaScript 文件 -->
	<script src="${pageContext.request.contextPath }/js/bootstrap-paginator.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap-mypaginator.js"></script>
	<script src="${pageContext.request.contextPath }/js/template-web.js" charset="utf-8" ></script>
	<script type="text/javascript">
		function showUser(id){
			var data = "";
			if(id == undefined || id == null || id == ""){
				data = $("#myForm").serialize();
				console.log("serialize:", data);
				//var dataArr = $("#myForm").serializeArray();
				//data = dataArr;
				//console.log("serializeArray:", dataArr);
			}else{
				data = "id=" + id;
			}
			$.ajax({
				"url" : "${pageContext.request.contextPath }/user/findUser.do",
				"type" : "post",
				"dataType" : "json",
				"data" : data,//"user.id="+id,
				"success" : function(data){
					//console.log(data);
				},
				"error" : function(){
					console.log("error");
				}
			});
			
			//$("#myForm").submit();
		}
	
	</script>
</head>
<body>
	<form id="myForm">
		<table>
			<tr>
				<td><label>id:</label></td>
				<td><input type="text" name="id" value="" /><br/></td>
			</tr>
			<tr>
				<td><label>username:</label></td>
				<td><input type="text" name="username" value="" /><br/></td>
			</tr>
			<tr>
				<td><label>nickname:</label></td>
				<td><input type="text" name="nickname" value="" /><br/></td>
			</tr>
			<tr>
				<td><label>email:</label></td>
				<td><input type="text" name="email" value="" /><br/></td>
			</tr>
			<tr>
				<td><label>flag:</label></td>
				<td>
					<select name="flag" >
						<option value="-1">全部</option>
						<option value="0">启用</option>
						<option value="1">禁用</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">xxxx</td>
			</tr>
		</table>
	</form>
	<input type="text" name="hobby" value="11" /><br/>
	
	<input type="text" name="hobby" value="22" /><br/>
	<input type="text" name="hobby" value="33" /><br/>
	<a href="${pageContext.request.contextPath }/user/loginOut.do" >loginOut!</a>
	<br/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th>id</th>
				<th>username</th>
				<th>password</th>
				<th>email</th>
				<th>createDate</th>
				<th>status</th>
			</tr>
		</thead>
		<tbody id="userList" >
			<c:forEach items="${userList.list}" var="item" >
				<tr>
					<td><a href="javascript:showUser('${item.id}');">${item.id}</a></td>
					<td>${item.username}</td>
					<td>${item.password}</td>
					<td>${item.email}</td>
					<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${item.flag}</td>
				</tr>
			</c:forEach>
		</tbody>
		<script id="userListTemplate" type="text/html" >
			{{each userList as item}}
				<tr>
					<td><a href="javascript:showUser('{{item.id}}');">{{item.id}}</a></td>
					<td>{{item.username}}</td>
					<td>{{item.password}}</td>
					<td>{{item.email}}</td>
					<td>{{item.createDateStr}}</td>
					{{if item.flag == 0}}
						<td>启用</td>
					{{else}}
						<td>禁用</td>
					{{/if}}
				</tr>
			{{/each}}
		</script>
	</table>
	<br />
	<br />
	<div style="text-align: center;">
		<input id="currentPageInput" type="hidden" value="20" />
		<ul id="myPages" ></ul>
	</div>
	<input type="button" value="查询" onclick="showUser()" />
	<div>瀑布流式分页：</div>
	<div style="text-align: center;">
		<table width="100%" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th>id</th>
					<th>username</th>
					<th>password</th>
					<th>email</th>
					<th>createDate</th>
				</tr>
			</thead>
			<tbody id="userList2" >
				
			</tbody>
		</table>
		<br/>
		<div class="load-more" >
			<input type="hidden" id="loadDataUserIdInput" value="" />
            <span class="sp" onclick="loadData()">加载更多...</span>
        </div>
	</div>
	<br/>
	<br/>
	<br/>
	
	<script>
		//方式1 a标签超链接
		//myoptions.pageUrl = function (type, page, current){
			//console.log(type);
			//console.log(page);
			//console.log(current);
			//return "${pageContext.request.contextPath }/user/findAllUser.do?pageNo=" + page;
		//};
		//$(function(){
			//myPaginatorFun('myPages', '${userList.pageNum}', '${userList.pages}');
		//});
		
		//方式2 点击按钮触发方法 可以使用ajax
		myoptions.onPageClicked = function(event, originalEvent, type, page){
			//console.log('page', page);
			//location.href = "${pageContext.request.contextPath }/user/findAllUser.do?pageNo=" + page;
			ajaxLoadData({'pageNo': page, 'userId': '20'});
		};
		$(function(){
			//myPaginatorFun('myPages', , );
			ajaxLoadData({'pageNo': 1});
		});
		
		function ajaxLoadData(params){
			$.ajax({
				"type": "post",
				"dataType": "json",
				"url" : "${pageContext.request.contextPath }/user/findAllUserByAjax.do",
				"data" : params,
				"success" : function(data){
					var list = data.list;//当前页的数据数组
					//var user = list[0];
					//var date = new Date(user.createDate);
					//date.getFullYear();
					//date.getMonth();
					//date.getDay();
					
					$("#userList").html("");
					var d = {
							"success": {"name":"test","password":"aaa"},
							"userList": list
						};
					var txt = template("userListTemplate", d);
					//console.log("txt", txt);
					$("#userList").html(txt);
					
					var totalPages = data.pages;//新数据的总页数
					if(totalPages == 0){
						totalPages = 1;
					}
					myPaginatorFun("myPages", params.pageNo, totalPages);
				},
				"error" : function(){
					alert("ajax出错");
				}
			});
		}
		
	/*
		//按钮的超链接设置函数
		
 		//myoptions.pageUrl = function(type, page, current) {
 		//	return "${pageContext.request.contextPath }/user/findAllUser.do?pageNo="
 		//			+ page;
 		//};
 		
		//分页回调函数,点击按钮事件
		myoptions.onPageClicked = function(event, originalEvent, type, page) {
			//ajax回调接收数据成功时再重新初始化分页按钮
 			ajaxLoadData({"pageNo":page}, "${pageContext.request.contextPath }/user/findAllUserByAjax.do", page);
		};
		$(function() {
			//首次初始化分页
			//参数:分页标签的id,当前页码,总页数
			console.log("当前页码,总页数",'${page.pageNum}','${page.pages}');
			//myPaginatorFun("myPages", '${userList.pageNum}', '${userList.pages}');
			
			//方式2
			ajaxLoadData({"pageNo":1}, "${pageContext.request.contextPath }/user/findAllUserByAjax.do", 1);
			
			
			//首次加载瀑布流分页
			loadData();
		});
		//ajax回调接收数据成功时再重新初始化分页按钮
		function ajaxLoadData(data, url, page){
			$.ajax({
				"type": "post",
				"dataType": "json",
				"url" : url,
				"data" : data,
				"success" : function(data){
					var list = data.list;//当前页的数据数组
					//console.log(list);
					$("#userList").html("");
					//方式1
					//数据数组进行页面的渲染
					//for ( var i = 0; i < list.length; i++) {
						//var user = list[i];
						//var txt = "<tr>"
							//+ "<td><a href=\"javascript:showUser("+user.id+");\">"+user.id+"</a></td>"
							//+ "<td>"+user.username+"</td>"
							//+ "<td>"+user.password+"</td>"
							//+ "<td>"+user.email+"</td>"
							//+ "<td>"+user.createDate+"</td>"
							//+ "</tr>";
						//$("#userList").append(txt);
					//}
					//方式2
					var d = {
							"success": {"name":"test","password":"aaa"},
							"userList": list
						};
					var txt = template("userListTemplate", d);
					console.log("txt", txt);
					$("#userList").html(txt);
					
					var totalPages = data.pages;//新数据的总页数
					myPaginatorFun("myPages", page, totalPages);
				},
				"error" : function(){
					alert("ajax出错");
				}
			});
		}
		//瀑布流式分页
		var myPageNo = 1;
		$("#loadDataUserIdInput").val("");//清空缓存
		function loadData(){
			var data = {
				"pageNo": 1,
				"userId": $("#loadDataUserIdInput").val(),
				"createDate": ""
			};
			console.log("data===", data);
			$.ajax({
				"type": "post",
				"dataType": "json",
				"url" : "${pageContext.request.contextPath }/user/findAllUserByAjax.do",
				"data" : data,
				"success" : function(data){
					var list = data.list;//当前页的数据数组
					var len = list.length;
					console.log("data.pages=" + data.pages);
					//if(len == 0){
					//	$(".load-more").html("<span>已经到底了...</span>");
					//	return;
					//}
					$("#loadDataUserIdInput").val(list[len - 1].id);
					//方式2
					var d = {
							"success": {"name":"test","password":"aaa"},
							"userList": list
						};
					var txt = template("userListTemplate", d);
					$("#userList2").append(txt);
					if(data.pages == 1){
						$(".load-more").html("<span>已经到底了...</span>");
					}
				},
				"error" : function(){
					alert("error");
				}
			});
		}*/
		
		
	</script>
</body>
</html>
