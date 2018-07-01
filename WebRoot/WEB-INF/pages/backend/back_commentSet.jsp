<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>评论管理</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="css/back-index.css" />
     <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/nmms.css" />
    <script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/bootstrap-paginator.js"></script>
    <script src="js/bootstrap-mypaginator.js"></script>
	<!-- 导入bootstrap分页插件 -->
    <script src="${pageContext.request.contextPath }/js/bootstrap-paginator.js" type="text/javascript" charset="utf-8"></script>
    
    <script type="text/javascript" >
        $(function(){
        
        
        	var pages;
    		var pageNo;
    		if("${pageTypes.pages}" == 0){
    			pages = 1;
    			pageNo = 1;
    		}else{
    			pages = "${pageTypes.pages}";
    			pageNo = "${pageTypes.pageNum}";
    		}
			//使用bootstrap分页插件实现分页功能
    		var options = {
    			bootstrapMajorVersion:3,//表示当前bootstrap版本号
    			currentPage:pageNo,//表示当前页
    			totalPages:pages,//一共多少页
    			size:"normal",
    			pageUrl:function(type,page,current){
    			return "${pageContext.request.contextPath}/commentSet/findAll.do?pageNo="+page;
    		}	
    	};
    	
    		$("#myPages").bootstrapPaginator(options);
    		
    		$("input[value='启用']").click(function(){
    			location.href = "${pageContext.request.contextPath}/commentSet/update.do?pageNo="+pageNo+"&id="
							+ $(this).attr("cid") + "&status=0";
    		});
    		
    		$("input[value='禁用']").click(function(){
    			location.href = "${pageContext.request.contextPath}/commentSet/update.do?pageNo="+pageNo+"&id="
							+ $(this).attr("cid") + "&status=1";
    		});
    		
            //返回章节列表
            $("#back").on('click', function(){
                $('#frame-id', window.parent.document).attr('src', 'back_courseReourceSet.html');
            });

            // 显示隐藏查询列表
            $('#show-comment-search').click(function() {
                $('#show-comment-search').hide();
                $('#upp-comment-search').show();
                $('.show-comment-search').slideDown(500);
            });
            $('#upp-comment-search').click(function() {
                $('#show-comment-search').show();
                $('#upp-comment-search').hide();
                $('.show-comment-search').slideUp(500);
            });

            //按钮的超链接设置函数
    //      myoptions.pageUrl = function(type, page, current) {
    //          return "${pageContext.request.contextPath }/findAll.do?pageNo="
    //                  + page;
    //      };

			$("#doSearch").click(function(){
			console.log($("#endTime").val());
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/commentSet/check.do",
				data:{
					"username":$("#user-name-search").val(),
					"usercomment":$("#user-comment-search").val(),
					"beginTime":$("#beginTime").val(),
					"endTime":$("#endTime").val(),
					"option":$("#course-resource-stauts-search option:selected").val()
				},
				dataType:"json",
				success:function(result){
					if(result.responseCode == "2001"){
						location.href="${pageContext.request.contextPath}/commentSet/showSelect.do?pageNo=1";    				
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    				setTimeout(function(){
    						location.href="${pageContext.request.contextPath}${commentSetsrc}"+pageNo;
    					},1000);
				}
				
			});    	
    	});
	
        });

    </script>
</head>

<body>
    <div class="panel panel-default" id="userSet">
        <div class="panel-heading">
            <h3 class="panel-title">评论管理<span id="errorMsg"></span></h3>
        </div>
        <div>
            <!-- course-resource-id,没有时,移除此按钮 -->
            <input type="hidden" name="course_resource_id" value=""  />
            <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 15px;" />
            <input type="button" class="btn btn-primary" id="show-comment-search" value="展开搜索" />
            <input type="button" value="收起搜索" class="btn btn-primary" id="upp-comment-search" style="display: none;" />
            <input type="button" value="返回章节列表" class="btn btn-success" id="back" style="margin: 5px 15px 5px 0px;float: right;" />
        </div>

        <div class="panel-body">
            <div class="show-comment-search" style="display: none;">
                <form class="form-horizontal" method="post">
                    <div class="form-group">
                        <div class="form-group col-xs-6">
                            <label for="user-name-search" class="col-xs-3 control-label">用户名：</label>
                            <div class="col-xs-8">
                                <input type="text" class="form-control" name="user-name-search" id="user-name-search" placeholder="请输入用户名" />
                            </div>
                        </div>
                        <div class="form-group col-xs-6">
                            <label for="user-comment-search" class="col-xs-3 control-label">内容：</label>
                            <div class="col-xs-8">
                                <input type="text" class="form-control" name="user-comment-search" id="user-comment-search" placeholder="请输入评论内容" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-group col-xs-6">
                            <label class="col-xs-3 control-label">开始日期：</label>
                            <div class="col-xs-8">
                                <input type="text" id="beginTime" name="beginTime" class="form-control" placeholder="请输入开始时间:2017-10-10" />
                            </div>
                        </div>
                        <div class="form-group col-xs-6">
                            <label class="col-xs-3 control-label">结束日期：</label>
                            <div class="col-xs-8">
                                <input type="text" id="endTime" name="endTime" class="form-control" placeholder="请输入结束时间:2017-10-12" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-group col-xs-6">
                            <label for="course-resource-stauts-search" class="col-xs-3 control-label">状态：</label>
                            <div class="col-xs-8">
                                <select class="form-control" id="course-resource-stauts-search" name="course-resource-stauts-search" >
                                    <option value="-1" >全部</option>
                                    <option value="0" >启用</option>
                                    <option value="1" >禁用</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    
                </form>
            </div>

            <div class="show-list">
                <table class="table table-bordered table-hover" style='text-align: center;'>
                    <thead>
                        <tr class="text-danger">
                            <th class="text-center">编号</th>
                            <th class="text-center">评论内容</th>
                            <th class="text-center">用户名</th>
                            <th class="text-center">评论时间</th>
                            <th class="text-center">赞</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody id="tb">
                    <c:forEach items="${pageTypes.list}" var="comment">
                        <tr>
                            <td>${comment.id }</td>
                            <td>${comment.context }</td>
                            <td>${comment.user.username }</td>
                            <td>${comment.createDate }</td>
                            <td>${comment.count }</td>
                            <c:if test="${comment.status eq STATUS_ENABLE}">
                            <td>启用</td>
                            </c:if>
                             <c:if test="${comment.status eq STATUS_DISABLE}">
                            <td>禁用</td>
                            </c:if>
                            <td class="text-center">
                            <c:if test="${comment.status eq STATUS_ENABLE}">
                                <input type="button" cid = "${comment.id }" class="btn btn-danger btn-sm" value="禁用" />
                            </c:if>
                            <c:if test="${comment.status eq STATUS_DISABLE}">
                            	<input type="button" cid = "${comment.id }" class="btn btn-success btn-sm" value="启用" />
                            </c:if>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- 分页 -->
            <div style="text-align: center;" >
                <ul id="myPages" ></ul>
            </div>

        </div>
    </div>
</body>

</html>