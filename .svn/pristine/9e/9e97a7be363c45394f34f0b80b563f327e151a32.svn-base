<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>课程详情</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/iconfont/font_0/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/nmms.css" />
    <style>
    html,
    body {
        height: 100%;
    }

    body>.wrap-cc {
        min-height: 100%;
    }

    .content-cc {
        /* padding-bottom 等于 footer 的高度 */
        padding-bottom: 80px;
    }

    .footer-cc {
        width: 100%;
        height: 80px;
        /* margin-top 为 footer 高度的负值 */
        margin-top: -80px;
    }
    </style>
    <script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    <script>
    $(function() {

		
        $(".title-ul>li").on('click', function() {
            console.log($(this));
            $(this).addClass('current').siblings().removeClass("current");
        })

        $('.addPraise').bind('click', function(){
            alert('点赞成功');
        });
        
        $("#add").click(function(){
        			$.ajax({
    				type:"post",
    				url:"${pageContext.request.contextPath}/chapterList/add.do",
    				data:{"text":$("#text").val(),
						"resourceId":${resourceId }
						},
    				dataType:"json",
    				success:function(result){
    					console.log(result.responseCode);
    					if(result.responseCode == "2001"){
    					 location.href="${pageContext.request.contextPath}/frontcourseDetail.do";
    					}else{
    						$("#errorMsg").tooltip({
    							title:"error",
    							placement:"center",
    							template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    							tigger:"manual",
    						}).tooltip("show");
    					}
    						setTimeout(function(){
    							location.href="${pageContext.request.contextPath}/frontcourseDetail.do";
    						},1000);
    					}
    				});
        	});
        
        $("i[name='DianZan']").click(function(){
        			var i = $(this).next().html();
        			i=parseInt(i)+1;
        			var $e = $(this);
        	$.ajax({
        			type : "post",
					url : "${pageContext.request.contextPath}/chapterList/dianZan.do",
					dataType : "json",
					data:{"commentId":$(this).attr("ComId"),
						"userId":$(this).attr("UId")},
    				dataType:"json",
					success : function(result) {
						if(result==1){
						$e.next().html(i);
						}
					}
				});
        	})
    })
    </script>
</head>

<body>
    <div class="wrap-cc">
        <div class="content-cc">
            <nav class="navbar navbar-default">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="front_index.html" class="vertical-middle"><i class="glyphicon glyphicon-menu-left"></i></a></li>
                        <li class="vertical-middle text-color"> ${title } </li>
                        <li class="vertical-middle little-title">${chapterId}-1 ${name}</li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                        <img class="img-circle" src="${pageContext.request.contextPath }/images/user.jpeg" id="userImg">
                        <span class="caret"></span>
                    </a>
                            <ul class="dropdown-menu userinfo cc">
                                <li>
                                    <img src="${pageContext.request.contextPath }/images/user.jpeg" class="img-circle" alt="">
                                    <div class="">
                                        <p>我叫细倩倩</p>
                                        <p>金币<span>236</span>&nbsp;积分 <span>0</span></p>
                                    </div>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath }/front_mycourse.html">
                                    <i class="glyphicon glyphicon-edit"></i>我的课程
                                </a>
                                    <a href="${pageContext.request.contextPath }/front_record.html">
                                    <i class="glyphicon glyphicon-record"></i>积分记录
                                </a>
                                </li>
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#userSet">
                                    <i class="glyphicon glyphicon-cog"></i>个人设置
                                </a>
                                    <a href="javascript:void(0);"><i class="glyphicon glyphicon-off"></i>退出登录</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- /.container-fluid -->
            </nav>
            <!-- 视频播放 -->
            <div class="container-fluid padding-0 bgColor">
                <video style="width: 100%; height:470px;" controls="controls">
                    <source src="${pageContext.request.contextPath }/upload${resourceUrl}">
                </video>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-9">
                        <ul class="title-ul">
                            <li class="current">评论</li>
                            <li>问答</li>
                            <li>笔记</li>&nbsp;&nbsp;&nbsp;&nbsp;<span id="errorMsg">${msg }</span>
                        </ul>
                        <div class="row comment-area">
                            <div class="col-md-1"><img src="images/user.jpeg" alt="" class="img-circle " width="35" height="35"></div>
                            <div class="col-md-9">
                                <textarea id="text" cols="70" rows="10" style="resize:none;"></textarea>
                            </div>
                            <div class="col-md-2 col-md-offset-10 ">
                                <button id = "add" class="btn btn-success">发表评论</button>
                            </div>
                        </div>
                        <ul id="commentDetail" class="margin-bottom-90">
                           <c:forEach items="${comments}" var="Comment"  >
                            <li>
                                <div class="row comment-area">
                                    <div class="col-md-1"><img src="${pageContext.request.contextPath }/images/user.jpeg" alt="" class="img-circle " width="35" height="35"></div>
                                    <div class="col-md-10 comment-info">
                                        <div class="col-md-12">${Comment.user.nickname}</div>
                                        <div class="col-md-12">${Comment.context}</div>
                                        <div class="col-md-12">
                                            <div>
                                                时间：<span>${Comment.createDate}</span>
                                            </div>
                                            <div>
                                                <span>举报</span>
                                                <span><i name="DianZan" ComId="${Comment.id}" UId="${Comment.user.id}" class="icon iconfont icon-zan"></i><span id="${Comment.id}">${Comment.count}</span></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            </c:forEach>
                            <li>
                                <div class="load-more">
                                    <span onclick="alert('正在加载...');">加载更多...</span>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <div class="row teacher-msg">
                            <div class="col-md-12 course-title padding-30">推荐课程</div>
                        </div>
                        <c:forEach items="${courses}" var="courseSet">
                        <div id="findCourses" class="row  recommd-course">
                            <div class="col-md-4">
                                <img src="${pageContext.request.contextPath }/upload${courseSet.cover_image_url}" alt="">
                            </div>
                            <div class="col-md-8">
                                <div><a href="front_course.html">${courseSet.course_name }</a></div>
                                <div>${courseSet.course_info}</div>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-cc">
        <div class="footer">
            <div>
                版权所有： 南京网博
            </div>
            <div>
                Copyright © 2017 imooc.com All Rights Reserved | 京ICP备 13046642号-2
            </div>
        </div>
    </div>
    <div class="modal fade" id="userSet" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">个人信息</h4>
                </div>
                <form action="#" class="form-horizontal" method="post">
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
                        <button type="button" class="btn btn-info" data-dismiss="modal" aria-label="Close">关&nbsp;&nbsp;闭</button>
                        <button type="reset" class="btn btn-info">重&nbsp;&nbsp;置</button>
                        <button type="submit" class="btn btn-info">确&nbsp;&nbsp;定</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>

</html>