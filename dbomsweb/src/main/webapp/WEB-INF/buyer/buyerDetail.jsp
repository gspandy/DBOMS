<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort() + path1 + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商户详情页-中国联通研究院大数据应用模型孵化后台管理系统</title>
<base href="<%=basePath1 %>" />

<link rel="Bookmark" href="img/favorite.png" > 
<link rel="Shortcut Icon" href="img/favorite.png" />
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/general.css">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/core.css">
<!--  
<link rel="stylesheet" href="css/components.css">
-->
<link rel="stylesheet" href="js/kkpager/kkpager_blue.css">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script> 
<script type="text/javascript" src="js/validate/jquery.validate.min.js"></script> 
<script type="text/javascript" src="js/validate/additional-methods.min.js"></script> 
<script type="text/javascript" src="js/validate/messages_zh.js"></script> 
<script type="text/javascript" src="js/kkpager/kkpager.min.js"></script> 
<script type="text/javascript" src="js/modal/bootstrap-modal.js"></script> 
<script type="text/javascript" src="js/modal/bootstrap-modalmanager.js"></script> 

<script type="text/javascript" src="resources/jquery/jquery.sizes.js"></script>
<script type="text/javascript" src="resources/jquery/jlayout.border.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.jlayout.js"></script>
<script type="text/javascript" src="resources/js/common_jq.js"></script>
<script type="text/javascript" src="resources/window/lhgdialog.min.js?skin=chrome"></script>
<%-- main.js用于获取当前登录用户可操作功能数据 --%>
<script type="text/javascript" src="resources/js/system/main.js"></script>
<script type="text/javascript" src="resources/js/menu.js"></script>

</head>
<body>
  	<script type="text/javascript">
	  	function down(fileCode){
	  		if (fileCode==null) {
	  	  		return false;
			}
	  		$("#fileCode").val(fileCode);
	  		$("#downFile").submit();
	  	}
  	</script>
	<form style="display:none;" id="downFile" name="downFile" action="buyer/downFile.do" method="post">
		<input id="fileCode" name="fileCode" value="" />
	</form>
	<!-----------------------HEADER----------------------------> 
	  
	<!------------------------SIDEBAR-------------------------->
     
     
    <!-------------------------CONT---------------------------->  
    <div class="Detail-cont box-cont">
        <div class="panel panel-default">
              <div class="panel-header">
                  <h4>商户信息</h4>
              </div>
              <div class="panel-body">

	               <!--商户注册资料-->
	               <article class="panel panel-primary mt-10">
	                   <div class="panel-header">
	                       <h4>商户注册资料</h4></div>
	                   <div class="panel-body">
	                       <p><span class="c-999">账号：</span>${vo.bussAccount}</p> 
	                       <p><span class="c-999">姓名：</span>${vo.bussName}</p> 
	                       <p><span class="c-999">手机号码：</span>${vo.bussMobileNo}</p> 
	                       <p><span class="c-999">身份证号码：</span>${vo.bussMobileNo}</p> 
	                       <p><span class="c-999">联系地址：</span>${vo.bussAddress}</p> 
	                       <p><span class="c-999">EMAIL：</span>${vo.bussEmail}</p> 
	                       <p><span class="c-999">机构名称：</span>${vo.orgName}</p> 
	                       <p><span class="c-999">工商编号：</span>${vo.bussinessNum}</p> 
	                       <p><span class="c-999">联系电话：</span>${vo.bussTeleNo}</p> 
	                       <p><span class="c-999">证件复印件：</span><a class="c-primary" onClick="down('${vo.fileId}');">${vo.uploadFileName}</a></p> 
	                   </div>
	               </article>
	               <!--商户审核-->
	
	               <article class="panel panel-primary mt-15 ">
	                   <div class="panel-header">
	                       <h4>商户审核</h4>
	                   </div>
	                   <div class="panel-body">
		                   	<p><span class="c-999">审核结果：</span>
			                   	<c:choose>
			                   		<c:when test="${vo.bussStatus=='02'}">
			                   			<span class="c-green f-16"><i class="fa fa-check-square-o "></i> 审核通过</span>
			                   		</c:when>
			                   		
			                   		<c:when test="${vo.bussStatus=='03'}">
			                   			<span class="c-red f-16"><i class="fa fa-times "></i> 审核不通过</span>
			                   		</c:when>
			                   	</c:choose>
		                   	</p>
	                       <p>
	                           <span class="c-999">审核意见：</span>${vo.suggestion}
	                       </p>
	                   </div>
	               </article>
	               <div class="btn-wrap pl-20 mt-10"> <a class="btn btn-secondary ml-10" href="javascript:history.go(-1);">关闭</a>
	               </div>
             </div>
         </div>
    </div>
    
    <!------------------------FOOTER----------------------------> 
    	
</body>
</html>
