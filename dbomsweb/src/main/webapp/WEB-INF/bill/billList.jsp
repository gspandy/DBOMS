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
<title>月账单查询页-中国联通研究院大数据应用模型孵化后台管理系统</title>
<base href="<%=basePath1 %>" />

    <link rel="Bookmark" href="img/favorite.png" > 
    <link rel="Shortcut Icon" href="img/favorite.png" />
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/core.css">
    <link rel="stylesheet" href="js/jbox/jbox.css">
    <link rel="stylesheet" href="js/kkpager/kkpager_blue.css">
    <script type="text/javascript" src="js/jquery-2.1.4.js"></script> 
    <script type="text/javascript" src="js/modal/bootstrap-modal.js"></script> 
    <script type="text/javascript" src="js/modal/bootstrap-modalmanager.js"></script> 
    <script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script> 
    <script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script>
    <script type="text/javascript" src="js/kkpager/kkpager.min.js"></script>
	<script type="text/javascript" src="resources/jquery/jquery.sizes.js"></script>
	<script type="text/javascript" src="resources/jquery/jlayout.border.js"></script>
	<script type="text/javascript" src="resources/jquery/jquery.jlayout.js"></script>
	<script type="text/javascript" src="resources/js/common_jq.js"></script>
	<script type="text/javascript" src="resources/window/lhgdialog.min.js?skin=chrome"></script>
    <%-- 时间插件 --%>
    <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script> 

  </head>
  
  <body>
  	<script type="text/javascript">
		$(function(){
			searchList();
		});

	    function doExport(){
	        $("form[name='searchForm']").submit();
	    }
	</script>
	<script type="text/javascript">
	  var totalPage;
	  var totalRecords;
	  var page = 1;
	  var param = {};
	  function searchList(){
		if (!timeCheck()) return false;
		page = 1;
		var bussAccount = $("#bussAccount").val()==undefined?"":$("#bussAccount").val();
		var startMonthId = $("#startMonthId").val()==undefined?"":$("#startMonthId").val();
		var endMonthId = $("#endMonthId").val()==undefined?"":$("#endMonthId").val();
		param["bussAccount"]=bussAccount.trim();
		param["startMonthId"]=startMonthId;
		param["endMonthId"]=endMonthId;
		param["page"]=page;
	    getList();
	  }
	  //切换页码获取数据
	  function changPage(){
		page = $(".curr").text()==undefined||$(".curr").text()==""?1:$(".curr").text();
	    param["page"] = page;
	    getList();
	  }
	  function getList(){
  		$.ajax({
  			type: "POST",
  			url: "bill/getList.do",
  			data: param,
  			datatype: "json",
  			success: function(data){
  				if (data!=null) {
  					$("#billSum").empty();
  					$("#billSum").text(data.billSum);
  					$("#billList").empty();
  					totalPage = data.totalPage;
  					totalRecords = data.totalNumber;
  					var billList = data.rows;
  					for (x in billList){
  						var temp = "<tr>"
  	                    +"<td>"+billList[x].monthId+"</td>"
  	                    +"<td>"+billList[x].bussAccount+"</td>"
  	                    +"<td><b class=\"c-orange\">"+billList[x].billAmount+"</b></td>"
  	                    +"<td>"
  	                  	+'<c:if test="${sessionScope.funOperate[\'CHA_BILL_CHECK\'] }">'
  	                    +"<a class=\"btn btn-op\" href=\"bill/toDetailList.do?monthId="+billList[x].monthId+"&bussAccount="+billList[x].bussAccount+"\" title=\"查看月账单明细\"><i class=\"fa fa-search\"></i></a>"
  	                    +'</c:if>'
  	                    +"</td>"
  	                	+"</tr>";
  	                	$("#billList").append(temp);
  					}
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
  <!-- 时间校验 -->
  <script type="text/javascript">
  	function timeCheck(){
  		var startTimeVal = $("#startMonthId").val();
  		var endTimeVal = $("#endMonthId").val();
  		if (startTimeVal==undefined||startTimeVal==null||startTimeVal=="") {
			return true;
		}
  		if (endTimeVal==undefined||endTimeVal==null||endTimeVal=="") {
			return true;
		}
  		if (endTimeVal>=startTimeVal) {
			return true;
		}
  		$.jBox.error("输入错误！<br/>结束时间必须大于等于开始时间", "提示");
  		return false;
  	}
  </script>
  <!-------------------------CONT---------------------------->	
<div class="List-cont box-cont">
	<div class="panel panel-default">
		<div class="panel-header">
			<h4>月账单查询</h4>
		</div>
		<div class="panel-body">
			<!--搜索模块-->
			<div class="search-form">
				<form id="searchForm" name="searchForm" action="bill/doExport.do" method="post">
					<div class="row cl">
						<div class="col-sm-3">
	                        <label class="form-label" for="">商户名称：</label>
	                        <div class="formControls">
	                            <input id="bussAccount" name="bussAccount" type="text" class="input-text">
	                        </div>
						</div>
                        <div class="col-sm-4">
                            <label class="form-label" for="">计费月份：</label>
                            <div class="formControls formControls-max" >
                               <input id="startMonthId" name="startMonthId" type="text" readonly="true" class="input-text input-min"
                               onclick="WdatePicker({el:'startMonthId',dateFmt:'yyyy-MM',skin:'whyGreen'})" >
                               --
                               <input id="endMonthId" name="endMonthId" type="text" readonly="true" class="input-text input-min"
                               onclick="WdatePicker({el:'endMonthId',dateFmt:'yyyy-MM',skin:'whyGreen'})" >
                            </div>
                        </div>
                        <div class="col-sm-5 text-r">
                            <a onClick="searchList();" class="btn btn-primary  ">查询</a>
                            <c:if test="${sessionScope.funOperate['CHA_BILL_EXPORT'] }">
                            <a onClick="doExport();" class="btn btn-secondary  ">导出</a>
                            </c:if>
                        </div>
                    </div>
                </form>
             </div>
             <!--列表-->
             <div class="text-r mt-20 pr-10">
             	合计金额： <b id="billSum" class="f-20 c-orange">7132.58</b> 元
             </div>
            <table class="table table-primary mt-10">
                <thead>
                    <tr>
                        <th>计费月份</th>
                        <th>商户名称</th>
                        <th>计费金额(元)</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody id="billList">
                </tbody>
            </table>
            <!--分页-->
            <div id="kkpager"></div>
          </div>
        </div>
    </div>
  </body>
</html>
