<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">

<head>
<meta charset="UTF-8">
<title>我的课程</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/nmms.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/iconfont/font_0/iconfont.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<style type="text/css">
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

html,body {
	height: 100%;
}

body>.wrap-cc {
	min-height: 100%;
}

.content-cc { /* padding-bottom 等于 footer 的高度 */
	padding-bottom: 80px;
}

.footer-cc {
	width: 100%;
	height: 80px;
	/* margin-top 为 footer 高度的负值 */
	margin-top: -80px;
}
</style>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
	nos = "${nos}";
	console.log(nos);
	$(function() {
		$("#sb").hide();
		$(".arrow").click(function() {
			$(this).parent().next().toggle();
		});

		$(".title-ul>li").on('click', function() {
			console.log($(this).attr("show"));
			$('.' + $(this).attr("show")).show().siblings().hide();

			$(this).addClass('current').siblings().removeClass("current");

		});

		$(".source-modify").on('click', function() {
			$("#user_source").modal("show");
		});

		$("#addres").click(function() {
			$("#user_source").modal("show");
		});

		$("#loadmore")
				.click(
						function() {
							location.href = "${pageContext.request.contextPath}/mycourse/findAll.do?nos="
									+ nos;
						})

		//禁用
		$("button[name='delete']")
				.click(
						function() {
							$
									.ajax({
										type : "post",
										url : "${pageContext.request.contextPath}/mycourse/delete.do",
										data : {
											"id" : $(this).attr("data-id")
										},
										dataType : "json",
										success : function(result) {
											if (result.responseCode == "2001") {
												location.href = "${pageContext.request.contextPath}/mycourse/findAll.do";
											} else {
												$("#errorMsg")
														.tooltip(
																{
																	title : "error",
																	placement : "center",
																	template : "<div class='tooltip errorMsg'>"
																			+ result.message
																			+ "</div>",
																	tigger : "manual",
																}).tooltip(
																"show");
											}
										}
									});
						});

		//修改给默认值

		$("button[name='modify']")
				.click(
						function() {
							$
									.ajax({
										type : "post",
										url : "${pageContext.request.contextPath}/mycourse/selectById.do",
										data : {
											"id" : $(this).attr("data-id")
										},
										dataType : "json",
										success : function(result) {
											if (result.responseCode == "2001") {
												$("#sb").val(
														result.returnObject.id);
												$("#title")
														.val(
																result.returnObject.title);
												$("#choose").html("选择文件");
												$("#course-resource-file").val(
														undefined);
												$("#file-cost-type")
														.val(
																result.returnObject.costType);
												$("#cost_value")
														.val(
																result.returnObject.costNumber);
											} else {
												$("#errorMsg")
														.tooltip(
																{
																	title : "error",
																	placement : "center",
																	template : "<div class='tooltip errorMsg'>"
																			+ result.message
																			+ "</div>",
																	tigger : "manual",
																}).tooltip(
																"show");
											}
										}
									});
						})

		$("#addres").click(function() {
			$("#sb").val("");
			$("#title").val("");
			$("#choose").html("选择文件");
			$("#course-resource-file").val(undefined);
			$("#file-cost-type").val("-1");
			$("#cost_value").val("");
		})

		$("button[name='submit']")
				.click(
						function() {
							//var user = $("#registForm").serializeArray();
							//有 form时的数据处理方式
							//var formData = new FormData($("#registForm")[0]);
							var formData = new FormData();//ajax上传文件时数据的类型
							var f = $("#course-resource-file")[0].files[0];
							console.log(f);
							//没有form时 数据的处理方式
							formData.append("file", f);
							//formData.append('username', $('input[name=nickname]').val());
							formData.append("id", $("#sb").val());
							formData.append("title", $("#title").val());
							formData.append("type", $("#file-cost-type").val());
							formData.append("number", $("#cost_value").val());
							$
									.ajax({
										type : "post",
										url : "${pageContext.request.contextPath}/mycourse/update.do",
										dataType : "json",
										data : formData,
										processData : false,//ajax上传文件必须的属性1
										contentType : false,//ajax上传文件必须的属性2
										success : function(result) {
											if (result.responseCode == "2001") {
												$("#title").val("");
												$("#choose").html("选择文件");
												$("#course-resource-file").val(
														undefined);
												$("#file-cost-type").val("-1");
												$("#cost_value").val("");
												location.href = "${pageContext.request.contextPath}/mycourse/findAll.do";
											} else {
												$("#errorMsg")
														.tooltip(
																{
																	title : "error",
																	placement : "center",
																	template : "<div class='tooltip errorMsg'>"
																			+ result.message
																			+ "</div>",
																	tigger : "manual",
																}).tooltip(
																"show");
											}
											setTimeout(
													function() {
														location.href = "${pageContext.request.contextPath}/mycourse/findAll.do";
													}, 1000);
										},
									});
						});

	});

	function fileUpload(item) {
		$(item).click();
	}

	function fileChange(item) {
		var file = item.files[0]; //获取选中的第一个文件
		$(".file").html(file.name);
		//console.log("file", file.name);
	}
	/* $("#loadmore").click(function(){
		console.log("aaa");
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/mycourse/findAll.do",
				data:{
					"no":"${no}",
				},
				success:function(result){
				location.href="${pageContext.request.contextPath}/mycourse/findAll.do?no=${no}";    				
					
			}
				
			});    	 */
</script>
</head>

<body>
	<input type="text" id="sb">
	<div class="wrap-cc">
		<div class="content-cc">
			<nav class="navbar navbar-default">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<!--  <a class="navbar-brand" href="#">Brand</a> -->
					<img src="${pageContext.request.contextPath}/images/com-logo1.png"
						alt="" width="120" style="margin-right: 20px;">
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="#" class="vertical-middle">免费课程</a>
						</li>
						<li><a href="#" class="vertical-middle">职业路径</a>
						</li>
					</ul>
					<form class="navbar-form navbar-left searchInput"
						style="padding:10px;">
						<div class="form-group">
							<input type="text" class="form-control " placeholder="Search">
						</div>
						<button type="submit" class="btn btn-default ">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</form>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="#"
							class="dropdown-toggle user-active" data-toggle="dropdown"
							role="button"> <img class="img-circle"
								src="${pageContext.request.contextPath}/images/user.jpeg"
								id="userImg"> <span class="caret"></span> </a>
							<ul class="dropdown-menu userinfo cc">
								<li><img
									src="${pageContext.request.contextPath}/images/user.jpeg"
									class="img-circle" alt="">
									<div class="">
										<p>${user.nickname}</p>
										<p>
											金币<span>${user.allGold}</span>&nbsp;积分 <span>${user.allPoint}</span>
										</p>
									</div></li>
								<li><a href="front_mycourse.html"> <i
										class="glyphicon glyphicon-edit"></i>我的课程 </a> <a
									href="${pageContext.request.contextPath}/record/findAll.do"> <i
										class="glyphicon glyphicon-record"></i>积分记录 </a></li>
								<li><a href="#" data-toggle="modal" data-target="#userSet">
										<i class="glyphicon glyphicon-cog"></i>个人设置 </a> <a
									href="javascript:void(0);"><i
										class="glyphicon glyphicon-off"></i>退出登录</a></li>
							</ul></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid --> </nav>
			<div class="container-fluid banner">
				<div class="container banner-mycourse">
					<div class="row">
						<p>user01</p>
					</div>
					<div class="row">
						<img src="${pageContext.request.contextPath}/images/girl.png"
							alt="" width="18">&nbsp;&nbsp; <span></span>&nbsp; <span></span>&nbsp;
						<span>${user.allPoint}</span>&nbsp; <span></span>
					</div>
					<div class="row">这位同学很懒，木有签名的说~~</div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<ul class="title-ul">
						<li class="current" id="cc-course" show='cc-course'>最近学习</li>
						<li class="source" id="cc-source" show='cc-source'>我的资源<span
							id="errorMsg"></span>
						</li>
					</ul>
					<div>
						<!-- 最近学习 -->
						<ul class="mycourse-content cc-course">
							<c:forEach items="${userresource}" var="userresource"
								end="${nos}">
								<li>
									<div class="col-md-1 col-sm-1">
										<strong>${userresource.updateDateYear}</strong>
										<div>${userresource.updateDateDay}</div>
									</div>
									<div class="col-md-11 col-sm-11">
										<span class="circle"></span>
										<div class="row  border-bottom">
											<div class="col-md-3">
												<img
													src="${pageContext.request.contextPath}/images/timg.jpg"
													alt="" height="120" class="mycourseImg">
											</div>
											<div class="col-md-8 mycourse-info">
												<p class="padding-top-25">
													<span> ${userresource.resource.title} </span> <span>更新至3-1</span>
												</p>
												<p class="padding-10">
													<span>已学8%</span> &nbsp;&nbsp;&nbsp;&nbsp; <span>
														用时34分 </span> &nbsp;&nbsp;&nbsp;&nbsp; <span>学习至1-1 01课程背景
													</span>
												</p>
												<div>
													<div>笔记0</div>
													<div>代码0</div>
													<div>问答0</div>
													<c:if test="${userresource.resource.status==0}">
														<div>继续学习</div>
													</c:if>
													<c:if test="${userresource.resource.status==1}">
														<div>资源被禁用</div>
													</c:if>
												</div>
											</div>
										</div>
									</div></li>
							</c:forEach>
							<li>
								<div class="load-more">
									<span id="loadmore">加载更多...</span>
								</div></li>
						</ul>
						<!-- 我的资源 -->
						<ul class="mycourse-content cc-source">
							<li style="text-align: right;margin-top:20px; ">
								<button class="btn btn-primary" id="addres"
									style="width: 100px;">添加资源</button></li>
							<c:forEach items="${resourceall}" var="resource">
								<li>
									<div class="col-md-1 col-sm-1">
										<strong>${resource.createDateYear}</strong>
										<div>${resource.createDateDay}</div>
									</div>
									<div class="col-md-11 col-sm-11">
										<span class="circle"></span>
										<div class="row  border-bottom">
											<div class="col-md-3">
												<img
													src="${pageContext.request.contextPath}/images/timg.jpg"
													alt="" height="120" class="mycourseImg">
											</div>
											<div class="col-md-8 mycourse-info">
												<p class="padding-top-25">
													<span> ${resource.title}</span> <span>
														${resource.fileSize}</span>
												</p>
												<p class="padding-10">
													<span>${resource.fileType}</span> &nbsp;&nbsp;&nbsp;&nbsp;
													<span> </span> &nbsp;&nbsp;&nbsp;&nbsp; <span> </span>
												</p>
												<div>
													<c:if test="${resource.costType==0}">
														<div>积分</div>
														<div>${resource.costNumber}</div>
													</c:if>
													<c:if test="${resource.costType==1}">
														<div>金币</div>
														<div>${resource.costNumber}</div>
													</c:if>
													<div>${resource.user.nickname}</div>
													<div class="nostyle">
														<button class="btn btn-warning source-modify"
															data-id="${resource.id}" name="modify">修改</button>
														<button class="btn btn-danger" data-id="${resource.id}"
															name="delete">删除</button>
													</div>
												</div>
											</div>
										</div>
									</div></li>
							</c:forEach>
							<!-- <li>
								<div class="load-more">
									<span onclick="alert('正在加载...');">加载更多...</span>
								</div>
							</li> -->
						</ul>
					</div>
				</div>
			</div>
			<div class="modal fade" id="userSet" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">个人信息</h4>
						</div>
						<form action="../../php/editPassword.php" class="form-horizontal"
							method="post">
							<div class="modal-body">
								<div class="form-group">
									<label class="col-sm-3 control-label">旧密码：</label>
									<div class="col-sm-6">
										<input class="form-control" type="text" name="password" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">新密码：</label>
									<div class="col-sm-6">
										<input class="form-control" type="password" name="newPassword" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">确认密码：</label>
									<div class="col-sm-6">
										<input class="form-control" type="password" name="rePassword" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">昵称：</label>
									<div class="col-sm-6">
										<input class="form-control" type="text" name="nickname" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">邮箱：</label>
									<div class="col-sm-6">
										<input class="form-control" type="text" name="email" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-info" data-dismiss="modal"
									aria-label="Close">关&nbsp;&nbsp;闭</button>
								<button type="reset" class="btn btn-info">重&nbsp;&nbsp;置</button>
								<button type="submit" class="btn btn-info">确&nbsp;&nbsp;定</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 资源模态框 -->
			<div class="modal fade" id="user_source" tabindex="-1" role="dialog"
				aria-labelledby="mySourceModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="mySourceModalLabel">资源</h4>
						</div>
						<form action="#" class="form-horizontal" method="post">
							<div class="modal-body">
								<div class="form-group">
									<label class="col-sm-3 control-label">标题：</label>
									<div class="col-sm-6">
										<input class="form-control" type="text" id="title"
											name="title" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">资源：</label>
									<div class="col-sm-6">
										<a href="javascript:fileUpload('#course-resource-file');"
											class="file" id="choose">选择文件</a> <input type="file"
											name="course_resource_file" style="display: none;"
											onchange="fileChange(this)" id="course-resource-file" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">资源花费类型：</label>
									<div class="col-sm-6">
										<select class="form-control" id="file-cost-type"
											name="file_cost_type_id">
											<option value="-1">请选择</option>
											<option value="0">积分</option>
											<option value="1">金币</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">花费值：</label>
									<div class="col-sm-6">
										<input class="form-control" type="text" name="cost_value"
											id="cost_value" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-info" data-dismiss="modal"
									aria-label="Close">关&nbsp;&nbsp;闭</button>
								<button type="button" name="submit" class="btn btn-info">确&nbsp;&nbsp;定</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer-cc">
		<div class="footer">
			<div>版权所有： 南京网博</div>
			<div>Copyright © 2017 imooc.com All Rights Reserved | 京ICP备
				13046642号-2</div>
		</div>
	</div>


</body>

</html>