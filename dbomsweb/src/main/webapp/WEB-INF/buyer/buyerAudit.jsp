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
<title>商户审核页-中国联通研究院大数据应用模型孵化后台管理系统</title>
<base href="<%=basePath1 %>" />

<link rel="Bookmark" href="img/favorite.png" > 
<link rel="Shortcut Icon" href="img/favorite.png" />
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/general.css">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/core.css">
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
<link rel="stylesheet" href="js/jbox/jbox.css">
<script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script> 
<script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script>
<script>

/***审核操作***/
function audit(bussId,type){
	/***
	if($("#suggestion").val()==""){
		alert("请输入审核意见");
	}***/
	var sugg = $("#suggestion").val();
	var bussDataType = $("#bussDataType").val();
	var jasonPara ={"bussId":bussId,"suggetion":sugg,"auditType":type,"bussDataType":bussDataType};
	$.ajax({
		type: "POST",
		url: "buyer/doAudit.do",
		data: jasonPara,
		datatype: "json",
		success: function(data){
			if (data.flag) {//操作成功
				$.jBox.tip('审核成功！','success');
				setTimeout('window.location.href="<%=basePath1 %>buyer/index.do"',500);
			} else {
				$.jBox.error('审核失败！<br/>'+data.msg, '提示');
			}
		}
   	});
}
</script>
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
              <h4>商户审核</h4>
            </div>
            <div class="panel-body">
                
                <!--商户注册资料-->
                <article class="panel panel-primary mt-10">
                    <div class="panel-header"><h4>商户注册资料</h4></div>
                    <div class="panel-body">
                       <p><span class="c-999">账号：</span>${vo.bussAccount}</p> 
                       <p><span class="c-999">姓名：</span>${vo.bussName}</p> 
                       <p><span class="c-999">手机号码：</span>${vo.bussMobileNo}</p> 
                       <p><span class="c-999">号身份证码：</span>${vo.bussMobileNo}</p> 
                       <p><span class="c-999">联系地址：</span>${vo.bussAddress}</p> 
                       <p><span class="c-999">EMAIL：</span>${vo.bussEmail}</p> 
                       <p><span class="c-999">机构名称：</span>${vo.orgName}</p> 
                       <p><span class="c-999">工商编号：</span>${vo.bussinessNum}</p> 
                       <p><span class="c-999">联系电话：</span>${vo.bussTeleNo}</p> 
                       <p><span class="c-999">证件复印件：</span><a class="c-primary" onClick="down('${vo.fileId}');">${vo.uploadFileName}</a></p> 
                    </div>
                </article>
                <!--商户审核-->
                <article class="panel panel-primary mt-15">
                    <div class="panel-header">
                        <h4>商户审核</h4>
                    </div><%--
                    <div class="panel-body">
                      <div class="sh-form-group mt-10 mb-10 cl">
							<span class="fl c-999">商户数据类型：</span> 
							<select id="bussDataType"
								name="bussDataType" class="input-text" >
								<c:forEach items="${bussDateType}" var="bussDataType">
									<option value="${bussDataType.key}" >${bussDataType.value}</option>
								</c:forEach>
							</select> <span style="color: #ee3170;font-size: 14px;display:none"
								id="prdUnitTip" name="prdUnitTip"></span>
					 </div>
					</div>
                    --%><div class="panel-body">
                        <div class="sh-form-group mt-10 mb-10 cl">
                          <span class="fl c-999">审核意见：</span>
                          <textarea class="textarea fl" name="" id="suggestion" cols="20" rows="10"></textarea>
                        </div>
                    
                    </div>
                </article>
                <div class="btn-wrap pl-20 mt-10">
                        <a class="btn btn-primary" href="javascript:void(0);" onclick="audit('${vo.bussId}','02')">审核通过</a>
                        <a class="btn btn-secondary ml-10" href="javascript:void(0);" onclick="audit('${vo.bussId}','03')">审核不通过</a>    
                        <a class="btn btn-secondary ml-10" href="javascript:history.go(-1);" >返回</a>    
                </div>
            </div> 
        </div>                                      
               
     </div>
    <!------------------------FOOTER----------------------------> 
    
</body>
</html>
