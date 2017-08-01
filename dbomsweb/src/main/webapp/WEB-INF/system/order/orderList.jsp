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
<title>工单列表页-中国联通研究院大数据应用模型孵化后台管理系统</title>
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
    <%-- 时间插件 --%>
    <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script> 
    
<%-- main.js用于获取当前登录用户可操作功能数据 --%>
<script type="text/javascript" src="resources/easyui13/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/easyui13/locale/easyui-lang-en.js"></script>
<script type="text/javascript" src="resources/common/easyui-expand.js"></script>
<script type="text/javascript" src="resources/common/easyui-validate.js"></script>

<script type="text/javascript" src="resources/js/system/main.js"></script>
<script type="text/javascript" src="resources/js/menu.js"></script>
</head>
  <body>
  <script type="text/javascript">
  $(function(){
	  searchList();
  });
  var totalPage;
  var totalRecords;
  var page = 1;
  var param = {};
  function searchList(){
	if (!timeCheck()) {
		return false;
	}
  	page = 1;
	var ordId = $("#ordId").val()==undefined?"":$("#ordId").val();
	var prdId = $("#prdId").val()==undefined?"":$("#prdId").val();
	var prdName = $("#prdName").val()==undefined?"":$("#prdName").val();
	var startTime = $("#startTime").val()==undefined?"":$("#startTime").val();
	var endTime = $("#endTime").val()==undefined?"":$("#endTime").val();
	var ordStatus = $("#ordStatus option:selected").val()==undefined?"":$("#ordStatus option:selected").val();
	param["ordId"] = ordId;
	param["prdId"] = prdId;
	param["prdName"] = prdName;
	param["startTime"] = startTime;
	param["endTime"] = endTime;
	param["ordStatus"] = ordStatus;
	param["page"] = page;
	getList();
  }
  //格式化时间
  function FormatDate (strTime) {
	  if(strTime.length<4)
		  return strTime;
      var date = new Date(strTime);
      var mon;var day;var hour;var min;
      var  temp=date.getMonth()+1;
      if(temp<10){mon="0"+temp;}else {mon=temp;}
      if(date.getDate()<10){day="0"+date.getDate();}else {day=date.getDate();}
      if(date.getHours()<10){hour="0"+date.getHours();}else {hour=date.getHours();}
      if(date.getMinutes()<10){min="0"+date.getMinutes();}else {min=date.getMinutes();}
      return date.getFullYear()+"-"+mon+"-"+day+"<br>"+hour+":"+min;
  }
  //切换页码获取数据
  function changPage(){
	page = $(".curr").text()==undefined||$(".curr").text()==""?1:$(".curr").text();
    param["page"] = page;
    getList();
  }
  //获取数据
  function getList(){
  		$.ajax({
  			type: "POST",
  			url: "sysOrder/getOrderList.do",
  			data: param,
  			datatype: "json",
  			success: function(data){
  				if (data!=null) {
  					var sysOrderList = data.rows;
  					totalPage = data.totalPage;
  					totalRecords = data.totalNumber;
  					$("#orderList").empty();
  					for(x in sysOrderList){
  						var prdUnit = sysOrderList[x].prdUnit==1?"条数":"MB";
  						var prdPrice = sysOrderList[x].prdUnit==1?"￥"+sysOrderList[x].prdPrice+"/条":"￥"+sysOrderList[x].prdPrice+"/MB";
  						var ordStatus = sysOrderList[x].ordStatus;
  						var time = FormatDate(sysOrderList[x].createTime);
  						if (ordStatus==1) {
  							ordStatus="<b class=\"c-orange\">待提交</b>";
						} else if (ordStatus==2) {
							ordStatus="<b class=\"c-green\">已提交</b>";
						} else if (ordStatus==3) {
							ordStatus="<b class=\"c-primary\">已执行</b>";
						} else if (ordStatus==4) {
							ordStatus="<b class=\"c-red\">正在执行</b>";
						}else if (ordStatus==5) {
                            ordStatus="<b class=\"c-red\">执行失败</b>";
                        }
  						var temp = "<tr>"
  						    +"<td>"+sysOrderList[x].ordId+"</td>"
  						    +"<td>"+sysOrderList[x].prdId+"</td>"
  						    +"<td>"+sysOrderList[x].prdName+"</td>"
  						    +"<td>"+prdUnit+"</td>"
  						    +"<td>"+prdPrice+"</td>"
  						    +"<td>"+time+"</td>"
  						    +"<td>"+sysOrderList[x].rows+"</td>"
  						    +"<td>"+ordStatus+"</td>"
  						    +"<td>"
	  	                  	+'<c:if test="${sessionScope.funOperate[\'ORDER_LIST_CHECK\'] }">'
  							+"<a class=\"btn btn-op\" onClick=\"toDetail(\'"+sysOrderList[x].ordId+"\')\" title=\"查看\"><i class=\"fa fa-search\"></i></a>"
  						    +'</c:if>'
  							+"</td>"
  							+"</tr>";
  						$("#orderList").append(temp);
  					}
  					toPage();
				}else{
				
				}
  			}
  		});
  }
  </script>
  <!-- 时间校验 -->
  <script type="text/javascript">
  	function timeCheck(){
  		var startTimeVal = $("#startTime").val();
  		var endTimeVal = $("#endTime").val();
  		if (startTimeVal==undefined||startTimeVal==null||startTimeVal=="") {
			return true;
		}
  		if (endTimeVal==undefined||endTimeVal==null||endTimeVal=="") {
			return true;
		}
  		if (endTimeVal>=startTimeVal) {
			return true;
		}
  		$.jBox.error("输入错误！<br/>结束时间必须大于开始时间", "提示");
  		return false;
  	}
  </script>
  <form style="display:none;" id="detailForm" method="post" action="sysOrder/toOrderDetail.do">
  	<input id="ordIdTemp" name="ordIdTemp" />
  </form>
  <script type="text/javascript">
  	function toDetail(ordCode){
  		if (ordCode==undefined||ordCode==null||ordCode=="") {
			return false;
		}
  		$("#ordIdTemp").val(ordCode);
  		$("#detailForm").submit();
  	}
  </script>
	<!-- 加载分页控件 -->
	<script type="text/javascript">
		function toPage(){
			kkpager.total = totalPage;
			kkpager.totalRecords = totalRecords;
			kkpager.pno = page;
			kkpager.hasPrv = (kkpager.pno > 1);
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
                    <h4>工单列表</h4>
                </div>
                <div class="panel-body">
                    <!--搜索模块-->
                    <div class="search-form">
                        <form id="searchForm">
                            <div class="row cl">
                                <div class="col-sm-3">
                                    <label class="form-label" for="">工单号：</label>
                                    <div class="formControls">
                                        <input type="text" id="ordId" name="ordId" class="input-text">
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <label class="form-label" for="">工单状态：</label>
                                    <div class="formControls">
                                        <select class="input-text" name="ordStatus" id="ordStatus">
                                            <option value="">全部</option>
                                            <option value="1">未提交</option>
                                            <option value="2">已提交</option>
                                            <option value="3">已执行</option>
                                            <option value="5">执行失败</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <label class="form-label" for="">提交时间：</label>
                                    <div class="formControls formControls-max">
	                                   <input id="startTime" name="startTime" type="text" readonly="true" class="input-text input-min"
	                                   onclick="WdatePicker({el:'startTime',dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" >
	                                   --
	                                   <input id="endTime" name="endTime" type="text" readonly="true" class="input-text input-min"
	                                   onclick="WdatePicker({el:'endTime',dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" >
                                    </div>
                                </div>
                            </div>
                            <div class="row mt-10 cl">
                                <div class="col-sm-3">
                                    <label class="form-label" for="">产品ID：</label>
                                    <div class="formControls ">
                                        <input id="prdId" name="prdId" type="text" class="input-text ">
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <label class="form-label" for="">产品名称：</label>
                                    <div class="formControls ">
                                        <input id="prdName" name="prdName" type="text" class="input-text ">
                                    </div>
                                </div>
                                <div class="col-sm-3 col-sm-offset-3 text-r">
                                    <a onClick="searchList();" class="btn btn-primary  ">查询</a>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!--列表-->
                    <table class="table table-primary mt-30">
                        <thead>
                            <tr>
                                <th width=15%>工单号</th>
                                <th width=15%>产品ID</th>
                                <th width=10%>产品名称</th>
                                <th width=10%>产品计量单位</th>
                                <th width=10%>产品单价</th>
                                <th width=10%>提交时间</th>
                                <th width=10%>数据返回条件</th>
                                <th width=10%>工单状态</th>
                                <th width=10%>操作</th>
                            </tr>
                        </thead>
                		<tbody id="orderList"></tbody>
                    </table>
                    <!--分页-->
                    <div id="kkpager" ></div>
                </div>
            </div>
        </div>
  </body>
</html>
