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
	<title>数据权限配置管理页-中国联通研究院大数据应用模型孵化后台管理系统</title>
	<base href="<%=basePath1 %>" />
	<!--[if lt IE 9]>
	<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/general.css">
	<link rel="stylesheet" href="css/layout.css">
	<link rel="stylesheet" href="css/core.css">
	<link rel="stylesheet" href="js/kkpager/kkpager_blue.css">
	<link rel="stylesheet" href="js/zTree_v3/css/zTreeStyle/zTreeStyle.css">
	<link rel="stylesheet" href="js/jbox/jbox.css">
	<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="js/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="js/validate/additional-methods.min.js"></script>
	<script type="text/javascript" src="js/validate/messages_zh.js"></script>
	<script type="text/javascript" src="js/kkpager/kkpager.min.js"></script>
	<script type="text/javascript" src="js/modal/bootstrap-modal.js"></script>
	<script type="text/javascript" src="js/modal/bootstrap-modalmanager.js"></script>
	<script type="text/javascript" src="js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="resources/js/system/menu.js"></script>
	<script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script>
	<script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script>

	<script type="text/javascript" src="js/app.js"></script>
	<!--[if IE 6]>
	<script type="text/javascript" src="Lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('.pngfix,.icon');</script>
	<![endif]-->

<script type="text/javascript">
	function saveDataCfg(type) {
		var id;
		if(type==0){id=$("#oldId").val().trim();}
		//id=$("#oldId").val().trim();
		var dataFileName=$("#dataFileName").val().trim();
		var dataType=$("#dataType").val().trim();
		var dataName='';
		var dataNames=$("div[name='dataNameDiv']");
		var oldDataNames=$(columns).val().trim();
		for(i=0;i<dataNames.length;i++){
			var data = $(dataNames[i]).text();
			dataName+=',' +data;
		}		
		if(null ==dataFileName || ""==dataFileName){ 
			jBox.tip("数据文件名不能为空!", 'error');
			return false;
		}
		if(null ==dataType || ""==dataType){ 
			jBox.tip("数据类型不能为空!", 'error');
			return false;
		}
		if(null ==dataName || ""==dataName){
		 	jBox.tip("数据字段不能为空!", 'error');
		 	return false;
		 
		 }

		$.ajax({
			url : "sys/insertDataCfg.do",
			type : "post",
			data : {"id" : id,
					"dataFileName" : dataFileName,
					"dataType" : dataType,
					"dataName" : dataName,
					"oldDataNames" : oldDataNames,
					"type" : type
			},
			dataType : "json",
			success : function (data) {
				if(data.success==true){
					$.jBox.tip(data.message,'success');
					setTimeout("window.location.href='sys/dataConfig.do'",500);
				}else {
					$.jBox.error(data.message, '提示');
					return false;
				}
			},
			error : function () {
				$.jBox.error(data.message, '提示');
				return false;
			}
		});
	}
	
	
	
</script>
</head>

<body>
<!-------------------------CONT---------------------------->
	<div class="Edit-cont box-cont">
		<div class="panel panel-default">
			<div class="panel-header">
				<h4>数据权限管理</h4>
			</div>
			<div class="panel-body">
				<div class="panel panel-primary">
					<div class="panel-header">
						<c:if test="${flag=='1'}">
							<h4>新增数据权限文件</h4>
						</c:if>
						<c:if test="${flag=='0'}">
							<h4>修改数据权限文件</h4>
							<input type="hidden" name="oldId" id="oldId" value="${dataCfg.id}"/>
						</c:if>
					</div>
					<div class="panel-body">
						<div class="user-form">
							<form id="dataForm">
								<div class="form-group">
									<label class="form-label" for="">数据文件名：</label> 
									<input type="text" id="dataFileName" name="dataFileName" value="${dataCfg.name}" class="input-text" >
									 <span style="color: #ee3170;font-size: 14px;display:none" id="fileNameCheckTip" name="fileNameCheckTip" >该数据文件名已存在，请重新填写</span>
								</div>

								<div class="form-group">
									<label class="form-label" for="">数据类型：</label> 
									<select	class="input-text" name="dataType" id="dataType">
										<option <c:if test="${dataCfg.parentId == 1}"> selected="selected"</c:if> value="1">Oracle样本数据</option>
										<option <c:if test="${dataCfg.parentId == 2}"> selected="selected"</c:if> value="2">HDFS样本数据</option>
									</select>
								</div>
								<div class="form-group">
									<label class="form-label" for="">数据字段：</label> 
									<input class="input-text" name="dataName"  id="dataName" type="text" onblur="true"> 
									<input type="hidden" name="columns" id="columns" value="${dataCfg.columns}"/>
									<span class="btn-add"><b class="fa fa-plus-circle"></b></span>
								</div>


								<!--数据字段 START-->
								<div class="form-group cl">
									<label class=" fl">&nbsp;</label>
									<div class="fl dataNeedy w350">
										<div id="columnDiv" class="data-box"></div>
									</div>

									<script>
									window.onload = function(){
										var columns = $("#columns").val().trim();
										var strs = new Array();
						  				$("#columnDiv").html('');
						  				//$(".data-box").empty();
						  				var iHtml = '';
						  				if(columns != null || columns != undefined || columns != ''){
						  					strs = columns.split(",");
						  					if(strs.length > 1){
						  						for (i=0;i<strs.length;i++){
						  							var tmp = "<div name='dataNameDiv' class='data'><span class='name'>" + strs[i] + "</span><b class='fa fa-times-circle'></b></div>";
						  							iHtml += tmp;
						  						}
						  						//$(".data-box").append(iHtml);
						  						document.getElementById("columnDiv").innerHTML = iHtml;
						  					}
						  				}
									};
									
                        			$(function(){                            			
                            			//增加数据字段
                            			$(".btn-add").on("click",function(){
                                			console.log($("input[name=dataName]").val());
                                			if($("input[name=dataName]").val()==""){
                                   				jBox.tip("数据字段不能为空!", 'error');
                                    			return;
                                			}else{
                                    			var dataName = $("input[name=dataName]").val();
                                    			var html =  "<div name='dataNameDiv' class='data'><span class='name'>"+dataName+"</span><b class='fa fa-times-circle'></b></div>";
                                    			$(".data-box").append(html);
                                    			$("input[name=dataName]").val("");  
                                			}
                                
                            			});
                            			//删除数据
                            			$(document).on("click",".data b",function(event){
                               				event.stopPropagation(); $(this).parent(".data").remove();
                            			});
                            
                        			});
                      				</script>

								</div>
								<!--数据字段END-->

							</form>
						</div>
					</div>
				</div>
				<div class="btn-wrap pl-20 mt-10">
					<c:if test="${flag=='1'}">
						<a  class="btn btn-primary  ml-10" onclick="saveDataCfg('1')">保存</a> <a onclick="javascript:history.go(-1);"  class="btn btn-secondary  ml-10">取消</a>
					</c:if>
					<c:if test="${flag=='0'}">
						<a  class="btn btn-primary  ml-10" onclick="saveDataCfg('0')">保存</a> <a onclick="javascript:history.go(-1);"  class="btn btn-secondary  ml-10">取消</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>


</body>

</html>