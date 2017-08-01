<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath1 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath1%>" />
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>产品审核详情页-中国联通研究院大数据应用模型孵化后台管理系统</title>
<!--[if lt IE 9]>
      <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/general.css">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/core.css">
<link rel="stylesheet" href="css/components.css">
<link rel="stylesheet" href="js/kkpager/kkpager_blue.css">
<link rel="stylesheet" href="js/jbox/jbox.css">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/validate/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="js/validate/additional-methods.min.js"></script>
<script type="text/javascript" src="js/validate/messages_zh.js"></script>
<script type="text/javascript" src="js/kkpager/kkpager.min.js"></script>
<script type="text/javascript" src="js/modal/bootstrap-modal.js"></script>
<script type="text/javascript" src="js/modal/bootstrap-modalmanager.js"></script>
<script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script> 
<script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script>



<script type="text/javascript" src="js/app.js"></script>
<!--[if IE 6]> 
      <script type="text/javascript" src="Lib/DD_belatedPNG_0.0.8a-min.js" ></script>
      <script>DD_belatedPNG.fix('.pngfix,.icon');</script> 
    <![endif]-->
<script type="text/javascript">
//下载文件
		function down(fileUrl){
	  		if (fileUrl==null) {
	  	  		return false;
			}
	  		$("#fileCode").val(fileUrl);
	  		$("#downFile").submit();
	  	}
    </script>
    
</head>
<body>
	<form style="display:none;" id="downFile" name="downFile" action="product/download.do" method="post">
		<input id="fileCode" name="fileCode" value="" />
	</form>
	
	<!-------------------------CONT---------------------------->
	<div class="Detail-cont box-cont">
		<div class="panel panel-default">
			<div class="panel-header">
				<h4>产品详情</h4>
			</div>
			<div class="panel-body">

				<!--商户注册资料-->
				<article class="panel panel-primary mt-10">
					<div class="panel-header">
						<h4>产品资料</h4>
					</div>
					<div class="panel-body">
						<p>
							<span class="c-999">产品名称：</span>${prdInfo.prdName}
						</p>
						<p>
							<span class="c-999">产品类型：</span>
							<c:if test="${prdInfo.prdType=='1'}">商户结果数据</c:if>
							<c:if test="${prdInfo.prdType=='2'}">联通原始数据</c:if>
							<c:if test="${prdInfo.prdType=='3'}">应用数据</c:if>
						</p>
						<p>
							<span class="c-999">产品描述：</span>${prdInfo.prdDes}
						</p><%--
						<p>
							<span class="c-999">数据资源需求：</span><a class="c-primary"
								onClick="down('${prdInfo.prdDatafile}')">${prdInfo.prdDataName}</a>
						</p>
						--%><p>
							<span class="c-999">其他需求：</span>${prdInfo.prdOthers}
						</p>
						<p>
							<span class="c-999">申请商户：</span>${prdInfo.creater}
						</p>
						<p>
							<span class="c-999">申请时间：</span>${createTime}
						</p>
 <!--                 数据资源需求 START-->
                  <div class="clearfix mt-10 mb-10"> <span class="fl">数据资源需求：</span>
                     <div class="fl dataNeedy detail">
                              <div class="data-box">
                                   <c:forEach var="bean" items="${prdDataList}">
	                                  <div class="data">
	                                      <span class="name">${bean.columnName}</span>
	                                      <span class="type">${bean.columnType}</span>
	                                      <span class="des">${bean.columnDesc}</span>
	                                  </div>
                                </c:forEach>
                              </div>
                              
                              
                          </div>
                          
                      <script>
                         //展示data信息
                            $(document).on("click",".data",function(){
                             
                            var info = '字段名：'+$(this).children(".name").text()+'<br/>字段类型：'+$(this).children(".type").text() +'<br/>字段描述：'+$(this).children(".des").text() ;

							 $.jBox.info(info, "字段信息",{ buttons: { '关闭': true} });
                                
                            });
                      </script>
                 
                 </div>  
<!--                 数据资源需求 END-->      

					</div>
				</article>
				<!--商户审核-->
				<article class="panel panel-primary mt-15 ">
					<div class="panel-header">
						<h4>产品审核</h4>
					</div>
					<div class="panel-body">
						<p>
							<span class="c-999">审核结果：</span>
							<c:choose>
								<c:when test="${prdInfo.prdStatus=='2'}">
									<span class="c-green f-16"><i
										class="fa fa-check-square-o "></i> 审核通过</span>
								</c:when>

								<c:when test="${prdInfo.prdStatus=='3'}">
									<span class="c-red f-16"><i class="fa fa-times "></i>
										审核不通过</span>
								</c:when>
							</c:choose>
						</p>
						<p>
							<span class="c-999">审核意见：</span>${prdInfo.checkOpin}
						</p>

						<p>
							<span class="c-999">计量单位：</span>
							<c:choose>
								<c:when test="${prdInfo.prdUnit=='1'}">
									<span>按数据结果条数计算</span>
								</c:when>

								<c:when test="${prdInfo.prdUnit=='2'}">
									<span>按数据结果容量计算(MB)</span>
								</c:when>
							</c:choose>
						</p>

						<p>
							<span class="c-999">计量单价(元)：</span>${prdInfo.prdPrice}
						</p>
						
						<c:if test="${prdInfo.prdType=='3'}">
						
							<p>
								<span class=" c-999">数据库IP：</span>${prdInfo.dataBaseIp}
							</p>
							
							<p>
								<span class=" c-999">数据库端口：</span>${prdInfo.dataBasePort}
							</p>
							
							<p>
								<span class=" c-999">数据库用户名：</span>${prdInfo.dataBaseUserName}
							</p>
							
							<p>
								<span class=" c-999">数据库密码：</span>${prdInfo.dataBaseUserPwd}
							</p>
							
						</c:if>

					</div>
				</article>
				<div class="btn-wrap mt-20 ml-60">
					<button class="btn btn-close"
						onClick="javascript:history.go(-1);return false;">关闭</button>
				</div>

			</div>
		</div>
	</div>


</body>
</html>