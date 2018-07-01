<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>用户资源管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/nmms.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/back-index.css" />
<script src="${pageContext.request.contextPath}/js/jquery.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
<script
	src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
<style>
.file {
	position: relative;
	display: inline-block;
	background: #D0EEFF;
	border: 1px solid #99D3F5;
	border-radius: 4px;
	padding: 4px 12px;
	overflow: hidden;
	color: #1E88C7;
	text-decoration: none;
	text-indent: 0;
	line-height: 20px;
	width: 100%;
	text-align: center;
}

.file:hover {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}

.file:focus {
	background: #AADFFD;
	border-color: #78C3F3;
	color: #004974;
	text-decoration: none;
}

.width120 {
	min-width: 120px;
}

.width90 {
	min-width: 90px;
}

.width50 {
	min-width: 50px;
}
</style>
<script type="text/javascript">
	$(function() {
		//显示
		$(".resource-detail").on(
				"click",
				function() {
					$('#frame-id', window.parent.document).attr('src',
							'back_resourceDetailSet.html');
				});

		// 显示隐藏查询列表
		$('#show-user-resource-search').click(function() {
			$('#show-user-resource-search').hide();
			$('#upp-user-resource-search').show();
			$('.show-user-resource-search').slideDown(500);
		});
		$('#upp-user-resource-search').click(function() {
			$('#show-user-resource-search').show();
			$('#upp-user-resource-search').hide();
			$('.show-user-resource-search').slideUp(500);
		});

		//按钮的超链接设置函数
		//      myoptions.pageUrl = function(type, page, current) {
		//          return "${pageContext.request.contextPath }/findAll.do?pageNo="
		//                  + page;
		//      };
		//分页回调函数,点击按钮事件
		//myoptions.onPageClicked = function(event, originalEvent, type, page) {
		//ajax回调接收数据成功时再重新初始化分页按钮
		//	console.log("pageNo=", page);
		//};

		//分页,在bootstrap-mypaginator.js中
		//myPaginatorFun("myPages", 1, 5);

		var pages;
		var pageNo;
		if ("${findresource.pages}" == 0) {
			pages = 1;
			pageNo = 1;
		} else {
			pages = "${findresource.pages}";
			pageNo = "${findresource.pageNum}";
		}

		//使用bootstrap分页插件实现分页功能
		var options = {
			bootstrapMajorVersion : 3,//表示当前bootstrap版本号
			currentPage : pageNo,//表示当前页
			totalPages : pages,//一共多少页
			size : "normal",
			pageUrl : function(type, page, current) {
				return "${pageContext.request.contextPath}${resourcesrc}"
						+ page;
			}
		};
		$("#myPages").bootstrapPaginator(options);
		
		//启用-禁用
    	$("input[name='modifyStatus']").click(function(){
    		$.ajax({
    			type:"post",
				url:"${pageContext.request.contextPath}/resource/modifyStatus.do",
				data:{"id":$(this).attr("data-id")},
				dataType:"json",
				success:function(result){
					if(result.responseCode == "2001"){
						location.href="${pageContext.request.contextPath}${resourcesrc}"+pageNo;    				
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
		
		
		
		
		
		$("#doSearch").click(function(){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/resource/searchResource.do",
				data:{
					"title":$("#user-resource-title-search").val(),
					"nickname":$("#user-name-search").val(),
					"beginTime":$("#beginTime").val(),
					"endTime":$("#endTime").val(),
					"status":$("#statusC").val(),
					"filetype":$("#filetype").val(),
				},
				dataType:"json",
				success:function(result){
					if(result.responseCode == "2001"){
						location.href="${pageContext.request.contextPath}/resource/showSelect.do?pageNo=1";    				
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    				setTimeout(function(){
    						location.href="${pageContext.request.contextPath}${resourcesrc}"+pageNo;
    					},1000);
				}
				
			});    	
    	});
		
		
		
		
		
		
		
		

	});
</script>

</head>

<body>
	<div class="panel panel-default" id="userPic">
		<div class="panel-heading">
			<h3 class="panel-title">用户资源管理<span id="errorMsg"></span></h3>
		</div>
		<div>
			<input type="button" value="查询" class="btn btn-success" id="doSearch"
				style="margin: 5px 5px 5px 15px;"  name="doSearch"/> <input type="button"
				class="btn btn-primary" id="show-user-resource-search" value="展开搜索" />
			<input type="button" value="收起搜索" class="btn btn-primary"
				id="upp-user-resource-search" style="display: none;" />
		</div>

		<div class="panel-body">
			<div class="show-user-resource-search" style="display: none;">
				<form class="form-horizontal">
					<div class="form-group">
						<div class="form-group col-xs-6">
							<label for="user-resource-title-search"
								class="col-xs-3 control-label">标题：</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
									id="user-resource-title-search" placeholder="请输入标题" />
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label for="user-name-search" class="col-xs-3 control-label">用户名：</label>
							<div class="col-xs-8">
								<input type="text" class="form-control" id="user-name-search"
									placeholder="请输入用户名" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-group col-xs-6">
							<label class="col-xs-3 control-label">开始日期：</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
								id="beginTime"	placeholder="请输入开始时间:2017-10-10" />
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-3 control-label">结束日期：</label>
							<div class="col-xs-8">
								<input type="text" class="form-control"
								id="endTime"	placeholder="请输入结束时间:2017-10-12" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="form-group col-xs-6">
							<label class="col-xs-3 control-label">状态：</label>
							<div class="col-xs-8">
								<select class="form-control" id="statusC">
									<option value="-1">全部</option>
									<option value="0">启用</option>
									<option value="1">禁用</option>
								</select>
							</div>
						</div>
						<div class="form-group col-xs-6">
							<label class="col-xs-3 control-label">文件类型：</label>
							<div class="col-xs-8">
								<select class="form-control" id="filetype">
									<option value="">全部</option>
									<c:forEach items="${ft}" var="string">
									<option value="${string}">${string}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>

				</form>
			</div>

			<!--  -->
			<div class="show-list">
				<table class="table table-bordered table-hover"
					style="text-align: center;">
					<thead>

						<tr class="text-danger">
							<th class="text-center width50">编号</th>
							<th class="text-center">资源标题</th>
							<th class="text-center">资源相对路径</th>
							<th class="text-center">文件原始名称</th>
							<th class="text-center width50">文件大小</th>
							<th class="text-center width50">文件类型</th>
							<th class="text-center width90">上传时间</th>
							<th class="text-center width50">消费类型</th>
							<th class="text-center width50">消费值</th>
							<th class="text-center width50">上传用户</th>
							<th class="text-center width50">状态</th>
							<th class="text-center width120">操作</th>
						</tr>
					</thead>
					<tbody id="tb">
						<c:forEach items="${findresource.list}" var="resource">
							<tr>
								<td>${resource.id}</td>
								<td>${resource.title}</td>
								<td>${resource.path}</td>
								<td>${resource.originalName}</td>
								<td>${resource.fileSize}</td>
								<td>${resource.fileType}</td>
								<td>${resource.createDate}</td>
								<c:if test="${resource.costType==0}">
									<td>积分</td>
								</c:if>
								<c:if test="${resource.costType==1}">
									<td>金币</td>
								</c:if>
								<td>${resource.costNumber}</td>
								<td>${resource.user.nickname}</td>
								<c:if test="${resource.status==0}">
									<td>启用</td>
								</c:if>
								<c:if test="${resource.status==1}">
									<td>禁用</td>
								</c:if>
								<td class="text-center"><c:if test="${resource.status==0}">
										<input type="button" class="btn btn-danger btn-sm" name="modifyStatus" data-id="${resource.id}" value="禁用" />
									</c:if> <c:if test="${resource.status==1}">
										<input type="button" class="btn btn-success btn-sm" name="modifyStatus" data-id="${resource.id}" value="启用" />
									</c:if> <input type="button"
									class="btn btn-success btn-sm resource-detail" value="详情" /></td>
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

	<div class="modal fade" tabindex="-1" id="courseReourceModal">
		<!-- 窗口声明 -->
		<div class="modal-dialog modal-lg">
			<!-- 内容声明 -->
			<div class="modal-content">
				<!-- 头部、主体、脚注 -->
				<div class="modal-header">
					<button class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">用户资源</h4>
				</div>
				<div class="modal-body text-center">

					<input type="hidden" name="course_id" value="" />
					<div class="row text-right" id="course-resource-id-input">
						<label for="course-resource-id" class="col-xs-4 control-label">章节编号：</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" id="course-resource-id"
								readonly="true" />
						</div>
					</div>
					<br>
					<div class="row text-right">
						<label for="course-resource-title" class="col-xs-4 control-label">章节标题：</label>
						<div class="col-xs-4">
							<input type="text" class="form-control"
								id="course-resource-title" />
						</div>
					</div>
					<br>
					<div class="row text-right">
						<label for="course-resource-context"
							class="col-xs-4 control-label">内容详情：</label>
						<div class="col-xs-4">
							<input type="text" class="form-control"
								id="course-resource-context" />
						</div>
					</div>
					<br>
					<p />
					<div class="row text-right">
						<label class="col-xs-4 control-label">资源：</label>
						<div class="col-xs-4">
							<a href="javascript:fileUpload('#course-resource-file');"
								class="file">选择文件</a> <input type="file"
								name="course_resource_file" style="display: none;"
								onchange="fileChange(this)" id="course-resource-file" />
						</div>
					</div>
					<br />
					<div class="row text-right">
						<label for="course-resource-file-title"
							class="col-xs-4 control-label">资源标题：</label>
						<div class="col-xs-4">
							<input type="text" class="form-control"
								id="course-resource-file-title" />
						</div>
					</div>
					<br>
					<div class="row text-right">
						<label for="file-cost-type" class="col-xs-4 control-label">查看资源花费类型：</label>
						<div class="col-xs-4">
							<select class="form-control" id="file-cost-type"
								name="file_cost_type_id">
								<option value="-1">请选择</option>
								<option value="1">积分</option>
								<option value="2">金币</option>
							</select>
						</div>
					</div>
					<br>
					<div class="row text-right">
						<label for="file-cost-type-val" class="col-xs-4 control-label">花费值：</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" id="file-cost-type-val" />
						</div>
					</div>
					<br>
					<div class="row text-right">
						<label for="resource-type-id" class="col-xs-4 control-label">资源类型：</label>
						<div class="col-xs-4">
							<select class="form-control" id="resource-type-id"
								name="resource_type_id">
								<option value="-1">请选择</option>
								<option value="1">mp4</option>
								<option value="2">pdf</option>
							</select>
						</div>
					</div>
					<br>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary course-reource-btn">确定</button>
					<button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>

</html>