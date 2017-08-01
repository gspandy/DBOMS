<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>My JSP 'index.jsp' starting page</title>
<%@include file="/commons/pages/common.jsp"%>
<link rel="stylesheet" type="text/css" href="resources/css/index4.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/jump_break_big.css"/>
<link rel="stylesheet" type="text/css" href="resources/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="resources/zTree_v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="resources/My97DatePicker/WdatePicker.js"></script>
<script>
      var SYS_VIP_SEL = "${sessionScope.funOperate['SYS_VIP_SYS_VIP_SEL']}";
      var SYS_VIP_EXP = "${sessionScope.funOperate['SYS_VIP_SYS_VIP_EXP']}";
      var regionJson = ${regionJson};
</script>
</head>
<body>
<div class="wrapperbr">
<div class="wrapper">
  <div class="crumb"><a href="javascript:void(0)">客户中心</a>><span>老用户日志列表</span>
  </div>
  <div class="admin">
  	<div class="part_y1"></div>
    <div class="s_part">
    <form action="" method="post" id="vip_form">
	    <%--<span class="cell">
	    	<b style="float:left; font-weight:normal">商城名称</b>
            <select id="mallCode" name="mallCode" style="width: 70px;">
             	 <option value="">所有</option>
	    			<c:forEach items="${mallList}" var="mall">
 	                    <option value="${mall.mallCode}">${mall.mallName}</option>
 	                </c:forEach>
          	</select>
        </span>
		--%><span class="cell">
			<b style="float:left; font-weight:normal">接入方式</b>
          	<select id="accessType" name="accessType" style="width: 70px;">
		         <option value="">所有</option>
		         <c:forEach items="${WCS_ACCESS_TYPE_MAP}" var="accessType">
		           <option value="${accessType.key}">${accessType.value}</option>
		         </c:forEach>
       		</select>
       </span> 
		<span class="cell">
			<b style="float:left; font-weight:normal">日志类型</b>
          	<select id="vipLogType" name="vipLogType" style="width: 70px;">
		         <option value="">所有</option>
		         <c:forEach items="${WCS_VIP_LOG_TYPE_MAP}" var="vipLogType">
		           <option value="${vipLogType.key}">${vipLogType.value}</option>
		         </c:forEach>
       		</select>
       </span> 
       <span class="cell">
       <b style="float:left; font-weight:normal">地市</b>
         <select id="orderCity" name="orderCity" style="width: 100px;">
           <option value="">所有</option>
           <c:forEach items="${basRegionList}" var="basRegion">
             <option value="${basRegion.regionCode}">${basRegion.regionName}</option>
           </c:forEach>
         </select>
       </span>
		<span class="cell">
			<b style="float:left; font-weight:normal">手机号</b>
			<input id="vipMobileNumber" name="vipMobileNumber" type="text" style="width: 90px;" onkeyup="onlyNum(this);">
		</span>
		<span class="cell">
			<b style="float:left; font-weight:normal">页面ID</b>
			<input id="vipPageId" name="vipPageId" type="text" style="width: 90px;" onkeyup="onlyNum(this);">
		</span>
		<span class="cell">
			<b style="float:left; font-weight:normal">产品ID</b>
			<input id="vipGoodsId" name="vipGoodsId" type="text" style="width: 90px;" onkeyup="onlyNum(this);">
		</span>
		<span class="cell" style="width: 300px;">
			<b style="float:left; font-weight:normal">登录时间</b>
                <input id="startDate" name="startDate" type="text" onClick="WdatePicker()" style="width: 80px;"/> - 
                <input id="endDate" name="endDate" type="text" onClick="WdatePicker()" style="width: 80px;margin: 0;"/>
              </span>
		<span class="cell">
		<c:if test="${sessionScope.funOperate['SYS_VIP_SYS_VIP_SEL']}">
	    <a href="javascript:void(0);" onclick="queryData();" class="button_search2"></a>
	    </c:if>
	    <c:if test="${sessionScope.funOperate['SYS_VIP_SYS_VIP_EXP']}">
	    <a href="javascript:void(0);" id="btn_print" class="button_export"></a>
	    </c:if>
        </span>
        </form>
    </div>
    <div class="part_y3" style="width: 100%;height: 430px;">
    <table style="height: auto;width: 100%;" cellspacing="0" cellpadding="0" id="userLogDG"></table>
	</div>
  </div>

  <div class="fooder"></div>  
</div>
</div>

</body>
<script type="text/javascript">
var userLogDG=null;
$(function(){
	userLogDG=$('#userLogDG').datagrid({
	url : 'vip/getUserLogList.do',
	fit : true,
	nowrap : true,
	fitColumns : true,
	pagination : true,
	rownumbers : true,
	toolbar:'#toolbar',
	pageSize : 10,
	pageList : [ 10, 20, 30, 40, 50 ],
	idField : 'vipLogId',
	columns : [ [ 
	{field : 'vipLogId',checkbox:true}, 
	{field : 'vipMobileNumber',align : 'left',width : 50,title : '<font color="black"><b>手机号</b></font>'},
	{field : 'mallCode',align : 'left',width : 50,title : '<font color="black"><b>商城</b></font>'},
	{field : 'accessType',align : 'center',width : 50,title : '<font color="black"><b>接入方式</b></font>',formatter : getAccessTypeCN},
	{field : 'cityCode',align : 'center',width : 50,title : '<font color="black"><b>地市</b></font>',formatter : function(value, rowData, rowIndex){return regionJson[value];}},
	{field : 'modifyTime',align : 'center',width : 50,title : '<font color="black"><b>记录时间</b></font>',formatter : formatTime},
	{field : 'vipLogType',align : 'center',width : 50,title : '<font color="black"><b>日志类型</b></font>',formatter : getVipLogTypeCN},
	{field : 'verifyCode',align : 'left',width : 50,title : '<font color="black"><b>验证码</b></font>'},
	{field : 'vipPageId',align : 'left',width : 50,title : '<font color="black"><b><font color="black"><b>页面ID</b></font>'}, 
	{field : 'vipGoodsId',align : 'left',width : 50,title : '<font color="black"><b>产品ID</b></font>'}
	] ],
	onLoadSuccess: function (data) {
		userLogDG.datagrid('clearSelections'); 
	}
});
	
	$('#vipMobileNumber').keypress(function(event) {
	if (event.keyCode == 13) {
		queryData();
		return false;
	}
});
    $("#btn_print").click(function() {
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        if (! startDate && ! endDate && startDate > endDate) {
            var tmp = startDate;
            $("#startDate").val(endDate);
            $("#endDate").val(tmp);
        }
        $("#vip_form").attr("action", "vip/export.do");
        $('#vip_form').submit();
    });
})
	
	function getAccessTypeCN(value, rowData, rowIndex){
			return $("#accessType option[value='"+value +"']").html();
		}
	function getVipLogTypeCN(value, rowData, rowIndex){
			return $("#vipLogType option[value='"+value +"']").html();
		}
	
	function queryData(){
		userLogDG.datagrid('load',{
			startDate : $.trim($("#startDate").val()),
		    endDate : $.trim($("#endDate").val()),
			vipMobileNumber:$('#vipMobileNumber').val(),
			accessType:$('#accessType').val(),
			orderCity:$('#orderCity').val(),
			vipLogType:$('#vipLogType').val(),
			vipPageId:$('#vipPageId').val(),
			vipGoodsId:$('#vipGoodsId').val()
		});
	}
	
	function formatTime(time)
    {
	    var d = new Date();
		d.setTime(time);
		var month = d.getMonth()+1;
        var day = d.getDate();
        return d.getFullYear()+"-"+formatDayAndMonth(month)+"-"+formatDayAndMonth(day) +" "+formatDayAndMonth(d.getHours())+":"+formatDayAndMonth(d.getMinutes())+":"+formatDayAndMonth(d.getSeconds());
    }
	
    function formatDayAndMonth(number)
    {
        if(number<10)
        {
            return "0"+number;
        }
        return number;
    }
</script>
</html>
