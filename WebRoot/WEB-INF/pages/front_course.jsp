<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>课程</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/iconfont/font_1/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/iconfont/font_0/iconfont.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
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
        $(".arrow").click(function() {
            $(this).parent().next().toggle();
        })

        $(".title-ul>li").on('click', function() {
            console.log($(this));
            $(this).addClass('current').siblings().removeClass("current");
            var panelId = "#" + $(this).attr("name");
            $(this).parent().siblings().css({
                'display': 'none'
            });
            $(panelId).css({
                'display': 'block'
            });

        })
        
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
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <!--  <a class="navbar-brand" href="#">Brand</a> -->
                        <a href="front_index.html"><img src="${pageContext.request.contextPath }/images/com-logo1.png" alt="" width="120" style="margin-right: 20px;"></a>
                    </div>
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="#" class="vertical-middle">免费课程</a></li>
                            <li><a href="#" class="vertical-middle">职业路径</a></li>
                        </ul>
                        <form class="navbar-form navbar-left searchInput" style="padding:10px;">
                            <div class="form-group">
                                <input type="text" class="form-control " placeholder="Search">
                            </div>
                            <button type="submit" class="btn btn-default "><span class="glyphicon glyphicon-search"></span></button>
                        </form>
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
                                        <a href="front_mycourse.html">
                                    <i class="glyphicon glyphicon-edit"></i>我的课程
                                </a>
                                        <a href="front_record.html">
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
                    </div>
                    <!-- /.navbar-collapse -->
                </div>
                <!-- /.container-fluid -->
            </nav>
            <div class="container-fluid banner">
                <div class="container banner-contain">
                    <div class="row">
                        <p> 课程&bsol;前端开发&bsol;前端工具&bsol;webpack深入与实战</p>
                    </div>
                    <div class="row">
                        <p>${courseName}</p>
                    </div>
                    <div class="row">
                        <button class="btn    btn-success  col-md-2">
                            继续学习 | &nbsp;&nbsp; <i class="glyphicon glyphicon-star-empty">  </i>
                        </button>
                        <ul class="col-md-5">
                            <li>点击量
                                <p> ${click} </p>
                            </li>
                            <li> 课程时长
                                <p>3小时21分</p>
                            </li>
                            <li> 综合评分
                                <p>9.7</p>
                            </li>
                        </ul>
                        <ul class="three-logo  col-md-4  col-md-offset-1 ">
                            <li>
                                <i class="icon iconfont icon-weixin"></i>
                            </li>
                            <li>
                                <i class="icon iconfont icon-weibo"></i>
                            </li>
                            <li>
                                <i class="icon iconfont icon-qq"></i>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-9">
                        <div class="desp">简介：${courseInfo}</div>
                        <ul class="title-ul">
                            <li class="current" name="chapter">章节</li>
                            <li name="comment">评价</li>
                            <span>${resourceMsg}</span>
                        </ul>
                        <ul class="course-content margin-bottom-90" id="chapter">
                            <c:forEach items="${chapters}" var="Chapter">
                            <li>
                                <div class="row">
                                    <div class="col-md-12 course-title">
                                        <i class="icon  i-round iconfont icon-weibiaoti-"></i> 第${Chapter.id}章 <span> ${Chapter.title} </span>
                                        <i class="glyphicon glyphicon-triangle-bottom pull-right arrow"></i>
                                    </div>
                                    <ul class="lesson-title">
                                        <li class="col-md-11 col-md-offset-1 padding-10 ">
                                            <span class="glyphicon glyphicon-triangle-right icon-right"> </span>
                                            <a href="${pageContext.request.contextPath}/chapterList/read.do?rId=${Chapter.resource.id}&title=${Chapter.title}&cid=${Chapter.id}&name=${Chapter.resource.title}"><span> ${Chapter.id}-1 ${Chapter.resource.title} (07:03)</span></a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            </c:forEach>
                        </ul>
                        <ul id="comment" class="margin-bottom-90">
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
</body>

</html>