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
<title>月账单明细页-中国联通研究院大数据应用模型孵化后台管理系统</title>
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

  </head>
  
  <body>
  <script type="text/javascript">
  	$(function(){
  		param["monthId"]=monthId;
  		param["bussAccount"]=bussAccount;
  		getList();
  	});
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
    //导出数据
    function doExport(){
        $("form[name='searchForm']").submit();
    }
  </script>
  <script type="text/javascript">
  	var monthId = "${monthId}";
  	var bussAccount = "${bussAccount}";
  	var totalPage;
  	var totalRecords;
  	var page = 1;
  	var param = {};
  	function getList(){
		page = $(".curr").text()==undefined||$(".curr").text()==""?1:$(".curr").text();
	    param["page"] = page;
  		$.ajax({
  			type: "POST",
  			url: "bill/getDetailList.do",
  			data: param,
  			datatype: "json",
  			success: function(data){
  				if (data!=null) {
  					$("#billList").empty();
  					totalPage = data.totalPage;
  					totalRecords = data.totalNumber;
  					var billList = data.rows;
  					for (x in billList){
  						var prdUnit = billList[x].prdUnit;
  						var prdPrice = prdUnit==1?"￥"+billList[x].prdPrice+"/条":"￥"+billList[x].prdPrice+"/MB";
  						var dataResult = prdUnit==1?billList[x].rowNum:billList[x].dataSize;
  						prdUnit = prdUnit==1?"条数":"MB";
  						var time = FormatDate(billList[x].ordDateBegin);
  						var temp="<tr>"
  							+"<td>"+billList[x].ordId+"</td>"
  							+"<td>"+billList[x].bussAccount+"</td>"
  							+"<td>"+billList[x].prdId+"</td>"
  							+"<td>"+billList[x].prdName+"</td>"
  							+"<td>"+prdUnit+"</td>"
  							+"<td>"+prdPrice+"</td>"
  							+"<td>"+time+"</td>"
  							+"<td>"+dataResult+"</td>"
  							+"<td>￥"+billList[x].consumAmout+"</td>"
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
		         getList();
		         return false;
		     },
		     getHref : function(n){
		         return 'javascript:void(0)';
		     }
			 });
	}
</script>
<form style="display:none;" id="searchForm" name="searchForm" action="bill/doExportDetail.do" method="post">
	<input id="monthId" name="monthId" value="${monthId }" />
	<input id="bussAccount" name="bussAccount" value="${bussAccount }" />
</form>
<!-------------------------CONT---------------------------->  
    <div class="Detail-cont box-cont">
            <div class="panel panel-default">
               <div class="panel-header">
                 <h4>月账单明细</h4>
               </div>
               <div class="panel-body">
                   <div class="btn-wrap text-r"> 
                       <a class="btn btn-primary " onClick="doExport();">导出</a>
					   <a class="btn btn-secondary ml-10" href="javascript:history.go(-1);">返回</a>                       
                    </div> 
              <!--详情--> 
               <table class="table table-primary mt-10">
                   <thead>
                       <tr>
                           <th>工单号</th>
                           <th>商户名称</th>
                           <th>产品ID</th>
                           <th>产品名称</th>
                           <th>产品计量单位</th>
                           <th>产品单位价格(元)</th>
                           <th>工单执行时间</th>
                           <th>数据结果计量</th>
                           <th>计费金额</th>
                       </tr>
                   </thead>
                   <tbody id="billList"></tbody>
               </table>
               <!--分页-->
          <div id="kkpager" class="mt-10 ml-20 mr-30"></div>
          </div>                                      
        </div>
  	</div> 
  </body>
</html>
