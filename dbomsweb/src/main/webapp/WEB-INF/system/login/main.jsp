<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort() + path1 + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>首页-中国联通研究院大数据应用模型孵化后台管理系统</title>
<base href="<%=basePath1 %>" />

    <link rel="Bookmark" href="img/favorite.png" > 
    <link rel="Shortcut Icon" href="img/favorite.png" />
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/core.css">
    <link rel="stylesheet" href="js/jbox/jbox.css">
    <script type="text/javascript" src="js/jquery-2.1.4.js"></script> 
    <script type="text/javascript" src="js/modal/bootstrap-modal.js"></script> 
    <script type="text/javascript" src="js/modal/bootstrap-modalmanager.js"></script> 
    <script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script> 
    <script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script> 

<script type="text/javascript" src="resources/jquery/jquery.sizes.js"></script>
<script type="text/javascript" src="resources/jquery/jlayout.border.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.jlayout.js"></script>
<script type="text/javascript" src="resources/js/common_jq.js"></script>
<script type="text/javascript" src="resources/window/lhgdialog.min.js?skin=chrome"></script>
<%-- main.js用于获取当前登录用户可操作功能数据 --%>
<script type="text/javascript" src="resources/js/system/main.js"></script>
<script type="text/javascript" src="resources/js/menu.js"></script>

<script type="text/javascript">
jQuery(function($){
	toShow();
	$("#updatePwd").click(function(){
		$('#oldPwd').val('');
		$('#newPwd').val('');
		$('#reNewPwd').val('');
		$("#oldPwdErorr").empty();
		$("#newPwdErorr").empty();
		$("#reNewPwdErorr").empty();
	});
});
function toShow(){
	$("#sidebar a").click(function(){
		$(".index-cont").remove();
		$("#iframe_right").show();
		$("#sidebar a").removeClass("active");
		$(this).addClass("active");
	});
	return true;
}
</script>
</head>
<body>
  <!-----------------------HEADER----------------------------> 
  <header class="navbar-wrapper navbar-fixed">
      <div class="navbar navbar-blue">
          <div class="container-fluid cl">
              <a class="logo navbar-logo" href="javascript:history.go(0)"><img src="img/logo.png" alt="logo"></a>
              <a class="logo navbar-slogan" href="javascript:history.go(0)">中国联通大数据应用模型孵化平台</a>
              <nav class="navbar-userbar fr">
                 <span class="btn btn-link-white mr-15"><i class="fa fa-user"></i> ${sessionScope.operator.operName }<a href="sysLogin/cancellation.do">[ 退出 ]</a></span>
                 <a id="updatePwd" class="btn btn-link-white" data-toggle="modal" href="#Modal-update" title="修改密码"><i class="fa fa-lock"></i> <span class="f-16">修改密码</span></a>
              </nav>
          </div>
      </div>
  </header>
  
 <!------------------------SIDEBAR-------------------------->
     <aside class="aside">
         <ul id="sidebar" class="sidebar">
         	<c:if test="${menus ne null }">
         		<c:forEach items="${menus }" var="menu" varStatus="vs_idx">
		             <li class="item">
		                 <h4>
		                 	<c:choose>
		                 		<c:when test="${menu.menuName eq '商户管理'}"><i class="fa fa-calendar"></i>${menu.menuName}</c:when>
		                 		<c:when test="${menu.menuName eq '产品管理'}"><i class="fa fa-tags"></i>${menu.menuName}</c:when>
		                 		<c:when test="${menu.menuName eq '工单管理'}"><i class="fa fa-map"></i>${menu.menuName}</c:when>
		                 		<c:when test="${menu.menuName eq '计费管理'}"><i class="fa fa-yen"></i>${menu.menuName}</c:when>
		                 		<c:when test="${menu.menuName eq '计量管理'}"><i class="fa fa-hourglass-end"></i>${menu.menuName}</c:when>
		                 		<c:when test="${menu.menuName eq '文件接口管理'}"><i class="fa fa-file-o"></i>${menu.menuName}</c:when>
		                 		<c:otherwise><i class="fa fa-gear"></i>${menu.menuName}</c:otherwise>
		                 	</c:choose>
		                 </h4>
		                 <div class="info">
		                 	<c:if test="${menu.child }">
		                 	<c:if test="${menu.childMenus ne null }">
		                 		<c:forEach items="${menu.childMenus }" var="child" varStatus="child_idx">
		                 			<a onClick="toShow();" href="${child.menuUri }" target="iframe_right">${child.menuName }</a>
		                 		</c:forEach>
		                 	</c:if>
		                 	</c:if>
		                 </div>
		             </li>
         		</c:forEach>
         	</c:if>
         </ul>
     </aside>
  <!-------------------------CONT---------------------------->  
	<div id="div_main_right" class="article-box">
	    <div class="index-cont ">
           <div class="panel user-panel">
                 <div class="panel-header">
                    <article class=" row cl">
                       <div class="col-sm-5">
                            <div class="fl pic ml-80">
                           <a>
                                <img width="90" height="90"  src="img/user_pic.jpg" alt="用户头像">
                                 <p class="text-c f-16">${sessionScope.operator.operName }</p>
                           </a>    
                        </div>
                        <div class="fl info mt-20 ml-15 f-14">
                            <p>登录时间：<br><time><fmt:formatDate value="${sessionScope.operator.lastLoginTime}" type="both" /></time></p>
                        </div>
                       </div>
                       <div class="col-sm-7">
                              <ul class="state-list ">
                        <li> 
                            <span class="icon icon1"> <b class="c-orange">${bussCount_TODO }</b></span>
                             <p>待审核商户</p>
                         </li>           
                        <li> 
                            <span class="icon icon2"> <b class="c-orange">${bussCount_DONE }</b></span>
                             <p>已审核商户</p>
                         </li>  
                         <li> 
                            <span class="icon icon3"> <b class="c-orange">${prdCount_TODO }</b></span>
                             <p>待审核产品</p>
                         </li>  
                         <li> 
                            <span class="icon icon4"> <b class="c-orange">${prdCount_DONE }</b></span>
                             <p>已审核产品</p>
                         </li>  
                    </ul>
                       </div>
                    </article>
                </div>
                <div class="panel-body panel-bg"></div>
          </div>                                     
       </div>   
  		<iframe id="iframe_right" name="iframe_right" style="min-height: 700px;" hidden="true" src="" border="0" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"  allowtransparency="yes" width="100%">
  		</iframe> 
  		<script type="text/javascript">
            //注意：下面的代码是放在和iframe同一个页面调用,放在iframe下面
            $("#iframe_right").load(function () {
                var mainheight = $(this).contents().find(".box-cont").height() + 30;
                $(this).height(mainheight);
            });
        </script>
    </div>
<!------------------------FOOTER---------------------------->
  <footer class="footer">
      <div class="container-fluid">
		<p>Copyright &copy;1999-2016&nbsp;中国联通&nbsp;版权所有&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        经营许可编号：<a href="http://www.miitbeian.gov.cn/" target="_blank" rel="nofollow">***ICP备****号</a>
		</p>
	</div>
  </footer>
<!--------------------------MODAL---------------------------->
<script type="text/javascript">
function checkOldPwd(){
	$("#oldPwdErorr").empty();
	if($("#oldPwd").val()==null||$("#oldPwd").val()==""){
		$("#oldPwdErorr").text("请输入旧密码！");
		$('#oldPwd').focus();
		return true;
	}
	return false;
}
function checkNewPwd(){
	//var patrn = /^[A-Za-z0-9]{6,16}$/;
	var patrn = /^((?=.*[0-9].*)(?=.*[A-Za-z].*))[0-9A-Za-z]{6,16}$/;
	$("#newPwdErorr").empty();
	if($("#newPwd").val()==null||$("#newPwd").val()==""){
		$("#newPwdErorr").text("请输入新密码！");
		return true;
	}
	if (!patrn.test($("#newPwd").val())){
		$("#newPwdErorr").text("密码必须是数字和字母的组合，6~16位！");
		return true;
	}
	return false;
}
function checkReNewPwd(){
	$("#reNewPwdErorr").empty();
	if($("#reNewPwd").val()==null||$("#reNewPwd").val()==""){
		$("#reNewPwdErorr").text("请输入确认密码！");
		return true;
	}
	if ($("#reNewPwd").val()!=$("#newPwd").val()){
		$("#reNewPwdErorr").text("两次输入的密码不一致！");
		return true;
	}
	return false;
}
function savePwd(){
	if (checkOldPwd()) return false;
	if (checkNewPwd()) return false;
	if (checkReNewPwd()) return false;
	$.ajax({
        type : 'POST',
        url : 'operator/operatorPwdUpdate.do',
        data : $('#pwdForm').serialize(),
		datatype : 'json',
		async : false,
        success : function(msg) {
        	if (msg.flag){
                $.jBox.tip(msg.msg,'success');
        		$("#Modal-update").modal('hide');
        	} else {
                $.jBox.error(msg.msg, '提示');
        	}
        }
	});
}
</script>
<!--修改密码对话框--->
<div id="Modal-update" class="modal w600 hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 <div class="modal-header">
    <h3 id="myModalLabel">修改密码</h3><a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i></a>
 </div>
  <div class="modal-body">
	<form action="" id="pwdForm" name="pwdForm">
		<input type="hidden" id="id" name="id" value="${sessionScope.operator.operId }" />
		<div class="form-group cl">
			<label class=" fl">旧密码：</label>
			<input class="input-text fl" type="password" value="" name="oldPwd" id="oldPwd" onblur="checkOldPwd();">
		</div>
		<div style="width:250px; height: 16px; margin:0 auto; color:red; text-align: left;padding-left: 65px;">
			<label id="oldPwdErorr"></label>
		</div>
		<div class="form-group cl">
			<label class=" fl">新密码：</label>
			<input class="input-text fl" type="password" value="" name="newPwd" id="newPwd" onblur="checkNewPwd();">
		</div>
		<div style="width:250px; height: 16px; margin:0 auto; color:red; text-align: left;padding-left: 65px;">
			<label id="newPwdErorr"></label>
		</div>
		<div class="form-group cl">
			<label class=" fl">确认密码：</label>
			<input class="input-text fl" type="password" value="" name="reNewPwd" id="reNewPwd" onblur="checkReNewPwd();">
		</div>
		<div style="width:250px; height: 16px; margin:0 auto; color:red; text-align: left;padding-left: 65px;">
			<label id="reNewPwdErorr"></label>
		</div>
	</form>
 </div>
  <div class="modal-footer text-c">
  <button class="btn btn-close" data-dismiss="modal" aria-hidden="true">取消</button>
 <button onclick="savePwd();" class="btn btn-primary">保存</button> 
 </div>
</div>
</body>
</html>
