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
<title>工单详情页-中国联通研究院大数据应用模型孵化后台管理系统</title>
<base href="<%=basePath1 %>" />
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/core.css">
    <link rel="stylesheet" href="js/kkpager/kkpager_blue.css">
    <link rel="stylesheet" href="js/jbox/jbox.css">
    <script type="text/javascript" src="js/jquery-2.1.4.js"></script> 
    <script type="text/javascript" src="js/validate/jquery.validate.min.js"></script> 
    <script type="text/javascript" src="js/validate/additional-methods.min.js"></script> 
    <script type="text/javascript" src="js/validate/messages_zh.js"></script> 
    <script type="text/javascript" src="js/kkpager/kkpager.min.js"></script> 
    <script type="text/javascript" src="js/modal/bootstrap-modal.js"></script> 
    <script type="text/javascript" src="js/modal/bootstrap-modalmanager.js"></script> 
    <script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script> 
    <script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script> 
</head>
  <body>
  	<script type="text/javascript">
	  	function down(filePath,fileName){
	  		if (filePath=='null'||fileName=='null') {
	  	  		return false;
			}
	  		$("#filePath").val(filePath);
	  		$("#fileName").val(fileName);
	  		$("#downFile").submit();
	  	}
  	</script>
	<form style="display:none;" id="downFile" name="downFile" action="sysOrder/fileDown.do" method="post">
		<input id="filePath" name="filePath" value="" />
		<input id="fileName" name="fileName" value="" />
	</form>
	<!-------------------------CONT---------------------------->  
	<div class="Detail-cont box-cont">
		<div class="panel panel-default">
			<div class="panel-header">
            	<h4>工单详情</h4>
            </div>
            <div class="panel-body">
            	<!--added by andy 0929屏蔽不展示-->
            	<!--  
                <article class="panel panel-primary mt-10">
                   <div class="panel-header"><h4>数据服务信息</h4></div>
                   <div class="panel-body">
                      <p><span class="c-999">数据服务ID：</span>${ordInfoVo.dataResourceId }</p> 
                      <p><span class="c-999">数据服务名称：</span>${ordInfoVo.dataResourceName }</p> 
                   </div>
               	</article>
               	-->
                <article class="panel panel-primary mt-10">
                    <div class="panel-header"><h4>结果数据信息</h4></div>
                    <div class="panel-body">
                       <p><span class="c-999">计量结果：</span>
                       		<c:if test="${ordInfoVo.resultUnit eq 1}">
                       			${ordInfoVo.resultCount }条
                       		</c:if>
                       		<c:if test="${ordInfoVo.resultUnit eq 2}">
                       			${ordInfoVo.resultCount }MB
                       		</c:if>
                       </p> 
                       <p><span class="c-999">提取时间：</span><fmt:formatDate value="${ordInfoVo.consumTime }" type="both" /></p> 
                       <p><span class="c-999">数据下载：</span><a onClick="down('${ordInfoVo.filePath }','${ordInfoVo.fileName }');" class="c-primary">${ordInfoVo.fileName }</a></p>   
                    </div>
                </article>
                <article class="panel panel-primary mt-10">
                    <div class="panel-header"><h4>工单状态信息</h4></div>
                    <div class="panel-body">
                       <p><span class="c-999">工单编号：</span>${ordInfoVo.ordId }</p> 
                       <p><span class="c-999">工单状态：</span>
					   		<c:if test="${ordInfoVo.ordStatus eq 1}">待提交</c:if>
					   		<c:if test="${ordInfoVo.ordStatus eq 2}">已提交</c:if>
					   		<c:if test="${ordInfoVo.ordStatus eq 3}">已执行</c:if>
					   		<c:if test="${ordInfoVo.ordStatus eq 4}">正在执行</c:if>
                            <c:if test="${ordInfoVo.ordStatus eq 5}"><span class="c-red">执行失败</span></c:if>
					   </p>
                        <p><span class="c-999">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注：</span>
                            <span class="c-red">${ordInfoVo.remark }</span>
                        </p>
                    </div>
                </article>
                <article class="panel panel-primary mt-10">
                    <div class="panel-header"><h4>工单操作信息</h4></div>
                    <div class="panel-body">
                       <table class="table table-primary">
                           <thead>
                               <tr>
                                   <th>操作时间</th>
                                   <th>操作信息</th>
                                   <th>操作人</th>
                                   <th>联系电话</th>
                               </tr>
                           </thead>
                           <c:forEach items="${ordLogList }" var="logList">
                           		<tr>
	                               <td><fmt:formatDate value="${logList.ordLogTime }" type="both" /></td>
	                               <td>${logList.ordLogMemo }</td>
	                               <td>${logList.ordLogUserAccount }</td>
	                               <td>${logList.ordLogUserTel }</td>
	                            </tr>
                           </c:forEach>
                        </table>
                    </div>
                </article>
                <article class="panel panel-primary mt-10">
                    <div class="panel-header"><h4>商户信息</h4></div>
                    <div class="panel-body">
                       <p><span class="c-999">商户名称：</span>${bussInfo.bussAccount }</p> 
                       <p><span class="c-999">联系电话：</span>${bussInfo.bussMobileNo }</p> 
                    </div>
                </article>
                <article class="panel panel-primary mt-10">
                    <div class="panel-header"><h4>产品清单</h4></div>
                    <div class="panel-body">
                       <table class="table table-primary">
                           <thead>
                               <tr>
                                   <th>产品编号</th>
                                   <th>产品名称</th>
                                   <th>产品计量单位</th>
                                   <th>产品单位价格</th>
                               </tr>
                           </thead>
                           <tr>
                               <td>${ordInfoVo.prdId}</td>
                               <td>${ordInfoVo.prdName}</td>
                               <td>
                                   <c:if test="${ordInfoVo.prdUnit eq 1}">按数据结果条数计算(条)</c:if>
                                   <c:if test="${ordInfoVo.prdUnit eq 2}">按数据结果容量计算(MB)</c:if>
                               </td>
                               <td>
                                   <c:if test="${ordInfoVo.prdUnit eq 1}">￥${ordInfoVo.prdPrice }/条</c:if>
                                   <c:if test="${ordInfoVo.prdUnit eq 2}">￥${ordInfoVo.prdPrice }/MB</c:if>
                               </td>
                           </tr>
                       </table>
                    </div>
                </article>
                <article class="panel panel-primary mt-10">
                    <div class="panel-header"><h4>产品信息</h4></div>
                    <div class="panel-body">
                       <p><span class="c-999">数据返回条件：</span>${ordInfoVo.rows }</p> 
                       <p><span class="c-999">数据时段：</span>
                       <fmt:formatDate value="${ordInfoVo.ordDateBegin }" type="both" />
                       	至
                       <fmt:formatDate value="${ordInfoVo.ordDateEnd }" type="both" />
                       </p> 
                    </div>
                </article>
                <div class="btn-wrap pl-60 mt-20">
                	<a class="btn btn-secondary " onClick="javascript:history.go(-1);return false;">关闭</a>
                </div>  
            </div>                                      
		</div>
     </div>
  </body>
</html>
