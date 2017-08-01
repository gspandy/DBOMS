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
<title>用户管理页-中国联通研究院大数据应用模型孵化后台管理系统</title>
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
    <script type="text/javascript" src="resources/js/organize/managerOrganize.js"></script>
    <script type="text/javascript" src="resources/easyui13/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="resources/easyui13/locale/easyui-lang-en.js"></script>
    <script type="text/javascript" src="resources/artDialog4.1.7/jquery.artDialog.js?skin=chrome"></script>
    <script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script>
    <script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script>

    <!--[if IE 6]>
<script type="text/javascript" src="Lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('.pngfix,.icon');</script>
<![endif]-->
<script type="text/javascript">
    var totalPage;
    var totalRecords;
    var pageNo = 1;
    var roleCodes=[];
    var oldRoleCodes="${orgCode}";
    var flag="${flag}";
    $(function(){
        getRoleList();
    });
    function parentOrgCodeShow(){
        $.ajax({
            type : 'POST',
            url : 'organize/getOrganizeTree.do',
            datatype : 'text',
            success : function(result) {
                $.fn.zTree.init($("#organizeTree"), {
                    data : {
                        simpleData : {
                            enable : true
                        }
                    },
                    callback : {
                        onClick : zTreeOnClickNum
                    }
                }, result);
            }
        });
    }
    // 树点击事件
    function zTreeOnClickNum(event, treeId, treeNode) {
        $("#organizeTable1").datagrid('load', {
            orgCode : treeNode.pkId == "-1" ? "" : treeNode.pkId
        });
        $('#orgCode').val(treeNode.name);
        $("#orgId").val(treeNode.id);
        $("#Modal-organization").modal("hide")

    }

function getRoleList(type) {
    if(type=="1"){
        pageNo=1;
    }
    var roleName=$("#roleName").val().trim();
    var roleCode=$("#roleCode").val().trim();
    $.ajax({
        url : "sysUser/getRoleList.do",
        type : 'post',
        dataType : 'json',
        data : {"roleName" : roleName,
                "roleCode" : roleCode,
                 "pageNo" : pageNo},
        success : function (data) {
            var list=data.objects;
            if(list.length>0){
                var html="";
                $("#roleList").html("");
                for (var i in list){
                    var temp=null==list[i].roleDesc?"":list[i].roleDesc;
                    html += "<tr>";
                    html += "<td><input type='checkbox' onclick='saveRole(this)'></td>";
                    html += "<td>"+list[i].roleName+"</td>";
                    html += "<td>"+list[i].roleCode+"</td>";
                    html += "<td>"+temp+"</td>";
                    html += "</tr>";
                }
                $("#roleList").html(html);
                totalPage = data.totalPage;
                totalRecords = data.totalNumber;
                pageNo = data.currentPage;
                toPage();
            }
        },
        error : function () {

        }

    });
}
function changPage(){
    pageNo = $(".curr").text()==undefined||$(".curr").text()==""?1:$(".curr").text();
    getRoleList();
}
    //分页
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
}   var roleNames=[];
    function saveRole(obj) {
        var str=obj.parentNode.parentNode;
        if(obj.checked){
            roleCodes.push(str.cells[2].innerText);
            roleNames.push(str.cells[1].innerText);
        }else{
            for(var i in roleCodes){
                if(roleCodes[i]==str.cells[2].innerText){
                    roleCodes.splice(i,1);
                }
            }
            for(var j in roleNames){
                if(roleNames[j]==str.cells[3].innerText){
                    roleNames.splice(j,1);
                }
            }
        }
    }
    //选择角色
    function selectRole() {
        unique(roleCodes);
        unique(roleNames);
        $("#Modal-role").modal("hide");
        for(var i in roleNames){
            $("#roles").append("<span class='btn btn-primary-outline '  data-value="+roleCodes[i]+">"+roleNames[i]+"<i class='fa fa-times'></i></span>");
        }
    }

    function unique(arr) {
        var result = [], hash = {};
        for (var i = 0, elem; (elem = arr[i]) != null; i++) {
            if (!hash[elem]) {
                result.push(elem);
                hash[elem] = true;
            }
        }
        return result;
    }
    //保存用户
    function saveUser(type) {
        var temp=validateMessage();
        if(temp==false){
            return;
        }
        var temp=[];
        $('.btn-wrap .btn').each(function (index) {
            temp.push($(this).attr('data-value'));
        })
        var operAccount=$("#operAccount").val();//账号
        var operPwd=$("#operPwd").val();//密码
        var operName=$("#operName").val();//姓名
        var idCard=$("#idCard").val();//身份证
        var email=$("#email").val();//邮箱
        var mobile=$("#mobile").val();//联系号码
        var position=$("#position").val();//职位
        var orgCode=$("#orgId").val();//组织机构
        var operAddr=$("#operAddr").val();//地址
        var oldOperId=$("#oldOperId").val();
        $.ajax({
            url : "sysUser/saveOrUpdateUser.do",
            type : "post",
            dataType : 'json',
            data : {"operAccount": operAccount,
                    "operPwd" : operPwd,
                    "operName" : operName,
                    "idCard" : idCard,
                    "email" : email,
                    "mobile" : mobile,
                    "position" : position,
                    "orgCode" : orgCode,
                    "operAddr" : operAddr,
                    "roleCode" : temp.join(","),
                    "operType" : type,
                    "oldOperId" :oldOperId
                },
            success : function (data) {
                if(data.success==true){
                    $.jBox.tip(data.message,'success');
                    setTimeout("window.location.href='sysUser/toUserList.do'",3000);
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
    
    function validateMessage() {
        var operAccount=$("#operAccount").val().trim();//账号
        if (null==operAccount || ""== operAccount){
            $("#accountTip").show();
            return false;
        }else{
            $("#accountTip").hide();
        }
        var operPwd=$("#operPwd").val().trim();//密码
        var  uspassword = /(?!^\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{8,18}/;
        if(flag=="1" || (operPwd!=null && ""!=operPwd)){
            if(null==operPwd || ""==operPwd){
                $("#pwdTip").show();
                return false;
            }else {
                $("#pwdTip").hide();
                if(!uspassword.test(operPwd)){
                    $("#pwdTip2").show();
                    return false;
                }else{
                    $("#pwdTip2").hide();
                }
            }
        }

        var operName=$("#operName").val().trim();//姓名
        if(null==operName ||"" ==operName){
            $("#nameTip").show();
            return false;
        }else {
            $("#nameTip").hide();
        }
        var idCard=$("#idCard").val().trim();//身份证
        if(null==idCard || "" ==idCard){
            $("#idCardTip").show();
            return false;
        }else {
            $("#idCardTip").hide();
            //15位数身份证正则表达式
            var arg1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
            //18位数身份证正则表达式
            var arg2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/;
            if (idCard.match(arg1) == null && idCard.match(arg2) == null){
                $("#idCardTip2").show();
                return false;
            }else {
                $("#idCardTip2").hide();
            }
        }
        var mobile=$("#mobile").val().trim();//联系号码
        if(null==mobile || ""==mobile){
            $("#mobileTip").show();
            return false;
        }else {
            $("#mobileTip").hide();
            var phoneRegNoArea = /^[1][358][0-9]{9}$/;
            if (!phoneRegNoArea.test(mobile)) {
                $("#mobileTip2").show();
                return false;
            }else{
                $("#mobileTip2").hide();
            }
        }
        var email=$("#email").val().trim();//邮箱
        if(null==email || ""==email){
            $("#emailTip").show();
            return false;
        }else {
            $("#emailTip").hide();
            var szReg=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
            if(!szReg.test(email)){
                $("#emailTip2").show();
                return false;
            }else{
                $("#emailTip2").hide();
            }
        }
        var operAddr=$("#operAddr").val().trim();//地址
        if(null==operAddr|| ""==operAddr){
            $("#addrTip").show();
            return false;
        }else {
            $("#addrTip").hide();
        }
        var position=$("#position").val().trim();//职位
        if(null==position || ""==position){
            $("#positionTip").show();
            return false;
        }else {
            $("#positionTip").hide();
        }
        var orgId=$("#orgId").val().trim();//组织机构
        if(null==orgId || ""==orgId){
            $("#orgCodenTip").show();
            return false;
        }else {
            $("#orgCodenTip").hide();
        }
    }
</script>
</head>
<body>
<!-------------------------CONT---------------------------->
<form id="userForm" method="post">
    <input type="hidden" name="oldOperId" id="oldOperId" value="${sysOperator.operId}"/>
<div class="Edit-cont box-cont">
    <div class="panel panel-default">
        <div class="panel-header">
            <h4>用户管理</h4>
        </div>
        <div class="panel-body">
            <div class="panel panel-primary">
                <div class="panel-header">
                    <c:if test="${flag=='1'}">
                        <h4>新增用户</h4>
                    </c:if>
                    <c:if test="${flag=='0'}">
                        <h4>修改用户</h4>
                    </c:if>
                </div>
                <div class="panel-body">
                    <div class="user-form">
                        <form>
                            <div class="form-group">
                                <label class="form-label" for="">登录账号：</label>
                                 <c:if test="${flag=='1'}">
		                        	 <input type="text" class="input-text" id="operAccount"  name="operAccount" onkeyup="value=value.replace(/[^a-zA-Z]/g,'')"onblur="validateMessage()"/>
			                    </c:if>
			                    <c:if test="${flag=='0'}">
			                         <input type="text" readonly class="input-text" id="operAccount"  name="operAccount" onkeyup="value=value.replace(/[^a-zA-Z]/g,'')"onblur="validateMessage()" value="${sysOperator.operAccount}"/>
			                    </c:if>
                                <input type="hidden" name="oldAccount" id="oldAccount" readonly value="${sysOperator.operAccount}"/>
                                <span id="accountTip" style="color: red;display: none">登录账号不能为空</span>
                            </div>
                            <div class="form-group">
                                <label class="form-label" for="">登录密码：</label>
                                <input type="password" class="input-text" id="operPwd" name="operPwd" onblur="validateMessage()">
                                <span id="pwdTip" style="color: red;display: none">登录密码不能为空</span>
                                <span id="pwdTip2" style="color: red;display: none">密码必须是数字和字母的组合，6~16位！</span>
                            </div>
                            <div class="form-group">
                                <label class="form-label" for="">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
                                <input type="text" class="input-text" name="operName" id="operName" onblur="validateMessage()" value="${sysOperator.operName}">
                                <span id="nameTip" style="color: red;display: none">姓名不能为空</span>
                            </div>
                            <div class="form-group">
                                <label class="form-label" for="">身份证号码：</label>
                                <input type="text" class="input-text" name="idCard" id="idCard" onblur="validateMessage()" value="${sysOperator.operCardNo}">
                                <span id="idCardTip" style="color: red;display: none">身份证号码不能为空</span>
                                <span id="idCardTip2" style="color: red;display: none">请输入正确的身份证号码</span>
                            </div>
                            <div class="form-group">
                                <label class="form-label" for="">联系号码：</label>
                                <input type="text" class="input-text" name="mobile" id="mobile" onblur="validateMessage()" value="${sysOperator.operMobile}">
                                <span id="mobileTip" style="color: red;display: none">联系号码不能为空</span>
                                <span id="mobileTip2" style="color: red;display: none">请输入正确的联系号码</span>
                            </div>
                            <div class="form-group">
                                <label class="form-label" for="">EMAIL：</label>
                                <input type="text" class="input-text" name="email" id="email" onblur="validateMessage()" value="${sysOperator.operEmail}">
                                <span id="emailTip" style="color: red;display: none">EMAIL不能为空</span>
                                <span id="emailTip2" style="color: red;display: none">请输入正确的EMAIL</span>
                            </div>
                            <div class="form-group">
                                <label class="form-label" for="">联系地址：</label>
                                <input type="text" class="input-text" name="operAddr" id="operAddr" onblur="validateMessage()" value="${sysOperator.operAddr}">
                                <span id="addrTip" style="color: red;display: none">联系地址不能为空</span>
                            </div>
                            <div class="form-group">
                                <label class="form-label" for="">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</label>
                                <input type="text" class="input-text"  name="position" id="position" onblur="validateMessage()" value="${sysOperator.position}">
                                <span id="positionTip" style="color: red;display: none">职位不能为空</span>
                            </div>
                            <div class="form-group">
                                <label class="form-label" for="">所属组织机构：</label>
                                <input type="text" class="input-text " name="orgCode" id="orgCode"  readonly onblur="validateMessage()" value="${sysOrganize.orgName}"> <a href="#Modal-organization" data-toggle="modal"  class="btn btn-third ml-5" onclick="parentOrgCodeShow()">选择组织机构</a>
                                <input type="hidden" name="orgId" id="orgId" value="${sysOrganize.orgCode}"/>
                                <span id="orgCodenTip" style="color: red;display: none">所属组织机构不能为空</span>
                            </div>
                            <div class="form-group cl" >
                                <label class="form-label fl" for="">关联角色：</label>
                                <div class="btn-wrap role-wrap" id="roles" >
                                <c:forEach items="${list}" varStatus="vtStatus" var="vt">
                                    <span class="btn btn-primary-outline "  data-value="${vt.roleCode}">${vt.roleName}<i class="fa fa-times"></i></span>
                                </c:forEach>
                                </div>

                                <a href="#Modal-role" data-toggle="modal"  class="btn btn-third ml-5">选择角色</a>
                            </div>
                            <script>
                                //删除关联角色功能
                                $(document).on('click', '.btn-wrap .fa-times',function(){
                                    $(this).parent(".btn").remove();
                                });
                            </script>


                        </form>
                    </div>
                </div>
            </div>
                <c:if test="${flag=='1'}">
                <div class="btn-wrap pl-20 mt-10"> <a class="btn btn-primary ml-10"onclick="saveUser('1')">保存</a><a class="btn btn-secondary ml-10" onclick="javascript:history.go(-1);">取消</a>
                </c:if>
                <c:if test="${flag =='0'}">
                <div class="btn-wrap pl-20 mt-10"> <a class="btn btn-primary ml-10"onclick="saveUser('0')">保存</a><a class="btn btn-secondary ml-10" onclick="javascript:history.go(-1);">取消</a>
                </c:if>
            </div>
        </div>
    </div>
</div>

<!--------------------------MODAL---------------------------->

<!--选择组织机构对话框--->
<div id="Modal-organization" class="modal w300 hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <h3 id="myModalLabel">选择组织机构</h3><a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i></a>
    </div>
    <div class="modal-body">
        <!--树状菜单-->
        <ul id="organizeTree" class="ztree"></ul>
    </div>
</div>
<!--选择角色对话框--->
<div id="Modal-role" class="modal full  hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <h3 id="myModalLabel">选择角色</h3><a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i></a>
    </div>
    <div class="modal-body">
        <!--搜索模块-->
        <div class="search-form  ">
            <form id="searchForm">
                <div class="row cl">
                    <div class="col-sm-4 col-sm-offset-1">
                        <label class="form-label" for="">角色名称：</label>
                        <div class="formControls">
                            <input type="text" class="input-text" id="roleName" name="roleName">
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <label class="form-label" for="">角色编码：</label>
                        <div class="formControls">
                            <input type="text" class="input-text" name="roleCode" id="roleCode">
                        </div>
                    </div>

                    <div class="col-sm-2 text-r">
                        <a  class="btn btn-primary  " onclick="getRoleList('0')">查询</a>
                    </div>

                </div>

            </form>
        </div>
        <!--    列表   -->
        <table class="table table-primary mt-10">
            <thead>
            <tr>
                <th width="30"></th>
                <th width="200">角色名称</th>
                <th width="100">角色编码</th>
                <th>描述</th>
            </tr>
            </thead>
            <tbody id="roleList">
            </tbody>
        </table>
        <!--分页-->
        <div id="kkpager"></div>

    </div>
    <div class="modal-footer text-c">
        <button class="btn btn-close" data-dismiss="modal" aria-hidden="true">关闭</button>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <button class="btn btn-primary" onclick="selectRole()">确定</button>
    </div>
</div>
</form>

</body>
</html>