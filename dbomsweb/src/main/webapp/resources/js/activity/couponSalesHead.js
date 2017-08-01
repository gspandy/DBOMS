var couponSalesDetailDG=null;
var params={};
$(function(){
	couponSalesDetailDG=$('#couponSalesDetailDG').datagrid({
		url : 'couponSalesDetail/getCouponSalesDetailList.do',
		fit : true,
		nowrap : true,
		fitColumns : true,
		pagination : true,
		rownumbers : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		idField : 'actSalesId',
		columns : [ [ {
			field : 'actSalesId',
			checkbox:true
		}, {
			field : 'actSalesName',
			align : 'left',
			width : 50,
			title : '优惠活动名称'
		},{
			field : 'couponName',
			align : 'center',
			width : 50,
			title : '优惠劵规则名称'
		},{
			field : 'couponAssignCount',
			align : 'center',
			width : 50,
			title : '已发卷'
		},{
			field : 'couponUsedCount',
			align : 'left',
			width : 50,
			title : '已使用'
		},
		/*{
			field : 'couponEnddateText',
			align : 'left',
			width : 50,
			title : '有效期截至'
		},*/
		{
			field : 'status',
			hidden:true
		},{
			field : 'statusText',
			align : 'left',
			width : 50,
			title : '状态'
		},{
			field : 'control',
			align : 'center',
			width : 100,
			title : '操作',
			formatter : function(value, rowData, rowIndex){
				var array=[];
				array.push('<a href="couponSalesDetail/visitModifyCouponSalesDetail.do?type=query&actSalesId='+rowData.actSalesId+'"  class="operButton" title="详情"><img src="resources/easyui13/themes/gray/images/button/btn_detail.gif" alt="详情" /></a>');
				if(params.WCS_ACT_NOTSTART==rowData.status||params.WCS_ACT_HANDING==rowData.status){
					if(params.UPD){
						array.push('<a href="couponSalesDetail/visitModifyCouponSalesDetail.do?type=update&actSalesId='+rowData.actSalesId+'" class="operButton" title="修改"><img src="resources/easyui13/themes/gray/images/button/btn_edit.gif" alt="修改" /></a>');
					}
				}
				if(params.WCS_ACT_NOTSTART==rowData.status){
					if(params.DEL){
						array.push('<a href="javascript:void(0);" onclick="delConfirm(\''+rowData.actSalesId+'\');" class="operButton" title="禁用"><img src="resources/easyui13/themes/gray/images/button/btn_del.gif" alt="禁用" /></a>');
					}
				}
				if(params.WCS_ACT_HANDING==rowData.status){
					if(params.DOW){
						array.push('<a href="javascript:void(0);" onclick="offShelve(\''+rowData.actSalesId+'\');" class="operButton" title="下架"><img src="resources/easyui13/themes/gray/images/button/btn_undercarriage.gif" alt="下架" /></a>');
					}
				}
				return array.join('');
			}
		} ] ],
		onLoadSuccess: function (data) {
			couponSalesDetailDG.datagrid('clearSelections'); 
		}
	});

	$('#querySalesName').keypress(function(event) {
		if (event.keyCode == 13) {
			queryData();
			return false;
		}
	});
	
	$('#queryRuleName').keypress(function(event) {
		if (event.keyCode == 13) {
			queryData();
			return false;
		}
	});
})

function queryData(){
	couponSalesDetailDG.datagrid('load',{
		actSalesName:$('#querySalesName').val(),
		couponName:$('#queryRuleName').val(),
		status:$('#statusList').val()
	});
}

function delConfirm(id){
	var row ;
	var ids = [];
	var postData = '';
	var noDel=[];
	var flag=false;
	if(undefined != id && "" != id){
		postData = 'actSalesIds='+id;
		flag=true;
	}else{
		row = couponSalesDetailDG.datagrid('getSelections');
		if(row.length < 1){
			$.dialog({
				title:'提示',
			    content:'请选择您要禁用的商城信息!',
			    cancelVal: '确定',
			    cancel: true,
			    lock:true
			});
			return false;
		}			
		for(var i=0;i<row.length;i++){
			if(params.WCS_ACT_NOTSTART==row[i].status){//只有是状态为未开始才能禁用
				ids.push(row[i].actSalesId);
				flag=true;
			}else{
				noDel.push(row[i].actSalesName);
			}
		}
		postData='actSalesIds='+ ids.join('&actSalesIds='); 
	}
	if(flag){
		var msg='';
		if(noDel.join(',').length>0){
			msg=noDel.join(',')+'不能禁用,确定禁用剩余的优惠券信息?';
		}else{
			msg='确定禁用剩余的优惠券信息?';
		}
		 $.dialog({
			 title:'提示',
		    content:msg,
		    cancelVal: '关闭',
		    cancel: true,
		    ok: function(){
		       deleteCouponDetail(postData);
		    },
		    lock:true
		});
	}else{
		$.dialog({
			title:'提示',
		    content:'优惠活动 : '+noDel.join(',')+'不能禁用',
		    cancelVal: '关闭',
		    cancel: true,
		    lock:true
		});
	}
}
	
function deleteCouponDetail(postData){
	$.ajax({
		type : 'POST',
		url : 'couponSalesDetail/deleteCouponDetail.do',
		data : postData,
		datatype : 'json',
		success : function(msg) {
			if(msg.flag){
				couponSalesDetailDG.datagrid('load',{});
			}
			$.dialog({
				title:'提示',
			    content:msg.msg,
			    cancelVal: '确定',
			    cancel: true,
			    lock:true
			});
		}
	});
}

function renew(id){
	$.ajax({
		type : 'POST',
		url : 'couponSalesDetail/renewCouponDetail.do',
		data : 'actSalesId='+id,
		datatype : 'json',
		success : function(msg) {
			if(msg.flag){
				couponSalesDetailDG.datagrid('load',{});
			}
			$.dialog({
				title:'提示',
			    content:msg.msg,
			    cancelVal: '确定',
			    cancel: true,
			    lock:true
			});
		}
	});
}

function offShelve(id){
	$.dialog({
	    content:'确定下架?',
	    cancelVal: '关闭',
	    cancel: true,
	    ok: function(){
	    	$.ajax({
	    		type : 'POST',
	    		url : 'couponSalesDetail/offShelve.do',
	    		data : 'actSalesId='+id,
	    		datatype : 'json',
	    		success : function(msg) {
	    			if(msg.flag){
	    				couponSalesDetailDG.datagrid('load',{});
	    			}
	    			$.dialog({
	    				title:'提示',
	    			    content:msg.msg,
	    			    cancelVal: '确定',
	    			    cancel: true,
	    			    lock:true
	    			});
	    		}
	    	});
	    },
	    lock:true
	});
}

