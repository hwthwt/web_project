<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>课程章节管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/back-index.css" />
    <script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-mypaginator.js"></script>
    <style type="text/css" >
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
    </style>
<script type="text/javascript">
    $(function(){
   			$("input[name='modifyResource']").click(function(){
   					$("#course-resource-id2").attr("value",$(this).attr("data-id"))
   			});
    
          $("#doSearch").click(function(){
          		$("#submit11").click();	
          })
    
    	var pages;
    	if("${chapterPages.pages}" == 0){
    		pages = 1;
    	}else{
    		pages = "${chapterPages.pages}";
    	}
    	$("#idv").hide();
    	$("#idv2").hide();
    	$("#submit11").hide();
    	$("#submit111").hide();
    	var options = {
    		bootstrapMajorVersion:3, //表示当前bootstrap版本号
    		currentPage:"${chapterPages.pageNum}",//当前页
    		totalPages:pages,//总页数
    		size:"normal",
    		aligment:"center",
    		pageUrl:function(type,page,current){
    		console.log("$('#idv'):"+$("#idv"));
    		console.log("$('#idv')值为:"+$("#idv").val());
    		 if("${chap.title}" != ""){
    		 		//$("#submit111").click();
    		 		//return "${pageContext.request.contextPath}/chapter/selectByFuzzy.do?pageNo="+page;
    		 		return "${pageContext.request.contextPath}/chapter/selectByFuzzy.do?pageNo="+page
    		 		+"&title="+"${chap.title}"
    		 		+"&info="+"${chap.info}"
    		 		+"&beginDate="+"${chap.beginDate}"
    		 		+"&endDate="+"${chap.endDate}"
    		 		+"&course-resource-stauts-search="+"${chap.status}"; 
    		 }else{
    			    return "${pageContext.request.contextPath}/chapter/findAll.do?pageNo="+page+"&id="+$("#idv").val();
    		 }
    		} 
    	};
    	$("#myPages").bootstrapPaginator(options);
    	
    	
     $("input[name='modifyStatus']").click(function(){
    		$.ajax({
    			type:"post",
    			url:"${pageContext.request.contextPath}/chapter/modifyStatus.do",
    			data:{"id":$(this).attr("data-id"),"status":$(this).attr("data-status")},
    			dataType:"json",
    			success:function(result){
    				if(result.responseCode==2001){
	    					 location.href="${pageContext.request.contextPath}/chapter/findAll.do?pageNo=${chapterPages.pageNum}"+"&id="+$("#idv").val();
    				}else{
    					$("#errorMsg").tooltip({
    						title:"error",
    						placement:"center",
    						//template表示显示的错误信息
    						template:"<div class='tooltip errorMsg'>"+result.message+"</div>",
    						tigger:"manual",
    					}).tooltip("show");
    				}
    			}
    		});
    	});
    	
    });
    </script>
<script type="text/javascript" >
        $(function(){
            //返回课程列表
            $("#back").on('click', function(){
                $('#frame-id', window.parent.document).attr('src', '${pageContext.request.contextPath}/course/findAll.do');
            });
            //相关评论
            $(".comment-detail").on('click', function(){
                $('#frame-id', window.parent.document).attr('src', '${pageContext.request.contextPath}/commentSet/findAll.do?id='+$(this).attr("data-id"));
            });

            //添加章节
            $("#doAddCourseReource").on('click', function(){
              //  $(".modal-title").html("添加章节");
          //      $("#course-resource-id-input").hide();
                $("#courseReourceModal").modal("show");
            });
            $(".course-reource-btn").on('click', function(){
                $("#courseReourceModal").modal("hide");
            });

            $(".course-reource-modify-btn").on('click', function(){
            //    $(".modal-title2").html("修改章节");
              //  $("#course-resource-id-input").show();
                $("#courseReourceModal2").modal("show");
                console.log($());
            });
           
           $(".course-reource-btn").on('click', function(){
                $("#courseReourceModal2").modal("hide");
            });
            
       //获取id的值
        
    	
            //显示播放页
            $(".resource-detail").on('click', function(){
                $('#frame-id', window.parent.document).attr('src', 'back_resourceDetailSet.html');
            });

            // 显示隐藏查询列表
            $('#show-course-resource-search').click(function() {
                $('#show-course-resource-search').hide();
                $('#upp-course-resource-search').show();
                $('.show-course-resource-search').slideDown(500);
            });
            $('#upp-course-resource-search').click(function() {
                $('#show-course-resource-search').show();
                $('#upp-course-resource-search').hide();
                $('.show-course-resource-search').slideUp(500);
            });

  /*           //按钮的超链接设置函数
    //      myoptions.pageUrl = function(type, page, current) {
    //          return "${pageContext.request.contextPath }/findAll.do?pageNo="
    //                  + page;
    //      };
            //分页回调函数,点击按钮事件
            myoptions.onPageClicked = function(event, originalEvent, type, page) {
                //ajax回调接收数据成功时再重新初始化分页按钮
                console.log("pageNo=", page);
            };

            //分页,在bootstrap-mypaginator.js中
            myPaginatorFun("myPages", 1, 5); */

        });

        function fileUpload(item){
            $(item).click();
        }
        function fileChange(item){
            var file = item.files[0];//获取选中的第一个文件
            $(".file").html(file.name);
            //console.log("file", file.name);
        }
        
        

    </script>
</head>

<body>
	
    <div class="panel panel-default" id="userSet">
        <div class="panel-heading">
            <h3 class="panel-title">课程章节管理</h3>
        </div>
        <div>
            <input type="button" value="添加章节" class="btn btn-primary" id="doAddCourseReource" style="margin: 5px 5px 5px 15px;" />
            <input type="button" value="查询" class="btn btn-success" id="doSearch" style="margin: 5px 5px 5px 0px;" />
            <input type="button" class="btn btn-primary" id="show-course-resource-search" value="展开搜索" />
            <input type="button" value="收起搜索" class="btn btn-primary" id="upp-course-resource-search" style="display: none;" />
            <input type="button" value="返回课程列表" class="btn btn-success" id="back" style="margin: 5px 15px 5px 0px;float: right;" />
        </div>
        <div class="panel-body">
            <div class="show-course-resource-search" style="display: none;">
                <form class="form-horizontal"  action="${pageContext.request.contextPath}/chapter/selectByFuzzy.do" method="post">
                    <div class="form-group">
                        <div class="form-group col-xs-6">
                            <label for="course-resource-title-search" class="col-xs-3 control-label">标题：</label>
                            <div class="col-xs-8">
                                <input type="text" class="form-control" id="course-resource-title-search" placeholder="请输入标题"  name="title"/>
                            </div>
                        </div>
                        <div class="form-group col-xs-6">
                            <label for="course-resource-info-search" class="col-xs-3 control-label">简介：</label>
                            <div class="col-xs-8">
                                <input type="text" class="form-control" id="course-resource-info-search" placeholder="请输入简介"  name="info"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-group col-xs-6">
                            <label class="col-xs-3 control-label">开始日期：</label>
                            <div class="col-xs-8">
                                <input type="text" class="form-control" placeholder="请输入开始时间:2017-10-10"  name="beginDate"/>
                            </div>
                        </div>
                        <div class="form-group col-xs-6">
                            <label class="col-xs-3 control-label">结束日期：</label>
                            <div class="col-xs-8">
                                <input type="text" class="form-control" placeholder="请输入结束时间:2017-10-12" name="endDate" />
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
                    <input type="submit" id="submit11">
                </form>
            </div>
            
            <div class="show-list">
                <table class="table table-bordered table-hover" style='text-align: center;'>
                    <thead>
                        <tr class="text-danger">
                            <th class="text-center">编号</th>
                            <th class="text-center">标题</th>
                            <th class="text-center">简介</th>
                            <th class="text-center">创建时间</th>
                            <th class="text-center">状态</th>
                            <th class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody id="tb">
                    <c:forEach items="${chapterPages.list}" var="chapter" >
                        <tr>
                            <td>${chapter.id}</td>
                            <td>${chapter.title}</td>
                            <td>${chapter.info}</td>
                            <td>${chapter.create_date}</td>
                            <td>
                            	<c:if test="${chapter.status eq 0 }">启用</c:if>
                            	<c:if test="${chapter.status eq 1 }">禁用</c:if>
                            </td>
                            <td class="text-center">
                                <input type="button"  name="modifyResource" data-id="${chapter.id}"  class="btn btn-warning btn-sm course-reource-modify-btn" value="修改"  >
                                
                                  <c:if test="${chapter.status eq 0 }">
                                	<input type="button" class="btn btn-danger btn-sm" value="禁用" data-id="${chapter.id}" data-status="${chapter.status}" name="modifyStatus">
                                </c:if>
                            	<c:if test="${chapter.status eq 1 }">
                                	<input type="button" class="btn btn-success btn-sm" value="启用" data-id="${chapter.id}" data-status="${chapter.status}" name="modifyStatus">
                            	</c:if>
                                
                                <input type="button" class="btn btn-success btn-sm resource-detail" value="详情" />
                                <input type="button" class="btn btn-success btn-sm comment-detail"  data-id="${chapter.id}"  value="相关评论" />
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

    <div class="modal fade" tabindex="-1" id="courseReourceModal" >
        <!-- 窗口声明 -->
        <div class="modal-dialog modal-lg">
            <!-- 内容声明 -->
            <div class="modal-content">
              <form action="${pageContext.request.contextPath}/chapter/addChapterAndResource.do" method="post" enctype="multipart/form-data">
              	<input  type="text"   value="${id}"  id="idv" name="courseId">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" >添加章节</h4>
                </div>
                <div class="modal-body text-center">
                    <div class="row text-right">
                        <label for="course-resource-title" class="col-xs-4 control-label">章节标题：</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="course-resource-title" name="title" />
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="course-resource-context" class="col-xs-4 control-label">内容详情：</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="course-resource-context"  name="info"/>
                        </div>
                    </div>
                    <br>
                    <p/>
                    <div class="row text-right">
                        <label class="col-xs-4 control-label">资源：</label>
                        <div class="col-xs-4">
                            <a href="javascript:fileUpload('#course-resource-file');" class="file" >选择文件</a>
                            <input type="file" name="course_resource_file" style="display: none;" onchange="fileChange(this)" id="course-resource-file" />
                        </div>
                    </div>
                    <br/>
                    <div class="row text-right">
                        <label for="course-resource-file-title" class="col-xs-4 control-label">资源标题：</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="course-resource-file-title"  name="ResourseTitle"/>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="file-cost-type" class="col-xs-4 control-label">查看资源花费类型：</label>
                        <div class="col-xs-4">
                            <select class="form-control" id="file-cost-type" name="file_cost_type_id" >
                                <option value="-1" >请选择</option>
                                <option value="0" >积分</option>
                                <option value="1" >金币</option>
                            </select>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="file-cost-type-val" class="col-xs-4 control-label">花费值：</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="file-cost-type-val" name="costNumber"/>
                        </div>
                    </div>
                    <br>
 	
                    <br>
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-primary course-reource-btn" value="确定">
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
             </form>
             </div>
        </div>
    </div>
             
             
            
            
          <div class="modal fade" tabindex="-1" id="courseReourceModal2" >
        <!-- 窗口声明 -->
        <div class="modal-dialog modal-lg">
            <!-- 内容声明 -->
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title2" >修改章节</h4>
                </div>
                <div class="modal-body text-center">
                    <!-- course-id,没有时,移除 '返回课程列表' 按钮 -->
           <form action="${pageContext.request.contextPath}/resource/modifyResouce.do" method="post" enctype="multipart/form-data">
                  	<input  type="text"   value="${id}"  id="idv2" name="courseId">
                    <input type="hidden" name="course_id" value=""  />
                    <div class="row text-right" id="course-resource-id-input" >
                        <label for="course-resource-id" class="col-xs-4 control-label">章节编号：</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="course-resource-id2" name="chapterid" readonly="true" value="safa" />
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="course-resource-title" class="col-xs-4 control-label">章节标题：</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="course-resource-title2" name="chaptertitle" />
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="course-resource-context" class="col-xs-4 control-label">内容详情：</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="course-resource-context2"  name="info"/>
                        </div>
                    </div>
                    <br>
                    <p/>
                    <div class="row text-right">
                        <label class="col-xs-4 control-label">资源：</label>
                        <div class="col-xs-4">
                            <a href="javascript:fileUpload('#course-resource-file');" class="file" >选择文件</a>
                            <input type="file" name="course_resource_file" style="display: none;" onchange="fileChange(this)" id="course-resource-file" />
                        </div>
                    </div>
                    <br/>
                    <div class="row text-right">
                        <label for="course-resource-file-title" class="col-xs-4 control-label">资源标题：</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="course-resource-file-title2"  name="resourcename"/>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="file-cost-type" class="col-xs-4 control-label">查看资源花费类型：</label>
                        <div class="col-xs-4">
                            <select class="form-control" id="file-cost-type2" name="file_cost_type_id" >
                                <option value="-1" >请选择</option>
                                <option value="0" >积分</option>
                                <option value="1" >金币</option>
                            </select>
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="file-cost-type-val" class="col-xs-4 control-label">花费值：</label>
                        <div class="col-xs-4">
                            <input type="text" class="form-control" id="file-cost-type-val2" name="costNum"/>
                        </div>
                    </div>
                    <br>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary course-reource-btn">确定</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
               </form>
            </div>
        </div>
    </div>
		

		
</html>