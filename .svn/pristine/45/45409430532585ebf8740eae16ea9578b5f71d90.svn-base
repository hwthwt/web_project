<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>课程类别管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/nmms.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/back-index.css" />
<script src="${pageContext.request.contextPath }/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap-paginator.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap-mypaginator.js"></script>
<script type="text/javascript" charset="utf-8">
	$(function() {
		$("#doAddCourseType").on("click", function() {
			$("#courseType-id-input").hide();
			$("#modifyType").hide();
			$("#addType").show();
			$("#courseTypeModal").modal("show");
		});
		$(".courseType-btn").on("click", function() {
			$("#courseTypeModal").modal("hide");
		});

		$(".courseType-modify").on("click", function() {
			$("#courseType-id-input").show();
			$("#modifyType").show();
			$("#addType").hide();
			$("#courseTypeModal").modal("show");
		});

		//查询子类别
		/* $(".course-type-detail").on(
				"click",
				function() {
					$('#frame-id', window.parent.document).attr('src',
							'back_courseTypeSet.html');
				}); */
		//返回父类别列表页面
		/* $("#back").on(
				"click",
				function() {
					$('#frame-id', window.parent.document).attr('src',
							'back_courseTypeSet.html');
				}); */

		//按钮的超链接设置函数
		//      myoptions.pageUrl = function(type, page, current) {
		//          return "${pageContext.request.contextPath }/findAll.do?pageNo="
		//                  + page;
		//      };
		//分页回调函数,点击按钮事件
		// myoptions.onPageClicked = function(event, originalEvent, type, page) {
		//ajax回调接收数据成功时再重新初始化分页按钮
		//   console.log("pageNo=", page);
		// };

		//分页,在bootstrap-mypaginator.js中
		// myPaginatorFun("myPages", 1, 5);
		
		var pages;
		var pageNo;
		if ("${findcoursetype.pages}" == 0) {
			pages = 1;
			pageNo = 1;
		} else {
			pages = "${findcoursetype.pages}";
			pageNo = "${findcoursetype.pageNum}";
		}

		//使用bootstrap分页插件实现分页功能
		var options = {
			bootstrapMajorVersion : 3,//表示当前bootstrap版本号
			currentPage : pageNo,//表示当前页
			totalPages : pages,//一共多少页
			size : "normal",
			pageUrl : function(type, page, current) {
				return "${pageContext.request.contextPath}${coursesrc}"
						+ page;
			}
		};
		$("#myPages").bootstrapPaginator(options);
		$("#showParentId").val("${pd}");
		//启用-禁用
    	$("input[name='modifyStatus']").click(function(){
    		$.ajax({
    			type:"post",
				url:"${pageContext.request.contextPath}/courseTypeSet/modifyStatus.do",
				data:{"id":$(this).attr("data-id")},
				dataType:"json",
				success:function(result){
					if(result.responseCode == "2001"){
						location.href="${pageContext.request.contextPath}${coursesrc}"+pageNo;    				
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
				}
    		});
    	});
		
		
		$("input[name='showModify']").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/courseTypeSet/findById.do",
    			data:{"id":$(this).attr("data-id")},
    			dataType:"json",
    			success:function(result){
    				if(result.responseCode == "2001"){
    					$("#courseType-id").val(result.returnObject.id);
    					$("#courseType-name").val(result.returnObject.typeName);
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    			}
    		});
    	
    	});
		//实现修改功能
    	$("#modifyType").click(function(){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/courseTypeSet/modifyType.do",
				data:{
					"id":$("#courseType-id").val(),
					"typeName":$("#courseType-name").val(),
				},
				dataType:"json",
				success:function(result){
					if(result.responseCode == "2001"){
						location.href="${pageContext.request.contextPath}${coursesrc}"+pageNo;    				
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    				setTimeout(function(){
    						location.href="${pageContext.request.contextPath}${coursesrc}"+pageNo;
    					},1000);
				}
				
			});    	
    	});
		
		
		
		//添加功能
    	$("#addType").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/courseTypeSet/addType.do",
    			data:{
					"typeName":$("#courseType-name").val(),
				},
    			dataType:"json",
    			success:function(result){
    				console.log(result.responseCode);
    				if(result.responseCode == "2001"){
						location.href="${pageContext.request.contextPath}${coursesrc}"+pageNo;    				
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    				setTimeout(function(){
    						location.href="${pageContext.request.contextPath}${coursesrc}"+pageNo;
    					},1000);
    			}
    		});
    	});
		$("#doAddCourseType").click(function(){
			$("#courseType-name").val("");
		});
		
			
		//隐藏返回上级列表
		if ($("#showParentId").val()==null||$("#showParentId").val()=="") {
			$("#back").hide();
		}else{
			$("#back").show();
		}
		
		
		
		//查询子类别
    	$(".course-type-detail").click(function(){
    	location.href="${pageContext.request.contextPath }/courseTypeSet/findSon.do?parentId="+$(this).attr("data-id");
    	});
    	
    	
    	//返回上一级
    	$("#back").click(function(){
    	location.href="${pageContext.request.contextPath }/courseTypeSet/findFather.do";
    	});
    	
    	});
    	
</script>


</head>

<body>

	<!-- 课程类别管理 -->
	<div class="panel panel-default" id="departmentSet">
		<div class="panel-heading">
			<h3 class="panel-title">课程类别管理&nbsp;&nbsp;&nbsp;<span id="errorMsg"></span></h3>
		</div>
		<div class="panel-body">
			<input type="button" value="添加课程类别" class="btn btn-primary"
				id="doAddCourseType" />
			<!-- courseType父类别id,没有时,移除此按钮 -->
			<input type="hidden" name="parent_id" id="showParentId"   /> <input
				type="button" value="返回上级列表" class="btn btn-success" id="back"
				style="float: right;" /> <br> <br>
			<div class="modal fade" tabindex="-1" id="courseTypeModal">
				<!-- 窗口声明 -->
				<div class="modal-dialog modal-lg">
					<!-- 内容声明 -->
					<div class="modal-content">
						<!-- 头部、主体、脚注 -->
						<div class="modal-header">
							<button class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">添加类别</h4>
						</div>
						<div class="modal-body text-center">
							<div class="row text-right" id="courseType-id-input">
								<label for="courseType-id" class="col-sm-4 control-label">编号：</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="courseType-id"
										readonly="true" />
								</div>
							</div>
							<br>
							<div class="row text-right">
								<label for="courseType-name" class="col-sm-4 control-label">类别名称：</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="courseType-name" />
								</div>
							</div>
							<br>
						</div>
						<div class="modal-footer">
							<button class="btn btn-primary courseType-btn" id="modifyType">确定</button>
							<button class="btn btn-primary courseType-btn" id="addType">确定</button>
							<button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>
			<div class="show-list">
				<table class="table table-bordered table-hover"
					style='text-align: center;'>
					<thead>
						<tr class="text-danger">
							<th class="text-center">编号</th>
							<th class="text-center">名称</th>
							<th class="text-center">状态</th>
							<th class="text-center">操作</th>
						</tr>
					</thead>
					<tbody id="tb">
						<c:forEach items="${findcoursetype.list}" var="courseType">
							<tr>
								<td>${courseType.id}</td>
								<td>${courseType.typeName}</td>
								<td><c:if test="${courseType.status==0}">启用</c:if> 
								<c:if test="${courseType.status==1}">禁用</c:if>
								</td>
								<td><input type="button"
									class="btn btn-warning btn-sm courseType-modify" name="showModify" value="修改" data-id="${courseType.id }" />
									<c:if test="${courseType.status==1 }">
										<input type="button" class="btn btn-success btn-sm"
											name="modifyStatus" data-id="${courseType.id }" value="启用" />
									</c:if> <c:if test="${courseType.status==0 }">
										<input type="button" class="btn btn-danger btn-sm"
											name="modifyStatus" data-id="${courseType.id}" value="禁用" />
									</c:if>
									<c:if test="${courseType.status==1 }"></c:if>
										<input type="button" class="btn btn-success btn-sm course-type-detail"  name="findNot" data-id="${courseType.id }"  value="查询子类别" />
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<!-- 分页 -->
			<div style="text-align: center;">
				<ul id="myPages"></ul>
			</div>
		</div>
	</div>
</body>

</html>