<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path1 = request.getContextPath();
	String basePath1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path1 + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>数据权限配置页-中国联通研究院大数据应用模型孵化后台管理系统</title>
<base href="<%=basePath1%>" />
<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
		<link rel="stylesheet" href="css/general.css">
			<link rel="stylesheet" href="css/layout.css">
				<link rel="stylesheet" href="css/core.css">
					<link rel="stylesheet" href="js/kkpager/kkpager_blue.css">
						<link rel="stylesheet" href="js/jbox/jbox.css">
							<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
							<script type="text/javascript"
								src="js/validate/jquery.validate.min.js"></script>
							<script type="text/javascript"
								src="js/validate/additional-methods.min.js"></script>
							<script type="text/javascript" src="js/validate/messages_zh.js"></script>
							<script type="text/javascript" src="js/kkpager/kkpager.min.js"></script>
							<script type="text/javascript" src="js/modal/bootstrap-modal.js"></script>
							<script type="text/javascript"
								src="js/modal/bootstrap-modalmanager.js"></script>
							<script type="text/javascript"
								src="js/jbox/jquery.jBox-2.3.min.js"></script>
							<script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script>

							<script type="text/javascript" src="js/app.js"></script>
</head>
<body style="min-height: 900px;">

<script type="text/javascript">
	$(function(){
		searchList();
	});
	function beforDel(id){
		$("#id").val(id);
	}
	function del(){
		$("#Modal-delete").modal('hide');
		var id = $("#id").val();
  		$.ajax({
  			type: "POST",
  			url: "sys/delConfig.do",
  			data: {id:id},
  			datatype: "json",
  			success: function(data){
  				if (data.flag) {
	  				searchList();
				}else{
		            $.jBox.error(data.msg, '提示');
				}
  			}
  		});
	}
</script>
<input type="hidden" id="id" />
<!-- 获取列表 -->
<script type="text/javascript">
	var totalPage;
	var totalRecords;
	var page = 1;
	var param = {};
	function searchList(){
		page = 1;
  		var dataFileName = $("#dataFileName").val()==undefined?"":$("#dataFileName").val();
  		var dataType = $("#dataType").val()==undefined?"":$("#dataType").val();
  		//var status = $("#status option:selected").val()==undefined?"":$("#status option:selected").val();
  		param["dataFileName"] = dataFileName.trim();
  		param["dataType"] = dataType.trim();
  		//param["status"] = status;
  		param["page"] = page;
  		getList();
	}
	function changPage(){
		page = $(".curr").text()==undefined||$(".curr").text()==""?1:$(".curr").text();
  		param["page"] = page;
  		getList();
	}
	function getList(){
  		$.ajax({
  			type: "POST",
  			url: "sys/getList.do",
  			data: param,
  			datatype: "json",
  			success: function(data){
  				if (data!=null) {
  					$("#dataConfigList").empty();
  					
  					var dataConfigList = data.rows;
  					totalPage = data.totalPage;
  					totalRecords = data.totalNumber;
  					
  					var temp = "<thead><tr><th width=20%>数据文件名</th><th width=20%>数据类型</th><th width=15%>操作</th></tr></thead>";
  					for(x in dataConfigList){
  						temp+="<tr>"
  					    +"<td>"+dataConfigList[x].name+"</td>"
  					    +"<td>"+dataConfigList[x].type+"</td>"
  					    +"<td>"
  					    +"<c:if test='${sessionScope.funOperate["SYS_USER_ROLE_CHECK"] }'>"
						+"<a class=\"btn btn-op\" onClick=\"configDetail(\'"+dataConfigList[x].id+"\')\" data-toggle=\"modal\" href=\"#Modal-detail\" title=\"查看\"><i class=\"fa fa-search\"></i></a>"
						+"</c:if>"
						+"<c:if test='${sessionScope.funOperate["SYS_USER_ROLE_UPDATE"] }'>"
						+"<a class=\"btn btn-op ml-10\" href=\"sys/dataCfgAdd.do?id="+dataConfigList[x].id+"&operType=0\" title=\"修改\"><i class=\"fa fa-edit\"></i></a>"
						+"</c:if>"
						+"<c:if test='${sessionScope.funOperate["SYS_USER_ROLE_DELETE"] }'>"
						+"<a class=\"btn btn-op ml-10\" onClick=\"beforDel('"+dataConfigList[x].id+"');\" data-toggle=\"modal\" href=\"#Modal-delete\" title=\"删除\"><i class=\"fa fa-trash\"></i></a>"
  					    +"</c:if>"
  					    +"</td>"
  					+"</tr>"
  					}
  					$("#dataConfigList").append(temp);
  					toPage();
  				}else{
  					
  				}
  			}
  		});
	}
</script>
<!-- 加载分页控件 -->
<script type="text/javascript">
	function toPage(){
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
<!-------------------------CONT---------------------------->  
	<div class="List-cont box-cont">
		<div class="panel panel-default">
			<div class="panel-header">
				<h4>数据权限管理</h4>
			</div>
			<div class="panel-body">
				<div class="panel panel-primary">
					<div class="panel-header">
						<h4>数据权限列表</h4>
					</div>
					<div class="panel-body">
						<!--搜索模块-->
						<div class="search-form">
							<form id="searchForm">
								<div class="row mt-10 cl">
									<div class="col-sm-3">
										<label class="form-label" for="">数据文件名：</label>
										<div class="formControls ">
											<input id="dataFileName" name="dataFileName" type="text" class="input-text ">
										</div>
									</div>
									<div class="col-sm-3">
										<label class="form-label" for="">数据类型：</label>
										<div class="formControls ">
											<input id="dataType" name="dataType" type="text" class="input-text ">
										</div>
									</div>
									<div class="col-sm-6  text-r">
										<a onClick="searchList();" class="btn btn-primary ">查询</a>
										<a href="sys/dataCfgAdd.do?operType=1" class="btn btn-secondary">新增</a>
									</div>
								</div>
							</form>
						</div>
						<!--列表-->
						<table id="dataConfigList" class="table table-primary mt-30"></table> 
						
						<!--分页-->
						<div id="kkpager"></div>

					</div>
				</div>

			</div>
		</div>
	</div>

	<!--查看对话框--->
 	<script type="text/javascript">
 		function configDetail(id){
  			$.ajax({
  				type: "POST",
  				url: "sys/toConfigDetail.do",
  				data: {id:id},
  				datatype: "json",
  				success: function(data){
  					$("#dataNameDetail").val(data.name);
  					$("#dataTypeDetail").val(data.type);
  					var strs = new Array();
  					var str = data.columns;
  					$("#columnDiv").html('');					
	  				var iHtml = '';
	  				if(str != null){
	  					strs = str.split(",");
	  					for (i=0;i<strs.length;i++){
	  						var tmp = '<div class="data"><span class="name">' + strs[i] + '</span></div>';
	  						iHtml += tmp;
	  					}
	  					document.getElementById("columnDiv").innerHTML = iHtml;
	  				}	
  				}
  			});
 		}
 	</script>
 	
 	<div id="Modal-detail" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-header">
            <h3 id="myModalLabel">数据权限文件详情</h3><a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i></a>
        </div>
        <div class="modal-body">
            <div class="form-group cl">
				<label class=" fl">数据文件名：</label>
				<input class="input-text fl" type="text" disabled="" id="dataNameDetail" value="">
			</div>
	        <div class="form-group cl">
				<label class=" fl">数据类型：</label>
				<input class="input-text fl" type="text" disabled="" id="dataTypeDetail" value="">
			</div>

			<div class="form-group cl">
				<label class=" fl">数据字段：</label>
				<div class="fl dataNeedy w350">
					<div id="columnDiv" class="data-box">
						
					</div>
				</div>
			</div>

		</div> 
        </div>
		    <div class="modal-footer text-c">
		  		<button class="btn btn-close" data-dismiss="modal" aria-hidden="true">关闭</button>
		 	</div>
    </div>
 	
	<!--删除对话框--->
	<div id="Modal-delete" class="modal w300 hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<h3 id="myModalLabel">删除</h3>
			<a class="close" data-dismiss="modal" aria-hidden="true"
				href="javascript:void();"><i class="fa fa-times"></i></a>
		</div>
		<div class="modal-body">
			<p>确定删除此数据文件？</p>
		</div>
		<div class="modal-footer text-c">
			<button class="btn btn-close" data-dismiss="modal" aria-hidden="true">关闭</button>
			<button onclick="del();" class="btn btn-primary">确定</button>
		</div>
	</div>
</body>
</html>
