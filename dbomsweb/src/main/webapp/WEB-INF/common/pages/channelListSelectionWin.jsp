<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/commons/pages/common.jsp"%>
<link rel="stylesheet" type="text/css" href="resources/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>
<script type="text/javascript" src="resources/zTree_v3/js/jquery.ztree.all-3.5.min.js"></script>
<input type="hidden" id="mallCodes" value="${mallCodes}"/>
<input type="hidden" id="regionCodeIn" value="${regionCodeIn}"/>
<input type="hidden" id="mallCode" value="${mallCode}"/>
<input type="hidden" id="saleGoodsId" value="${saleGoodsId}"/>
<c:set scope="page" var="defaultChannelAccounts" value="输入多个渠道帐号，请使用;分离"></c:set>
<script type="text/javascript">
var defaultChannelAccountsText = '${defaultChannelAccounts}';
var singleSelect = '${singleSelect}'; 
</script>
<script type="text/javascript" src="resources/js/common/channelListSelectionWin.js"></script>
<div class="window no_pd_br_mg_trbl" style="width:580px;">
  <div class="win_tree7b otherWin7" style="height: 500px;">
    <div class="textpart5" >
      <strong>渠道类型</strong>
      <strong>
      	<input type="hidden" id="module" value="${module }"/>
      	<input type="hidden" id="licensingId" value="${licensingId }"/>
      	<input type="hidden" id="saleGoodsChannelMark" value="${channelMark }"/>
      	<input type="hidden" id="channels" value="${channels }"/>
        <input type="hidden" id="channelTypeId"/>
        <input type="text" id="channelType" style="width: 108px;" class="inputclass va_middleInput w100" readonly="readonly">
        <div id="menuContent" class="menuContent" style="overflow:scroll; overflow-x:hidden;height:200px;background: #f0f6e4;display:none; position: absolute;z-index: 2000;">
          <ul id="channelTypeTree" class="ztree" style="margin-top:0; width:180px; height: 200px;"></ul>
        </div>
      </strong>
      
      <strong >渠道名称</strong>
      <strong><input id="channelName" name="channelName" type="text" style="width: 200px;" class="inputclass va_middleInput w120" value="${channelName }" onkeydown="getKey(event)" onkeypress="getKey(event)" maxlength="100"></strong>
      <%-- <strong class="mg_l w50">地市</strong>
      
      
      
      <strong><select id="regionCode" name="regionCode" class="inputselect va_middleSelect w190" style="top:-2px">
        <option value="">所有</option>
        <c:forEach items="${basRegionList}" var="basRegion">
          <option value="${basRegion.regionCode}">${basRegion.regionName}</option>
        </c:forEach>
      </select></strong> --%>
     </div>
     <div class="textpart5" style="padding-top: 0px;"> 
      <%-- <strong>渠道等级</strong>
      <strong><select id="channelGrade" name="channelGrade" class="inputselect va_middleSelect w100" style="top:-2px">
        <option value="">所有</option>
        <c:forEach items="${channelGradeMap}" var="channelGrade">
          <option value="${channelGrade.key}">${channelGrade.value}</option>
        </c:forEach>
      </select></strong> --%>
      
<%--       <strong class="mg_l w50">渠道标记</strong>
      <strong><select id="channelMark" name="channelMark" class="inputselect va_middleSelect w190" style="top:-2px">
        <option value="">所有</option>
        <c:forEach items="${WCS_CHANNEL_MARK_MAP}" var="channelMark">
          <option value="${channelMark.key}">${channelMark.value}</option>
        </c:forEach>
      </select></strong> --%>
    </div>
     
    <div class="textpart5" style="padding-top: 0px;">
      
      <strong >渠道帐号</strong>
      <strong><input id="channelAccounts" name="channelAccounts" type="text" style="width: 200px;" class="inputclass va_middleInput w120" value="${defaultChannelAccounts }"></strong>
      
      
      <strong>查询方式</strong>
      <strong><select id="queryMode" name="queryMode" class="inputselect va_middleSelect w100" style="top:-2px">
        <c:forEach items="${queryModeMap}" var="queryMode">
          <option value="${queryMode.key}">${queryMode.value}</option>
        </c:forEach>
      </select></strong>
    </div>
    
    <div class="textpart5" style="padding-top: 0px;">
      <a href="javascript:void(0);" id="btn_search" class="va_middleBen button_search2"></a>
    </div>
    <div class="textpart1" style="height: 400px;padding:0 0 30px 0;">
      <table width="100%" id="channelTable" class="tableStyle2 txa_c" border="0" cellspacing="0" cellpadding="0"></table>
      <c:if test="${singleSelect != '1'}">
      <div class="mg_t" style="margin-top: 5px;"><label><input id="checkAll" class="checkbox" type="checkbox" value="all">跨页全选</label></div>
      </c:if>
    </div>
  </div>
</div>