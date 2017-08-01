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
<title>商户APP详情页-中国联通研究院大数据应用模型孵化后台管理系统</title>
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
  	</script>
	<!-----------------------HEADER----------------------------> 
	  
	<!------------------------SIDEBAR-------------------------->
     
     
    <!-------------------------CONT---------------------------->  
    <div class="Detail-cont box-cont">
        <div class="panel panel-default">
              <div class="panel-header">
                  <h4>商户信息</h4>
              </div>
              <div class="panel-body">

	               <article class="panel panel-primary mt-10">
	                   <div class="panel-header">
	                       <h4>商户APP资料</h4></div>
	                   <div class="panel-body">
	                       <p><span class="c-999">商户ID：</span>${appInfo.bussId}</p> 
	                       <p><span class="c-999">商户姓名：</span>${appInfo.bussName}</p> 
	                       <p><span class="c-999">商户账号：</span>${appInfo.tenantName}</p> 
	                       <p><span class="c-999">APP名称：</span>${appInfo.appName}</p> 
	                       <p><span class="c-999">APP描述：</span>${appInfo.appDes}</p> 
	                       <p><span class="c-999">APP URL：</span>${appInfo.appurl}</p> 
	                       <p><span class="c-999">APP图片：</span>${appInfo.appImg}</p> 
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
