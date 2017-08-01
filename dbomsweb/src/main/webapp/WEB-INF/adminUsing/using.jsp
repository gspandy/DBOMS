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
<base href="<%=basePath1 %>" />
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>使用量查询-中国联通研究院大数据应用模型孵化后台管理系统</title>
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
    <script type="text/javascript" src="js/validate/additional-methods.min.js"></script>
    <script type="text/javascript" src="js/validate/messages_zh.js"></script>
    <script type="text/javascript" src="js/kkpager/kkpager.min.js"></script>
   <!--  <script type="text/javascript" src="js/app.js"></script> -->
    <script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script>
    <script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script>
    <%-- 时间插件 --%>
    <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script> 
    <!--[if IE 6]> 
      <script type="text/javascript" src="Lib/DD_belatedPNG_0.0.8a-min.js" ></script>
      <script>DD_belatedPNG.fix('.pngfix,.icon');</script> 
    <![endif]-->
    <script type="text/javascript">
	//加载页面数据
	var totalPage;
	var totalRecords;
	var pageNo = 1;
	var param = {};
    $(function(){
		getUsingList();
	});

	function getUsingList(){
		if (!timeCheck()) {
			return false;
		}
		var ordId = $("#ordId").val().trim();
		var bussName = $("#bussName").val().trim();
		var prdId = $("#prdId").val().trim();
		var prdName = $("#prdName").val().trim();
		var startTime = $("#startTime").val()==undefined?"":$("#startTime").val();
		var endTime = $("#endTime").val()==undefined?"":$("#endTime").val();
		var total=0;
		var totalM=0;
		$.ajax({
			url :"adminUsing/getUsingList.do",
			data:{"ordId":ordId,
				  "bussName":bussName,
				  "prdId":prdId,
				  "prdName":prdName,
				  "startTime":startTime,
				  "endTime":endTime,
				  "pageNo" : pageNo},
			type:"post",
			dataType : "json",
			success:function(result){
				var html="";
				$("#UsingList").html("");
				var list=result.objects;
				for (var i in list){
				var prdUnit = list[i].PRD_UNIT==1?"条数":"MB";
				var time = FormatDate(list[i].RUN_TIME);
				html += "<tr>";
				html += "<td>"+list[i].WORK_NO+"</td>";
				html += "<td>"+list[i].BUSS_NAME+"</td>";
				html += "<td>"+list[i].PRD_ID+"</td>";
				html += "<td>"+list[i].PRD_NAME+"</td>";
				html += "<td>"+time+"</td>";
				html += "<td>"+prdUnit+"</td>";
				
			
				
				var sum="";
				var summ="";
	                if(list[i].PRD_UNIT=="1"){
	                	html += "<td>"+list[i].ROW_NUM+"</td>";
	                   sum=Number(list[i].ROW_NUM);
	                   total=(Number(total)+Number(sum));
	                }else if(list[i].PRD_UNIT=="2"){
	                	html += "<td>"+list[i].DATA_SIZE+"</td>";
	                    summ=Number(list[i].DATA_SIZE);
	                   totalM=(Number(totalM)+Number(summ));
	                }else{
	                    sum="";
	                }
	            	html += "</tr>";
				}
				$("#UsingList").html(html);
				$("#total").empty();
				$("#totalM").empty();
                $("#total").append(total);
                $("#totalM").append(totalM);
				totalPage = result.totalPage;
				totalRecords = result.totalNumber;
				pageNo = result.currentPage;
				toPage();
			},
			error:function(){
				alert("查询使用量列表失败!");
				return false;
			}
		});
	}
	//切换页码获取数据
	function changPage() {
		pageNo = $(".curr").text() == undefined || $(".curr").text() == "" ? 1
				: $(".curr").text();
		getUsingList();
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
	
	  //格式化时间
  function FormatDate (strTime) {
	/*   if(strTime.length<4)
		  return strTime; */
      var date = new Date(strTime);
      var mon;var day;var hour;var min;
      var  temp=date.getMonth()+1;
      if (strTime==undefined){return "";}
      if(temp<10){mon="0"+temp;}else {mon=temp;}
      if(date.getDate()<10){day="0"+date.getDate();}else {day=date.getDate();}
      if(date.getHours()<10){hour="0"+date.getHours();}else {hour=date.getHours();}
      if(date.getMinutes()<10){min="0"+date.getMinutes();}else {min=date.getMinutes();}
      return date.getFullYear()+"-"+mon+"-"+day+"<br>"+hour+":"+min;
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
  		$.jBox.error("输入错误！<br/>错误原因：结束时间必须大于开始时间", "提示");
  		return false;
  	}
  	function doExport(){
        $("form[name='searchForm']").submit();
    }
  	 </script>
</head>

<body>

    <!-------------------------CONT---------------------------->
<div class="List-cont box-cont">
            <div class="panel panel-default">
                <div class="panel-header">
                    <h4>使用量查询</h4>
                </div>
                <div class="panel-body">
                    <!--搜索模块-->
                    <div class="search-form">
                         <form id="searchForm" name="searchForm" action="adminUsing/doExport.do" method="post">
                            <div class="row cl">
                                <div class="col-sm-3">
                                    <label class="form-label" for="">工单号：</label>
                                    <div class="formControls">
                                        <input type="text" class="input-text" id="ordId" name ="ordId">
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <label class="form-label" for="">商户名称：</label>
                                    <div class="formControls">
                                        <input type="text" class="input-text" id="bussName" name="bussName">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <label class="form-label" for="">执行时间：</label>
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
                                        <input type="text" class="input-text " id="prdId" name="prdId">
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <label class="form-label" for="">产品名称：</label>
                                    <div class="formControls ">
                                        <input type="text" class="input-text " id="prdName" name="prdName">
                                    </div>
                                </div>
                              
                                <div class="col-sm-t  text-r">
                                    <a class="btn btn-primary  "  onclick="getUsingList('1')">查询</a>
                                    <c:if test="${sessionScope.funOperate['ADMIN_USING_EXPORT'] }">
                                    <a class="btn btn-secondary  " onclick="doExport()">导出</a>
                                    </c:if>
                                </div>
                            </div>
                        </from>
                    </div>
                    <!--列表-->
                    <div class="text-r mt-20 pr-10">
                       
                      数据结果总计： <b class="f-20 c-orange" id="totalM"></b> MB，<b class="f-20 c-orange" id ="total"></b> 条
                     
                    </div>
                    <table class="table table-primary mt-10">
                        <thead>
                            <tr>
                                <th>工单号</th>
                                <th>商户名称</th>
                                <th>产品ID</th>
                                <th>产品名称</th>
                                <th>工单执行时间</th>
                                <th>产品计量单位</th>
                                <th>数据计量结果</th>
                                
                                
                            </tr>
                        </thead>
                        <tbody id="UsingList">
                        
                		</tbody>

                    </table>
                    <!--分页-->
                    <div id="kkpager"></div>
                </div>
            </div>
        </div>
 


</body>

</html>