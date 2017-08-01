package com.tydic.dc.utils;

import com.tydic.dbs.commons.define.WcsMallOrderStatus;
import com.tydic.dbs.commons.define.WcsOrderStatus;
import com.tydic.dbs.commons.define.WcsWfNodesCode;

/**
 * 
 * @ClassName: OrderStatusCKOrderOutStatus 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author Carson liulu@tydic.com 
 * @date 2015-2-6 上午11:19:21 
 *
 */
public class OrderStatusCKOrderOutStatus {
	
	/**
	 * 通过流程状态获取对应的订单商场状态
	 * @Title: getOrderOutSatusByOrderStatus 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param orderStatus
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String getOrderOutSatusByOrderStatus(String orderStatus){
		String orderOutSatus=null;
		if(null!=orderStatus && !"".equals(orderStatus)){
			if(orderStatus.equals(WcsWfNodesCode.WCS_ORDER_PROC)){//如果是业务受理
				orderOutSatus=WcsMallOrderStatus.WAIT_SELLER_SEND_GOODS;//买家已付款,等待卖家发货
			}else if(orderStatus.equals(WcsWfNodesCode.WCS_DELIVERY_PROC)){//发货
				orderOutSatus=WcsMallOrderStatus.WAIT_SELLER_SEND_GOODS;//买家已付款,等待卖家发货
			}else if(orderStatus.equals(WcsWfNodesCode.WCS_CLOSE_FILE)){//回归归档
				orderOutSatus=WcsMallOrderStatus.WAIT_BUYER_CONFIRM_GOODS;//等待买家确认收货
			}else if(orderStatus.equals(WcsWfNodesCode.WCS_REFUND_PROC)){//退单处理
				orderOutSatus=WcsMallOrderStatus.REFUND_PROCESS;//退款处理中
			}
		}
		return orderOutSatus;
	}
	
	/**
	 * 根据订单流程状态查询订单内部状态
	 * @Title: getOrderSatus 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param orderStatus
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String getOrderSatus(String orderStatus){
		String ordSatus=null;
		if(null!=orderStatus && !"".equals(orderStatus)){
			if(orderStatus.equals(WcsWfNodesCode.WCS_ORDER_PROC)){//如果是业务受理
				ordSatus=WcsOrderStatus.ORDER_BUSINESS_ACCEPTANCE;//业务受理
			}else if(orderStatus.equals(WcsWfNodesCode.WCS_DELIVERY_PROC)){//发货
				ordSatus=WcsOrderStatus.ORDER_SENDGOODS;//发货
			}else if(orderStatus.equals(WcsWfNodesCode.WCS_CLOSE_FILE)){//回归归档
				ordSatus=WcsOrderStatus.ORDER_CUSTOMER_RETURN;//待归档
			}else if(orderStatus.equals(WcsWfNodesCode.WCS_REFUND_PROC)){//退单处理
				ordSatus=WcsOrderStatus.ORDER_SINGLE_APPLICATION;//退款处理中
			}
		}
		
		return ordSatus;
	}

}
