<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath1 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<base href="<%=basePath1%>" />

<link rel="Bookmark" href="img/favorite.png">
<link rel="Shortcut Icon" href="img/favorite.png" />
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/general.css">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/core.css">
<link rel="stylesheet" href="js/kkpager/kkpager_blue.css">
<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="js/validate/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="js/validate/additional-methods.min.js"></script>
<script type="text/javascript" src="js/validate/messages_zh.js"></script>
<script type="text/javascript" src="js/kkpager/kkpager.min.js"></script>
<script type="text/javascript" src="js/modal/bootstrap-modal.js"></script>
<script type="text/javascript" src="js/modal/bootstrap-modalmanager.js"></script>

<script type="text/javascript" src="resources/jquery/jquery.sizes.js"></script>
<script type="text/javascript" src="resources/jquery/jlayout.border.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.jlayout.js"></script>
<script type="text/javascript" src="resources/js/common_jq.js"></script>
<script type="text/javascript"
	src="resources/window/lhgdialog.min.js?skin=chrome"></script>
<%-- main.js用于获取当前登录用户可操作功能数据 --%>
<script type="text/javascript"
	src="resources/easyui13/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="resources/easyui13/locale/easyui-lang-en.js"></script>
<script type="text/javascript" src="resources/common/easyui-expand.js"></script>
<script type="text/javascript" src="resources/common/easyui-validate.js"></script>

<script type="text/javascript" src="resources/js/system/main.js"></script>
<script type="text/javascript" src="resources/js/menu.js"></script>
<script type="text/javascript">
	var totalPage;
	var totalRecords;
	var pageNo = 1;
	var param = {};
	$(function() {
		getProductList();
	});
	//切换页码获取数据
	function changPage() {
		pageNo = $(".curr").text() == undefined || $(".curr").text() == "" ? 1
				: $(".curr").text();
		getProductList();
	}
	function getProductList(type) {
		if (type=="1"){
			pageNo=1;
		}
		var prdId=$("#prdId").val().trim();
		var prdName=$("#prdName").val().trim();
		var prdType=$("#prdType").val().trim();
		var prdStatus=$.trim($("#prdStatus").val());
		$.ajax({
			url : "product/getProductList.do",
			data : {"prdId" : prdId,
					"prdName" : prdName,
					"prdType" : prdType,
					"prdStatus" : prdStatus,
					"pageNo" : pageNo},
			type : "post",
			dataType : "json",
			success: function (result) {
				var html="";
				$("#productList").html("");
				var list=result.objects;
				for (var i in list){
					var prdStatus = "";
				 	if( list[i].prdStatus=='1'){
						prdStatus = "<b class=\"c-orange\">待审核</b>";
					}else if(list[i].prdStatus=='2'){
						prdStatus = "<b class=\"c-green\">审核通过</b>"
					}else if(list[i].prdStatus=='3'){
						prdStatus = "<b class=\"c-red\">审核不通过</b>"
					}
					var prdType = "";
				 	if( list[i].prdType=='1'){
				 		prdType = "商户结果数据";
					}else if(list[i].prdType=='2'){
						prdType = "联通原始数据";
					}else if(list[i].prdType=='3'){
						prdType = "应用数据";
					}
					html += "<tr>";
					html += "<td>"+list[i].prdId+"</td>";
					html += "<td>"+list[i].prdName+"</td>";
					html += "<td>"+prdType+"</td>"; 
					/* html += "<td> <a class=\"c-primary\" onClick=\"down(\'"+list[i].prdDatafile+"\')\">"+list[i].prdDataName+"</a></td>";*/
					html += "<td>"+list[i].prdOthers+"</td>";
					html += "<td>"+list[i].creater+"</td>";
					html += "<td>"+prdStatus+"</td>";
					 if( list[i].prdStatus=="1"){
							html += '<td>'+
	  	                  	'<c:if test="${sessionScope.funOperate[\'PRD_LIST_CHECK\'] }">'+
							'<a class="btn btn-op" data-toggle="modal"" href="#Modal-edit" title="查看" onclick="prdDeatil(\''+list[i].prdId+'\',\'1\',\'1\')"><i class="fa fa-search"></i></a>'+
							'</c:if>'+
							'<c:if test="${sessionScope.funOperate[\'PRD_LIST_AUDIT\'] }">'+
							'<a class="btn btn-op ml-10"  data-toggle="modal"" href="#Modal-edit" title="审核"onclick="prdDeatil(\''+list[i].prdId+'\',\'1\',\'2\')"><i  class="fa fa-legal"></i></a>'+
							'</c:if>'+
							'</td>';
					 }else{
					 		html += '<td>'+
	  	                  	'<c:if test="${sessionScope.funOperate[\'PRD_LIST_CHECK\'] }">'+
					 		'<a class="btn btn-op" data-toggle="modal"" href="#Modal-edit" title="查看" onclick="prdDeatil(\''+list[i].prdId+'\',\'1\',\'1\')"><i class="fa fa-search"></i></a>'+
							'</c:if>'+
							'</td>';
					 }
					html += "</tr>";
				}
				$("#productList").html(html);
				totalPage = result.totalPage;
				totalRecords = result.totalNumber;
				pageNo = result.currentPage;
				toPage();
			},
			error : function () {
				alert("查询菜单列表失败!");
				return false;
			}
		});
	}
	/*
	 * 查看/审核商户
	 */
	function prdDeatil(prdId,status,type){//type 0新增 1查看 2审核
  		if (type==2&&status==2) {//状态值为2表示已审核
  			alert("已审核通过的产品不能修改!");
  			return false;
		}
  		$("#prdId1").val(prdId);
  		$("#type").val(type);
  		$("#detialForm").submit();
  	}
	//下载文件
	function down(fileUrl){
  		if (fileUrl==null) {
  	  		return false;
		}
  		$("#fileCode").val(fileUrl);
  		$("#downFile").submit();
  	}
	<!-- 加载分页控件 -->
	function toPage(){
			//alert("总页数"+totalPage+"-总数据"+totalRecords+"-当前页"+pageNo);
			kkpager.total = totalPage;
			kkpager.totalRecords = totalRecords;
			kkpager.pno = pageNo;
			kkpager.hasPrv = (kkpager.pno > 1)
			kkpager.hasNext = (kkpager.pno < kkpager.total);
			kkpager.generPageHtml({
				pno : pageNo,
				mode : 'click',
				total : totalPage,
				totalRecords : totalRecords,
				click : function(n){
					this.selectPage(n);
					changPage();
					return false;
				},
				getHref : function(n){
					return "javascript:void(0)";
				}
			});
		}
		
</script>

</head>
<body>
	<form style="display:none;" id="downFile" name="downFile" action="product/download.do" method="post">
		<input id="fileCode" name="fileCode" value="" />
	</form>
	
	  <form style="display:none;" id="detialForm" method="post" action="product/query.do">
	  	<input id="prdId1" name="prdId1" />
	  	<input id="type" name="type" />
	  	<input id="status" name="status" />
	  </form>

	<!-------------------------CONT---------------------------->
	<div class="List-cont box-cont">
		<div class="panel panel-default">
			<div class="panel-header">
				<h4>产品列表</h4>
			</div>
			<div class="panel-body">
				<!--搜索模块-->
				<div class="search-form">
					<form id="searchForm">
						<div class="row cl">
							<div class="col-sm-3">
								<label class="form-label" for="">产品ID：</label>
								<div class="formControls">
									<input type="text" class="input-text" id="prdId" name="prdId">
								</div>
							</div>
							<div class="col-sm-3">
								<label class="form-label" for="">产品名称：</label>
								<div class="formControls ">
									<input type="text" class="input-text " id="prdName"
										name="prdName">
								</div>
							</div>
							<div class="col-sm-3">

								<label class="form-label" for="">产品数据类型：</label>
								<div class="formControls" for="">
									<select class="input-text" name="prdType" id="prdType">
										<option value="">全部</option>
										<option value="1">商户结果数据</option>
										<option value="2">联通原始数据</option>
										<option value="3">应用数据</option>
									</select>
								</div>



							</div>
							
							
							</div>
							<div class="row cl mt-20">
							
							<div class="col-sm-3">

								<label class="form-label" for="">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label>
								<div class="formControls" for="">
									<select class="input-text" name="prdStatus" id="prdStatus">
										<option value="">全部</option>
										<option value="1">待审核</option>
										<option value="2">审核通过</option>
										<option value="3">审核不通过</option>
									</select>
								</div>
							</div>
							<div class="col-sm-9 text-r">
								<a class="btn btn-primary  " onclick="getProductList('1')">查询</a>

							</div>

						</div>
					</form>
				</div>
				<!--列表-->
				<table class="table table-primary mt-30">
					<thead>
						<tr>
							<th>产品ID</th>
							<th>产品名称</th>
							<th>产品数据类型</th>
							<th>其他需求</th>
							<th>申请人</th>
							<th>状态</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody id="productList">

					</tbody>
				</table>
				<!--分页-->
				<div id="kkpager"></div>
			</div>
		</div>
	</div>


	<!--删除对话框--->
	<div id="Modal-delete" class="modal w300 hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<h3 id="myModalLabel">删除</h3>
			<a class="close" data-dismiss="modal" aria-hidden="true"
				href="javascript:void();"><i class="fa fa-times"></i> </a>
		</div>
		<div class="modal-body">
			<p>确定删除此商户资料？</p>
		</div>
		<div class="modal-footer text-c">
			<button class="btn btn-close" data-dismiss="modal" aria-hidden="true">关闭</button>
			<button class="btn btn-primary">确定</button>
		</div>
	</div>


</body>


</html>