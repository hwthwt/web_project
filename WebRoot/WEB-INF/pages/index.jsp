<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>优学堂</title>
    <!-- js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/swiper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/front-index.js"></script>
    <script src="${pageContext.request.contextPath}/js/template-web.js"></script>
    <!-- css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/swiper.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href='${pageContext.request.contextPath}/iconfont/font_1/iconfont.css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/front-index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
    <style>
    .ok {
		color: green;
	}

	.error {
		color: red;
	}
	
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
    <script>
		$(function(){
		$("a[name='showcourse']").mouseover(function(){
				console.log("第一级的id为:"+ $(this).attr("data-id"));
				$.ajax({
		    			type:"post",
		    			url:"${pageContext.request.contextPath}/userfont/findFourCourse.do",
		    			data:{"id":$(this).attr("data-id")},
		    			dataType:"json",
		    			success:function(data){
							 if (data.success) {
									if (data.obj != null) {
									console.log("修改成功2");
											if (data.obj[0] != null) {
														//console.log("课程名为:"+data.obj[0].course_name);
														$(".kechengming1").text(data.obj[0].course_name);
									
														$(".dianjiliang1").text(data.obj[0].click_number+"人");
														$(".tupian1").attr("src","${pageContext.request.contextPath}/upload"+data.obj[0].cover_image_url);
											}
											if (data.obj[1] != null) {
														//console.log("课程名为:"+data.obj[1].course_name);
														$(".kechengming2").text(data.obj[1].course_name);
														
														$(".dianjiliang2").text(data.obj[1].click_number+"人");
														$(".tupian2").attr("src","${pageContext.request.contextPath}/upload"+data.obj[1].cover_image_url);
														
											}
											if (data.obj[2] != null) {
														$(".kechengming3").text(data.obj[2].course_name);
														
														$(".dianjiliang3").text(data.obj[2].click_number+"人");
														$(".tupian3").attr("src","${pageContext.request.contextPath}/upload"+data.obj[2].cover_image_url);
											}
											if (data.obj[3] != null) {
														$(".kechengming4").text(data.obj[3].course_name);
														
														$(".dianjiliang4").text(data.obj[3].click_number+"人");
														$(".tupian4").attr("src","${pageContext.request.contextPath}/upload"+data.obj[3].cover_image_url);
											}
											
					} else {
						/* $("#oldpasswordMsg").text("原密码错误,请重新输入");
						$("#oldpasswordMsg").removeClass("ok");
						$("#oldpasswordMsg").addClass("error"); */
					}
				} else {
					/* $("#oldpasswordMsg").text(data.msg);
					$("#oldpasswordMsg").removeClass("ok");
					$("#oldpasswordMsg").addClass("error"); */
				}
    		   },
    		});	
		});
}); 





//检查两次输入的新密码是否一致
function checkModifyRepassword(){
     if($("#newPassword").val()=="" || $.trim($("#newPassword").val()).length == 0){
    		$("#newpasswordMsg1").text("密码不可为空");
    		 $("#newpasswordMsg1").removeClass("ok");
    		 $("#newpasswordMsg1").addClass("error");
    		return false;
     }else if($("#renewPassword").val()!=$("#newPassword").val()){
    		$("#newpasswordMsg2").text("两次输入的密码不一致");
    		$("#newpasswordMsg2").removeClass("ok");
    		$("#newpasswordMsg2").addClass("error");
    			return false;
   	  }else{
   	  			 $("#newpasswordMsg2").text("两次输入的密码一致,可用");
   	  			 $("#newpasswordMsg2").removeClass("error");
    			$("#newpasswordMsg2").addClass("ok");
   	 			 return true;
   	 		 }
     }

//检查修改用户名是原密码是否正确
	var oldpwdFlag=false;
	function checkOldPassword() {
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/userfont/checkOldPassword.do",
			data : {
				"password" : $("#oldpassword1").val(),
				"username" : $("#modifyUsername1").val()
			},
			dataType : "json",
			success : function(data) {
				if (data.success) {
					if (data.obj != null) {
						$("#oldpasswordMsg").text("原密码正确,请输入要修改的用户信息"); 
						$("#oldpasswordMsg").removeClass("error");
						$("#oldpasswordMsg").addClass("ok");
						oldpwdFlag=true;
					} else {
						$("#oldpasswordMsg").text("原密码错误,请重新输入");
						$("#oldpasswordMsg").removeClass("ok");
						$("#oldpasswordMsg").addClass("error");
					}
				} else {
					$("#oldpasswordMsg").text(data.msg);
					$("#oldpasswordMsg").removeClass("ok");
					$("#oldpasswordMsg").addClass("error");
				}
			},
		});
	}

 function modifycheck(){    
	if(oldpwdFlag&&checkModifyRepassword()){
				return true;
	}else{
				return false;
	}
} 

//签到操作
 $(function() {
         $(".signBtn").click(function() {
		           $.ajax({
							type : "post",
							url : "${pageContext.request.contextPath}/userfont/qiandao.do",
							data : {
								"useriD" : $("#UserId11").val(),
					},
					dataType : "json",
					success : function(data) {
						if(data.success){
						   		 //alert("签到成功");
								 $(".expe").show().addClass('animated forward fadeOutUp');
		        		  		 $(".signBtn").html("已签到").unbind("click").addClass('gray').removeClass('hoverRed');
						}
					},
			});"/home/soft01/桌面/项目/source/pages/front_index.html"
        });
      var isLogin = false;
      changeUserDiv(isLogin);
    }); 

//  检查是否已经签到
function changeUserDiv(isLogin){
	var isLogin=isLogin;
	
	var SignInDate=$("#logindate11").val();
	console.log("isLogin:"+isLogin);
	console.log("签到日期为:"+SignInDate);
	console.log("--------------------------------------");
  /*   if (isLogin) { 
            $("#loginOff").hide();
            $("#loginOn").show();
            $("#login_modal").modal("hide");
            // $(".signBtn").html("已签到").unbind("click").addClass('gray').removeClass('hoverRed');
        } else {
            $("#loginOn").hide();
            $("#loginOff").show();
        }   */
 }


//检查邮箱格式是否正确
function checkEmail(){
    	   var reg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    		var email = $("#registEmail").val();
    		if(reg.test(email)){
        		$("#email").text("邮箱校验通过");
        		$("#email").removeClass("error");
        		$("#email").addClass("ok");
        		return true;
    		}else{
        		$("#email").text("邮箱格式不正确");
        		$("#email").removeClass("ok");
        		$("#email").addClass("error");
        		return false;
    		}
 }

//检查密码是否为空和两次密码是否一致
function checkRepassword(){
     if($("#registPassword").val()=="" || $.trim($("#registPassword").val()).length == 0){
    		$("#password").text("密码不可为空");
    		 $("#password").removeClass("ok");
    		 $("#password").addClass("error");
    		return false;
     }else if($("#registPassword").val()!=$("#registRePassword").val()){
    		$("#rePassword").text("两次输入的密码不一致");
    		$("#rePassword").removeClass("ok");
    		$("#rePassword").addClass("error");
    			return false;
   	  }else{
   	  			 $("#rePassword").text("两次输入的密码一致,可用");
   	  			 $("#rePassword").removeClass("error");
    			$("#rePassword").addClass("ok");
   	 			 return true;
   	 		 }
     }

	 var checkusernameFlag=true;
	//查询用户名是否可用
	function checkUsername() {
		if ($("#registUsername").val == "" ||  $.trim($("#registUsername").val()).length == 0) {
			$("#username").text("对不起该用户名不可以为空");
			$("#username").removeClass("ok");
			$("#username").addClass("error");
			return checkusernameFlag = false;
		}
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/userfont/selectByName.do",
			data : {
				"name" : $("#registUsername").val()
			},
			dataType : "json",
			success : function(data) {
				if (data.success) {
					if (data.obj != null) {
						//document.getElementById("s").innerHTML="对不起该用户名已经被占用";
						$("#username").text("对不起该用户名已经被占用");
						$("#username").removeClass("ok");
						$("#username").addClass("error");
						checkusernameFlag = false;
					} else {
						$("#username").text("用户名可用");
						$("#username").removeClass("error");
						$("#username").addClass("ok");
						checkusernameFlag = true;
					}
				} else {
					$("#username").text(data.msg);
					$("#username").removeClass("ok");
					$("#username").addClass("error");
					checkusernameFlag = false;
				}
			},
		});
	}

/**	$(function() {
		$(".signBtn").click(
				function() {
					$(".expe").show().addClass('animated forward fadeOutUp');

					$(".signBtn").html("已签到").unbind("click").addClass('gray')
							.removeClass('hoverRed');
				});

		var isLogin = false;
		console.log("isLogin=", isLogin);
		changeUserDiv(isLogin);

		$.ajax({
			url : 'types.json',
			dataType : 'json',
			success : function(data) {
				$('#typeItemDiv').html("");
				console.log(data);
				var txt = template('typeItemList', {
					list : data
				});
				$('#typeItemDiv').append(txt);
			},
			error : function() {
				alert('加载分类出错!');
			}
		});
	});  */

	function openUserModal(isRegist) {
		if (isRegist) { //是注册
			$("#regist_modal").modal("show");
			return;
		}
		//是登录
		$("#login_modal").modal("show");
	}
			
function changeUserDiv(isLogin) {
		var isqiandao=$("#flag11").val();
		if (isqiandao==1) { //
			/* $("#loginOff").hide();
			$("#loginOn").show();
			$("#login_modal").modal("hide"); */
			$(".signBtn").html("已签到").unbind("click").addClass('gray').removeClass('hoverRed');
		} /* else {
			$("#loginOn").hide();
			$("#loginOff").show();
		} */
	}
	
//判断是否提交表单
  //是否提交表单
 function check(){    
	if(checkusernameFlag&&checkRepassword()&&checkEmail()){
				return true;
	}else{
				return false;
	}
} 
    </script>
</head>

<body>
	<input type="hidden"  value="${user.id}"  id="UserId11">
	<input type="hidden"  value="${user.loginDate}"  id="logindate11">
	<input type="hidden"  value="${user.flag}"  id="flag11"><br/>
	<input type="hidden"  value="${courseTypes[0].typeName}">
	
    
    <div class="wrap-cc">
    <div class="content-cc">
        <!-- head -->
    <nav class="navbar navbar-default">
        <div class="container-fluid" style="background: #fff;box-shadow: 5px 5px 5px #eef;padding-left: 20px;">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <!--  <a class="navbar-brand" href="#">Brand</a> -->
                <img src="images/com-logo1.png" alt="" width="120" style="margin-right: 20px;">
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="#" class="vertical-middle">免费课程</a></li>
                    <li><a href="#" class="vertical-middle">职业路径</a></li>
                </ul>
                <form action="${pageContext.request.contextPath}/selectfont/search.do" class="navbar-form navbar-left searchInput" style="padding:10px;">
                    <div class="form-group">
                        <input type="text" class="" placeholder="Search" name="searchcontext">
                    </div>
                    <button type="submit" class="btn btn-default "><span class="glyphicon glyphicon-search"></span></button>
                </form>
				
			<c:if test="${user == null}">
				<span style="color:red">${LoginMsg}</span>
                <div id="loginOff" class="regist ccc">
                    <span style="margin-right: 20px;font-size: 14px;">下载APP</span>
                    <a href="javascript:openUserModal(false);" class="ccc">登录</a> &nbsp;&nbsp;/&nbsp;&nbsp;
                    <a href="javascript:openUserModal(true);" class="ccc">注册</a>
                </div>
			</c:if>	
			
			<c:if test="${user != null}">
				<span style="color:green">${LoginMsg}</span>
				<span style="color:green">${ModifyMsg}</span>
                <ul id="loginOn" class="nav navbar-nav navbar-right">
                    <li class="nav navbar-nav signIn">
                        <div class="signBtn hoverRed">签到</div>
                        <div class="expe">+5经验值</div>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle user-active" data-toggle="dropdown" role="button">
                            <img class="img-circle" src="images/user.jpeg" id="userImg">
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu userinfo cc">
                            <li>
                                <img src="images/user.jpeg" class="img-circle" alt="">
                                <div class="">
                                    <p>${user.nickname}</p>
                                    <p>金币<span>${user.allGold}</span>&nbsp;积分 <span>${user.allPoint}</span></p>
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
                                <a href="${pageContext.request.contextPath}/userfont/loginOut.do"><i class="glyphicon glyphicon-off"></i>退出登录</a>
                            </li>
                        </ul>
                    </li>
                </ul>
			</c:if>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    <!-- nav -->
    <div class="main">
        <!-- 左侧 -->
        <div id="typeItemDiv" class="menu-left">
			<c:forEach items="${courseTypes}" var="courseType" >
            <div class="item" data-id="a">
                <a href="#" name="showcourse">
                    <span class="group">${courseType.typeName}</span>
                    <span class="tip">&gt;</span>
                </a>
                <div class="course-detail">
					<c:forEach items="${courseType.children}" var="courseType2" >
                    <div class="one">
                        <div class="base">
                            <span>${courseType2.typeName}</span>
                            <div class="line"></div>
                        </div>
                        <p>
							<c:forEach items="${courseType2.children}" var="courseType3" >
                            <a href="#"><span>${courseType3.typeName}</span></a>
                            </c:forEach>
                        </p>
                    </div>
					</c:forEach>
                    <!-- <div class="one">
                        <div class="base">
                            <span>进阶</span>
                            <div class="line"></div>
                        </div>
                        <p>
                            <a href="#"><span>HTML/CSS</span></a>
                            <a href="#"><span>JavaScript</span></a>
                            <a href="#"><span>jQuery</span></a>
                        </p>
                    </div> -->

                    <div class="two">
                        <div class="item-box">
                            <a href="front_course.html">
                                <div class="item-course">
                                    <div class="item-course-l">
                                        <img src="./images/course04.jpg" alt="" class="tupian1">
                                    </div>
                                    <div class="item-course-r">
                                        <p class="kechengming1">前端进阶：响应式开发与常用框架</p>
                                        <p>                                      
                                            点击量<span class="dianjiliang1">503人</span>
                                        </p>
                                        <p>￥599.00</p>
                                    </div>
                                </div>
                            </a>
                            <a href="front_course.html">
                                <div class="item-course">
                                    <div class="item-course-l">
                                        <img src="./images/course02.jpg" alt="" class="tupian2">
                                    </div>
                                    <div class="item-course-r">
                                        <p class="kechengming2">前端进阶：响应式开发与常用框架</p>
                                        <p>
                                            点击量<span class="dianjiliang2">503人</span>
                                        </p>
                                        <p>￥599.00</p>
                                    </div>
                                </div>
                            </a>
                            <a href="front_course.html">
                                <div class="item-course">
                                    <div class="item-course-l">
                                        <img src="./images/course03.jpg" alt="" class="tupian3">
                                    </div>
                                    <div class="item-course-r">
                                        <p class="kechengming3">前端进阶：响应式开发与常用框架</p>
                                        <p>
                                            点击量<span class="dianjiliang3">503人</span>
                                        </p>
                                        <p>￥599.00</p>
                                    </div>
                                </div>
                            </a>
							<a href="front_course.html">
                                <div class="item-course">
                                    <div class="item-course-l">
                                        <img src="./images/course03.jpg" alt="" class="tupian4">
                                    </div>
                                    <div class="item-course-r">
                                        <p class="kechengming4">前端进阶：响应式开发与常用框架</p>
                                        <p>
                                            点击量<span class="dianjiliang4">503人</span>
                                        </p>
                                        <p>￥599.00</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>

                </div>
            </div>
			</c:forEach>

            
        </div>

		<script id="typeItemList" type="text/html">
            {{each list as type1 index}}
            <div class="item" typeId="{{type1.id}}">
                <a href="#">
                    <span class="group">{{type1.typeName}}</span>
                    <span class="tip">&gt;</span>
                </a>
                <div class="course-detail">
                    {{each type1.children as type2 index2}}
                    <div class="one">
                        <div class="base" typeId="{{type2.id}}" >
                            <span>{{type2.typeName}}</span>
                            <div class="line"></div>
                        </div>
                        <p>
                            {{each type2.children as type3 index3}}
                            <a href="#">
                                <span typeId="{{type3.id}}" >{{type3.typeName}}</span>
                            </a>
                            {{/each}}
                        </p>
                    </div>
                    {{/each}}
                    <!-- 当前分类下,点击量前四的课程 -->
                    <div class="two">
                        <div class="item-box">
                            <a href="front_course.html">
                                <div class="item-course">
                                    <div class="item-course-l">
                                        <img src="./images/course04.jpg" alt="">
                                    </div>
                                    <div class="item-course-r">
                                        <p>前端进阶：响应式开发与常用框架</p>
                                        <p>
                                            <span>职业路径</span>
                                            <span>5步/30课</span>
                                            <span>503人</span>
                                        </p>
                                        <p>￥599.00</p>
                                    </div>
                                </div>
                            </a>
                            <a href="front_course.html">
                                <div class="item-course">
                                    <div class="item-course-l">
                                        <img src="./images/course02.jpg" alt="">
                                    </div>
                                    <div class="item-course-r">
                                        <p>前端进阶：响应式开发与常用框架</p>
                                        <p>
                                            <span>职业路径</span>
                                            <span>5步/30课</span>
                                            <span>503人</span>
                                        </p>
                                        <p>￥599.00</p>
                                    </div>
                                </div>
                            </a>
                            <a href="front_course.html">
                                <div class="item-course">
                                    <div class="item-course-l">
                                        <img src="./images/course03.jpg" alt="">
                                    </div>
                                    <div class="item-course-r">
                                        <p>前端进阶：响应式开发与常用框架</p>
                                        <p>
                                            <span>职业路径</span>
                                            <span>5步/30课</span>
                                            <span>503人</span>
                                        </p>
                                        <p>￥599.00</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            {{/each}}
        </script>  

        <!-- 右侧 -->
        <div class="menu-right">
            <!-- banner -->
            <div class="banner">
                <div class="swiper-container">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                            <img src="./images/banner01.jpg" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="./images/banner02.jpg" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="./images/banner03.jpg" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="./images/banner04.jpg" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="./images/banner05.jpg" alt="">
                        </div>
                    </div>
                    <!-- Add Pagination -->
                    <div class="swiper-pagination"></div>
                </div>
            </div>
            <!-- 课程信息 -->
            <div class="tips">
                <div class="path-banner">
                    <a href="#">
                        <i class="i-web"></i>
                        <p class="tit">Web前端攻城狮</p>
                        <p class="desc">互联网时代最火爆的技术</p>
                    </a>
                    <a href="#">
                        <i class="i-java"></i>
                        <p class="tit">Java攻城狮</p>
                        <p class="desc">健壮、安全、适用广泛</p>
                    </a>
                    <a href="#">
                        <i class="i-android"></i>
                        <p class="tit">Android攻城狮</p>
                        <p class="desc">移动设备市场份额第一</p>
                    </a>
                    <a href="#">
                        <i class="i-php"></i>
                        <p class="tit">PHP攻城狮</p>
                        <p class="desc">世界上最好的语言：）</p>
                    </a>
                    <a href="#">
                        <i class="i-ios"></i>
                        <p class="tit">iOS攻城狮</p>
                        <p class="desc">可能是全球最好用的系统</p>
                    </a>
                </div>
            </div>
        </div>
    </div>
	
	
    <!-- 实战推荐 -->
    <div class="course">
        <h3 class="types-title">                
            <span class="tit-icon tit-icon-l"></span>
            <em>实</em>／<em>战</em>／<em>推</em>／<em>荐</em>
        </h3>
		<c:forEach items="${allcourses}" var="allcourse"  varStatus="status" >
			<c:if test="${((status.index)+1) %4 ==1}"> 
				<div class="course-box">
			</c:if>   
		
        
            <div class="course-item">
                <div class="item-t">
                    <img src="${pageContext.request.contextPath}/upload${allcourse.cover_image_url}" alt="">
                    <div class="java">
                        <label>${allcourse.course_info}</label>
                    </div>
                </div>
                <div class="item-b">
                    <a href="front_course.html">
                        <h4>${allcourse.course_name}</h4>
                    </a>
                    <p class="title">
                        <span>${allcourse.click_number}人点击</span>         
                    </p>
                </div>
            </div>
			
			
            
           
        
            <c:choose>
				<c:when test="${(status.index+1) %4 ==0}">
					</div>
				</c:when>
				<c:when test="${status.last}">  
					</div>
				</c:when> 
			</c:choose>
		</c:forEach>
        
    </div>    
	
	
	
<div class="footer-cc">
    <div class="foots">
        <div>
            版权所有： 南京网博
        </div>
        <div>
            Copyright © 2017 imooc.com All Rights Reserved | 京ICP备 13046642号-2
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
				<form
					action="${pageContext.request.contextPath}/userfont/modifyUser.do"
					class="form-horizontal" method="post"  onsubmit="return modifycheck()">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-3 control-label">旧密码：</label>
							<div class="col-sm-6">
								<input class="form-control" type="password" name="oldpassword"  id="oldpassword1"  onblur="checkOldPassword()"/>
								<span id="oldpasswordMsg"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">新密码：</label>
							<div class="col-sm-6">
								<input class="form-control" type="password" name="newPassword" id="newPassword" onblur="checkModifyRepassword()"/>
								<span id="newpasswordMsg1"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">确认密码：</label>
							<div class="col-sm-6">
								<input class="form-control" type="password" name="renewPassword" id="renewPassword" onblur="checkModifyRepassword()"/>
								<span id="newpasswordMsg2"></span>
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
						<input type="hidden" value="${user.username}" name="username"  id="modifyUsername1">
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
	<!-- regist modal -->
	<div class="modal fade" id="regist_modal" tabindex="-1" role="dialog"
		aria-labelledby="myRegistLabel">
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myRegistLabel">注册</h4>
				</div>
				<form action="${pageContext.request.contextPath}/userfont/regist.do"
					class="form-horizontal" method="post" onsubmit="return check()">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-3 control-label">登录名：</label>
							<div class="col-sm-6">
								<input class="form-control" type="text" name="username"
									id="registUsername" onblur="checkUsername()" /> <span
									id="username"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">密码：</label>
							<div class="col-sm-6">
								<input class="form-control" type="password" name="password"
									id="registPassword" /> <span id="password"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">确认密码：</label>
							<div class="col-sm-6">
								<input class="form-control" type="password" name="rePassword"
									id="registRePassword" onblur="checkRepassword()" /> <span
									id="rePassword"></span>
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
								<input class="form-control" type="text" name="email"
									id="registEmail" onblur="checkEmail()" /><span id="email"></span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-warning" data-dismiss="modal"
							aria-label="Close">关&nbsp;&nbsp;闭</button>
						<button type="submit" class="btn btn-info" id="register">注&nbsp;&nbsp;册</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- login modal -->
	<div class="modal fade" id="login_modal" tabindex="-1" role="dialog"
		aria-labelledby="myLoginLabel">
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myLoginLabel">登录</h4>
				</div>
				<form action="${pageContext.request.contextPath}/userfont/login.do"
					class="form-horizontal" method="post">
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-3 control-label">登录名：</label>
							<div class="col-sm-6">
								<input class="form-control" type="text" name="username"
									id="loginName" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">密码：</label>
							<div class="col-sm-6">
								<input class="form-control" type="password" name="password"
									id="loginPassword" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<span id="s_login" ></span>
						<button type="button" class="btn btn-warning" data-dismiss="modal"
							aria-label="Close">关&nbsp;&nbsp;闭</button>
						<button type="submit" class="btn btn-info"  onclick="changeUserDiv(true)">登&nbsp;&nbsp;录</button>
						<!-- onclick="changeUserDiv(true)" -->
					</div>
				</form>
			</div>
		</div>
	</div>
    
</body>

</html>