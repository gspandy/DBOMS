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
	//function down(fileUrl){
  		//if (fileUrl==null) {
  	  		//return false;
		//}
  		//$("#fileCode").val(fileUrl);
  		//$("#downFile").submit();
  	//}
	
	function checkPrice(){
		var prdPrice = $("#prdPrice").val();//计量单价
		if (prdPrice==""){
			$("#prdPriceTip").hide();
		}else{
			$("#prdPriceTip").hide();
		}
	}
	function checkprdUnit(){
		var prdUnit = $("#prdUnit").val();//计量单位
		if (prdPrice==""){
			$("#prdUnitTip").hide();
		}else{
			$("#prdUnitTip").hide();
		}
	}
	function checkDataIp(){
		var dataIp = $("#dataIp").val();//数据库IP
		var ipTest = new RegExp(
		/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/);
		if(dataIp==""){
			$("#dataIpTip").hide();
			$("#dataIpTip1").hide();
		}else{
		  	if(!ipTest.test(dataIp)){
		  		$("#dataIpTip1").show();
		  		 return false;
		  	}else{
		  		$("#dataIpTip").hide();
				$("#dataIpTip1").hide();
    			return true;
		  	}
		}
	}
	function checkDataPort(){
		var dataPort = $("#dataPort").val();//数据库端口
		var port =  /^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/; 
		if (dataPort==""){
			$("#dataPortTip").hide();
			$("#dataPortTip1").hide();
		}else{
			if (!port.test(dataPort)){
				$("#dataPortTip1").show();
				 return false;
			}else {
				$("#dataPortTip").hide();
				$("#dataPortTip1").hide();
				return true;
			}
		}
	}
	
	/***审核操作***/
function audit(prdId,type){

	var suggestion = $("#suggestion").val();//审核意见
	var prdPrice = $("#prdPrice").val();//计量单价
	var prdUnit = $("#prdUnit").val();//计量单位
	var prdType = $("#prdType").val();//产品类型
	var dataIp = $("#dataIp").val();//数据库ip
	var dataPort = $("#dataPort").val();//数据库端口
	var dataName = $("#dataName").val();//数据库用户名
	var dataPass = $("#dataPass").val();//数据库密码
	
	if(type=="2"){//审核通过
		if (prdUnit==""&&prdPrice==""){
			$("#prdUnitTip").show();
			$("#prdPriceTip").show();
			return false;
		}
		if (prdUnit==""&&prdPrice!=""){
			$("#prdUnitTip").show();
			$("#prdPriceTip").hide();
			return false;
		}else{
			if (prdPrice==""&&prdUnit!=""){
				$("#prdPriceTip").show();
				$("#prdUnitTip").hide();
				return false;
			}
		}
	}else if(prdType=="3"&&type=="1"){//通过并且是应用数据产品
			if(prdUnit==""){
				$("#prdUnitTip").show();
				return false;
			}else {
				if(prdPrice==""){
					$("#prdPriceTip").show();
					return false;
				}else{
					if (dataIp==""){//ip
						$("#dataIpTip").show();
						return false;
					}else{
						if(dataPort==""){
							$("#dataPortTip").show();
							return false;
						}else{
							if(dataName==""){
								$("#dataNameTip").show();
								return false;
							}else{
								if(dataPass==""){
									$("#dataPassTip").show();
									return false;
								}
							}
						}
					}
				}
			}
	}
	var jasonPara ={"prdId":prdId,
					"suggetion":suggestion,
					"prdPrice":prdPrice,
					"prdUnit":prdUnit,
					"Type":type,
					"prdType":prdType,
					"dataIp":dataIp,
					"dataPort":dataPort,
					"dataName":dataName,
					"dataPass":dataPass};
	$.ajax({
		type: "POST",
		url: "product/doAudit.do",
		data: jasonPara,
		datatype: "json",
		success: function(data){
			if (data.flag) {//操作成功
				 $.jBox.tip('审核成功！','success');
					  setTimeout('window.location.href="<%=basePath1%>product/index.do"',3000);
					return true;
				} else {
					$.jBox.error('审核失败！<br/>失败原因：' + data.msg, '提示');
				}
			}
		});
	}
	var record = {
		num : ""
	}
	var checkDecimal = function(n) {
		var decimalReg = /^\d{1,8}\.{0,1}(\d{1,2})?$/;
		if (n.value != "" && decimalReg.test(n.value)) {
			record.num = n.value;
		} else {
			if (n.value != "") {
				n.value = record.num;
			}
		}
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
				<h4>产品审核</h4>
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
						</p>
						<%--<p>
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
						<div class="sh-form-group mt-15  cl">
							<span class="fl c-999">审核意见：</span>
							<textarea class="textarea fl" name="suggestion" id="suggestion"
								cols="20" rows="10" maxlength="500"></textarea>
						</div>
						<div class="sh-form-group mt-15  cl">
							<span class="fl c-999">计量单位：</span> <select id="prdUnit"
								name="prdUnit" class="input-text" onblur="checkprdUnit();">
								<option value="">请选择</option>
								<c:forEach items="${WCS_PRD_UNIT_MAP}" var="prdUnit">
									<option value="${prdUnit.key}" >${prdUnit.value}</option>
								</c:forEach>
							</select> <span style="color: #ee3170;font-size: 14px;display:none"
								id="prdUnitTip" name="prdUnitTip">请选择计量单位！</span>
						</div>
						<div class="sh-form-group mt-15 mb-15 cl">
							<span class="fl c-999">计量单价：</span> 
							<input class="input-text fl"
								type="text" name="prdPrice" id="prdPrice" onblur="checkPrice();"
								onkeyup='checkDecimal(this)'>
							<span class="fl ml-10">(元)</span> <span  
								style="color: #ee3170;font-size: 14px;display:none"
								id="prdPriceTip" name="prdPriceTip">请输入计量单价，该值不允许为空！</span>
						</div>
						<c:if test="${prdInfo.prdType=='3'}">
							<div class="sh-form-group mt-15 mb-15 cl">
								<span class="fl c-999">数据库IP：</span> 
								<input type="hidden" name="prdType" id="prdType" value=${prdInfo.prdType}>
								<input class="input-text fl"
									type="text" name="dataIp" id="dataIp" onblur="checkDataIp();"
									 maxlength="15">
									<span  style="color: #ee3170;font-size: 14px;display:none"
									id="dataIpTip" name="dataIpTip">请输入数据库IP，该信息不允许为空！</span>
									<span  style="color: #ee3170;font-size: 14px;display:none"
									id="dataIpTip1" name="dataIpTip1">请输入正确的数据库IP！</span>
							</div>
							<div class="sh-form-group mt-15 mb-15 cl">
								<span class="fl c-999">数据库端口：</span> 
								<input class="input-text fl"
									type="text" name="dataPort" id="dataPort" onblur="checkDataPort();"
									maxlength="5">
								<span  style="color: #ee3170;font-size: 14px;display:none"
									id="dataPortTip" name="dataPortTip">请输入数据库端口，该信息不允许为空！</span>
								<span  style="color: #ee3170;font-size: 14px;display:none"
									id="dataPortTip1" name="dataPortTip1">请输入正确的端口！</span>
							</div>
							<div class="sh-form-group mt-15 mb-15 cl">
								<span class="fl c-999">数据库用户名：</span> 
								<input class="input-text fl"
									type="text" name="dataName" id="dataName" 
									maxlength="50">
								<span  style="color: #ee3170;font-size: 14px;display:none"
									id="dataNameTip" name="dataNameTip">请输入数据库用户名，该信息不允许为空！</span>
							</div>
							<div class="sh-form-group mt-15 mb-15 cl">
								<span class="fl c-999">数据库密码：</span> 
								<input class="input-text fl"
									type="text" name="dataPass" id="dataPass" 
									maxlength="50">
								<span  style="color: #ee3170;font-size: 14px;display:none"
									id="dataPassTip" name="dataPassTip">请输入数据库密码，该信息不允许为空！</span>
							</div>
						</c:if>
					</div>
				</article>
				<div class="btn-wrap mt-20 ml-60">
					<c:if test="${prdInfo.prdType=='3'}">
						<a class="btn btn-primary " href="javascript:void(0);"
						onclick="audit('${prdInfo.prdId}','1')">审核通过</a>
					</c:if>
					<c:if test="${prdInfo.prdType=='1'}">
					<a class="btn btn-primary " href="javascript:void(0);"
						onclick="audit('${prdInfo.prdId}','2')">审核通过</a>
					</c:if>	
					<c:if test="${prdInfo.prdType=='2'}">
					<a class="btn btn-primary " href="javascript:void(0);"
						onclick="audit('${prdInfo.prdId}','2')">审核通过</a>
					</c:if>	
						 <a class="btn btn-secondary ml-10" href="javascript:void(0);"
						onclick="audit('${prdInfo.prdId}','3')">审核不通过</a> 
						
						<a class="btn btn-secondary ml-10"
						onClick="javascript:history.go(-1);return false;">返回</a>
				</div>
			</div>
		</div>
	</div>


</body>
</html>