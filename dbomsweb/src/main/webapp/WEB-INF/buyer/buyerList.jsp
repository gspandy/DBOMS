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
<title>商户列表页-中国联通研究院大数据应用模型孵化后台管理系统</title>
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

<%-- main.js用于获取当前登录用户可操作功能数据 --%>
<script type="text/javascript" src="resources/easyui13/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/easyui13/locale/easyui-lang-en.js"></script>
<script type="text/javascript" src="resources/common/easyui-expand.js"></script>
<script type="text/javascript" src="resources/common/easyui-validate.js"></script>

<script type="text/javascript" src="resources/js/menu.js"></script>
<script type="text/javascript" src="resources/js/system/main.js"></script>
<link rel="stylesheet" href="js/jbox/jbox.css">
<script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script> 
<script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script>
<script type="text/javascript">
jQuery.browser={};
(function(){
	jQuery.browser.msie=false; 
	jQuery.browser.version=0;
	if(navigator.userAgent.match(/MSIE ([0-9]+)./)){
	 jQuery.browser.msie=true;
	 jQuery.browser.version=RegExp.$1;
	 }})();

/*
 * 查看商户
 */
function queryBuyer(bussId){
	window.location.href = "buyer/query.do?bussId="+bussId;
}

/*
 * 审核商户
 */
function auditBuyer(bussId){
	window.location.href = "buyer/audit.do?bussId="+bussId;
}

/**
 * 商户注销
 */
function deleteBuyer(){
	$("#Modal-delete").modal('hide');
	var bussId = $("#bussId").val();
	$.ajax({
		type : 'POST',
		url : 'buyer/deleteBuyer.do',
		data : {"bussId":bussId},
		datatype : 'json',
		success : function(data) {
			if(data.flag){
				$.jBox.tip('商户注销成功！','success');
				setTimeout("getList()",500);
			}else {
				$.jBox.error('商户注销失败！<br/>'+data.msg, '提示');
			}
		}
	});
}	 
</script>
<script type="text/javascript">
$(function(){
	getList();
});

var totalPage;
var totalRecords;
var page = 1;
var rows = 6;
var param = {};
function getList(){
	var bussAccount = $("#bussAccount").val()==undefined?"":$("#bussAccount").val();
	var bussCardNo = $("#bussCardNo").val()==undefined?"":$("#bussCardNo").val();
	var bussTeleNo = $("#bussTeleNo").val()==undefined?"":$("#bussTeleNo").val();
	var orgName = $("#orgName").val()==undefined?"":$("#orgName").val();
	var bussStatus = $("#bussStatus option:selected").val()==undefined?"":$("#bussStatus option:selected").val();
	param["bussAccount"] = bussAccount;
	param["bussCardNo"] = bussCardNo;
	param["bussMobileNo"] = bussTeleNo;
	param["orgName"] = orgName;
	param["bussStatus"] = bussStatus;
	param["page"] = page;
	param["rows"] = rows;
	getData();
}
function changPage(){
		page = $(".curr").text()==undefined||$(".curr").text()==""?1:$(".curr").text();
  		param["page"] = page;
  		getData();
}

function getData(){
	$.ajax({
		type: "POST",
		url: "buyer/getBussinfoList.do",
		data:param,
		dataType: "json",
		success: function(data){
			if (data!=null){
				var buyerList = data.bussInfoList;
				totalPage = data.totalPage;
				totalRecords = data.totalNumber;
				$("#buyerList").empty();
				var bussStatus = "";
				var itStatus = "<b class=\"\">未配置</b>";
				var authStatus = "<b class=\"\">未配置</b>";
				var dataStatus = "<b class=\"\">未配置</b>";
				var auditStatus = "";
				var temp = "<thead><tr><th>商户ID</th><th>商户姓名</th><th>商户账户</th><th>身份证号码</th><th>联系电话</th><th>联系地址</th><th>归属机构</th><th>审核状态</th><th>操作权限状态</th><th>数据权限状态</th><th width=\"100\">操作</th></tr></thead>";
				for(i in buyerList){
					if(buyerList[i].bussStatus=='01'){
						bussStatus = "<b class=\"c-orange\">未审核</b>";
						auditStatus = "01";
					}else if(buyerList[i].bussStatus=='02'){
						bussStatus = "<b class=\"c-green\">审核通过</b>"
						auditStatus = "02";
					}else if(buyerList[i].bussStatus=='03'){
						bussStatus = "<b class=\"c-red\">审核不通过</b>"
						auditStatus = "03";
					}else if(buyerList[i].bussStatus=='04'){
						bussStatus = "<b class=\"c-red\">失效</b>"
						auditStatus = "04";
					}
					
					if (buyerList[i].leeauthStatus=='WAIT'){
						authStatus = "<b >待审核</b>";
					}else if (buyerList[i].leeauthStatus=='PASS'){
						authStatus = "<b class=\"c-green\">审核通过</b>";
					}else if (buyerList[i].leeauthStatus=='NOPASS'){
						authStatus = "<b class=\"c-red\">审核不通过</b>";
					}
					
					if(buyerList[i].datapemStatus=='0'){
						dataStatus = "<b class=\"c-orange\">已保存</b>";
					}else if (buyerList[i].datapemStatus=='1'){
						dataStatus = "<b >待审核</b>";
					}else if (buyerList[i].datapemStatus=='2'){
						dataStatus = "<b class=\"c-green\">审核通过</b>";
					}else if (buyerList[i].datapemStatus=='3'){
						dataStatus = "<b class=\"c-red\">审核不通过</b>";
					}
					
					if(auditStatus == "04"){
						temp+="<tr>"
							+ "<td>"+buyerList[i].bussId+"</td>"
							+ "<td>"+buyerList[i].bussName+"</td>"
							+ "<td>"+buyerList[i].bussAccount+"</td>"
							+ "<td>"+buyerList[i].bussCredNo+"</td>"
							+ "<td>"+buyerList[i].bussTeleNo+"</td>"
							+ "<td>"+buyerList[i].bussAddress+"</td>"
							+ "<td>"+buyerList[i].orgName+"</td>"
							+ "<td>"+bussStatus+"</td>"
							+ "<td>"+authStatus+"</td>"
							+ "<td>"+dataStatus+"</td>"
							+ "<td>"
	  	                  	+'<c:if test="${sessionScope.funOperate[\'BUS_LIST_CHECK\'] }">'
		  					+ "<a class=\"btn btn-op\" onclick=\"queryBuyer('"+buyerList[i].bussId+"')\" title=\"查看\"><i class=\"fa fa-search\"></i></a>"
		  					+'</c:if>'
		  					+ "</td>"
		  				+"</tr>"
					}else if(auditStatus == "01"){
						temp+="<tr>"
							+ "<td>"+buyerList[i].bussId+"</td>"
							+ "<td>"+buyerList[i].bussName+"</td>"
							+ "<td>"+buyerList[i].bussAccount+"</td>"
							+ "<td>"+buyerList[i].bussCredNo+"</td>"
							+ "<td>"+buyerList[i].bussTeleNo+"</td>"
							+ "<td>"+buyerList[i].bussAddress+"</td>"
							+ "<td>"+buyerList[i].orgName+"</td>"
							+ "<td>"+bussStatus+"</td>"
							
							+ "<td>"+authStatus+"</td>"
							+ "<td>"+dataStatus+"</td>"
							+ "<td>"
	  	                  	+'<c:if test="${sessionScope.funOperate[\'BUS_LIST_CHECK\'] }">'
		  					+ "<a class=\"btn btn-op\" onclick=\"queryBuyer('"+buyerList[i].bussId+"')\" title=\"查看\"><i class=\"fa fa-search\"></i></a>"
		  					+'</c:if>'
	  	                  	+'<c:if test="${sessionScope.funOperate[\'BUS_LIST_LOGOFF\'] }">'
		  					+ "<a class=\"btn btn-op\" onclick=\"preDelete('"+buyerList[i].bussId+"')\" data-toggle=\"modal\" href=\"#Modal-delete\" title=\"注销\"><i class=\"fa fa-power-off\"></i></a>"
		  					+'</c:if>'
	  	                  	+'<c:if test="${sessionScope.funOperate[\'BUS_LIST_AUDIT\'] }">'
		  					+ "<a class=\"btn btn-op\" onClick=\"auditBuyer('"+buyerList[i].bussId+"')\" title=\"审核\"><i class=\"fa fa-legal\"></i></a>"
		  					+'</c:if>'
		  					+ "</td>"
		  				+"</tr>"
					}else{
						temp+="<tr>"
							+ "<td>"+buyerList[i].bussId+"</td>"
							+ "<td>"+buyerList[i].bussName+"</td>"
							+ "<td>"+buyerList[i].bussAccount+"</td>"
							+ "<td>"+buyerList[i].bussCredNo+"</td>"
							+ "<td>"+buyerList[i].bussTeleNo+"</td>"
							+ "<td>"+buyerList[i].bussAddress+"</td>"
							+ "<td>"+buyerList[i].orgName+"</td>"
							+ "<td>"+bussStatus+"</td>"
							
							+ "<td>"+authStatus+"</td>"
							+ "<td>"+dataStatus+"</td>"
							+ "<td>"
	  	                  	+'<c:if test="${sessionScope.funOperate[\'BUS_LIST_CHECK\'] }">'
		  					+ "<a class=\"btn btn-op\" onclick=\"queryBuyer('"+buyerList[i].bussId+"')\" title=\"查看\"><i class=\"fa fa-search\"></i></a>"
		  					+'</c:if>'
	  	                  	+'<c:if test="${sessionScope.funOperate[\'BUS_LIST_LOGOFF\'] }">'
		  					+ "<a class=\"btn btn-op\" onclick=\"preDelete('"+buyerList[i].bussId+"')\" data-toggle=\"modal\" href=\"#Modal-delete\" title=\"注销\"><i class=\"fa fa-power-off\"></i></a>"
		  					+'</c:if>'
		  					+ "</td>"
		  				+"</tr>"
					}
					
						
				}
				$("#buyerList").append(temp);
  					toPage();
			}else {
				
			}
		}
	});
}

function preDelete(bussId){
	$("#bussId").val(bussId);
}

<!-- 加载分页控件 -->
function toPage(){
//alert("总页数"+totalPage+"-总数据"+totalRecords+"-当前页"+page);
		kkpager.total = totalPage;
		kkpager.totalRecords = totalRecords;
		kkpager.pno = page;
		kkpager.hasPrv = (kkpager.pno > 1)
		kkpager.hasNext = (kkpager.pno < kkpager.total);
		kkpager.generPageHtml({
		     pno : page,
		     mode : 'click', 
		     total : totalPage,  
		     totalRecords : totalRecords,
		     click : function(n){
		         this.selectPage(n);
		  		 changPage();
		         return false;
		     },
		     getHref : function(n){
		         return 'javascript:void(0)';
		     }
			 });
	}
</script>
</head>
<body>
	<!-----------------------HEADER----------------------------> 
	  
	<!------------------------SIDEBAR-------------------------->
     
     
     <!-------------------------CONT---------------------------->  
     
    <div class="List-cont box-cont" >
            <div class="panel panel-default">
                <div class="panel-header">
                  <h4>商户资料列表</h4>
                </div>
                <div class="panel-body">
                    <!--搜索模块-->
                   <div class="search-form">
                       <form id="searchForm">
                       	 <input type="hidden" id="bussId" value=""/>
			             <div class="row cl">
			                 <div class="col-sm-3">
			                     <label class="form-label" for="">商户账号：</label><div class="formControls"><input type="text" class="input-text" id="bussAccount" ></div>
			                 </div>
			                 <div class="col-sm-3">
			                      <label class="form-label" for="">身份证号码：</label>
			                      <div class="formControls "><input type="text" class="input-text " id="bussCardNo"></div>
			                 </div>
			                 <div class="col-sm-3">
			                      <label class="form-label" for="">联系号码：</label>
			                      <div class="formControls "><input type="text" class="input-text " id="bussTeleNo"></div>
			                 </div>
			             </div>
			             <div class="row mt-10 cl">
			                 <div class="col-sm-3">
			                     <label class="form-label" for="">归属机构：</label><div class="formControls"><input type="text" class="input-text" id="orgName"></div>
			                 </div>
			                 <div class="col-sm-3">
			                     <label class="form-label" for="">审核状态：</label>
			                     <div class="formControls">
		                             <select class="input-text" name="" id="bussStatus">
		                                 <option value="">全部</option>
		                                 <option value="01">未审核</option>
		                                 <option value="02">审核通过</option>
		                                 <option value="03">审核不通过</option>
		                                 <option value="04">失效</option>
		                             </select>
			                     </div>
			                 </div>
			                 <!--  
			                 <div class="col-sm-3 ">
			                     <label class="form-label" for="">商户配置状态：</label>
			                     <div class="formControls">
		                             <select class="input-text" name="" id="configStatus"">
		                                 <option value="">全部</option>
		                                 <option value="">未配置</option>
		                                 <option value="">配置成功</option>
		                                 <option value="">配置失败</option>
		                             </select>
			                     </div>
			                 </div>
			                 -->
			                 <div class="col-sm-3 text-c" style="float: right;">
			                     <a href="javascript:void(0)" class="btn btn-primary " onclick="getList();" style="float: right;width: initial;">查询</a>
			                 </div>
			             </div>
       				</form> 
                </div>
   				<!--列表-->
                <table id="buyerList" class="table table-primary mt-30" >
                	
                </table>   
                <!--分页-->
               <div id="kkpager"></div>
             </div> 
                                                  
        </div>
        
     </div>
    <!------------------------FOOTER----------------------------> 
    
  
	<!--删除对话框--->
		<div id="Modal-delete" class="modal w300 hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			 <div class="modal-header">
			    <h3 id="myModalLabel">注销</h3><a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i></a>
			 </div>
			  <div class="modal-body">
			 	<p>确定注销此商户资料？</p>
			 </div>
			  <div class="modal-footer text-c">
			  	<button class="btn btn-close" data-dismiss="modal" aria-hidden="true">关闭</button>
			 	<button class="btn btn-primary" onclick="deleteBuyer();">确定</button> 
			 </div>
		</div>	
</body>
</html>
