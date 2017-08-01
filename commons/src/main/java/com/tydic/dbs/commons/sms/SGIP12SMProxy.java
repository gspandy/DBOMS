package com.tydic.dbs.commons.sms;

import com.huawei.insa2.comm.sgip.message.SGIPDeliverMessage;
import com.huawei.insa2.comm.sgip.message.SGIPMessage;
import com.huawei.insa2.util.Args;
import com.huawei.smproxy.SGIPSMProxy;

/**
 * @file  SGIP12SMProxy.java
 * @author caipeimin
 * @version 0.1
 * @SGIP代理
 * Copyright(C), 2013-2014
 *		 Guangzhou Sunrise Technology Co., Ltd.
 * History
 *   	1. Date: 2014-04-09 18:41:49
 *      	Author: caipeimin
 *      	Modification: this file was created
 *   	2. ...
 */
public class SGIP12SMProxy extends SGIPSMProxy {

	public SGIP12SMProxy(Args args) {
		super(args);
	}

	public SGIPMessage onDeliver(SGIPDeliverMessage msg) {
		return super.onDeliver(msg);
	}
}
