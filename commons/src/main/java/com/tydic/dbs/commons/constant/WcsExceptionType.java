package com.tydic.dbs.commons.constant;

/**
 * 
 * @ClassName: WcsExceptionType 
 * @Description: TODO(系统后台主动跑出错误种类变量定义表) 
 * @author davis
 * @date 2015-1-30 上午8:59:15 
 *
 */
public class WcsExceptionType {
	
	
	/** common：订单不存在*/
    public static final String ORD_COM_ORDER_NOEXIST = "OrdOrdersBo-0001";//前面的00表示方法，后面01表示错误类型编号    
    /** common：订单编码为空*/
    public static final String ORD_COM_ORDER_NO_NULL = "OrdOrdersBo-0002";
    /** common：找不到对应的仓位信息  */
    public static final String ORD_COM_NO_STOCK = "OrdOrdersBo-0003";
    
    /** order model start*/
    /** cancelOrder：订单已经支付不能取消  */
    public static final String ORD_CAN_ORDER_PAYED = "OrdOrdersBo-0101";
    
    /** appRefund: 订单已申请退款，不能重复申请*/
    public static final String ORD_REF_ORDER_APPLIED = "OrdOrdersBo-0201";
    /** appRefund: 订单支付状态不是已支付*/
    public static final String ORD_REF_ORDER_NOTPAYED = "OrdOrdersBo-0202";
    
    /** save：没有提货方式  */
    public static final String ORD_SAVE_NO_DELIVERY_WAY = "OrdOrdersBo-0301";
    /** save：号码销售状态不正确,请重新选择  */
    public static final String ORD_SAVE_SALE_STATUS_ERROR = "OrdOrdersBo-0302";
    /** save：号码已被预占,不允许提交订单  */
    public static final String ORD_SAVE_NUM_USED = "OrdOrdersBo-0303";
    /** save：保存订单商品失败  */
    public static final String ORD_SAVE_GOODS_FAIL = "OrdOrdersBo-0304";
    /** save：订单配送信息不能为空  */
    public static final String ORD_SAVE_NO_DELIVERY_INFO = "OrdOrdersBo-0305";
    
    
    
    /** order model end*/

    
}
