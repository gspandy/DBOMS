/*
 *审核状态
 *
*/
package com.tydic.dbs.commons.enums;

/** 
 * @ClassName: CrmBalType 
 * @Description: TODO(余额大类) 
 * @author 叶子丰 yezifeng@tydic.com 
 * @date 2015-6-8 下午12:17:14 
 *  
 */
public enum OperationType {

	ADD("1","新增"),MODIFY("2","修改"),
	DEL("3","删除"),SYNC("4","同步"),LOOK("5","查看");

	private OperationType(String code, String name){
		this.code=code;
		this.name=name;
	}
	
    private String code;
	
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
     * @ClassName: CrmBalType
     * @Description: TODO(余额大类)
     * @author 叶子丰 yezifeng@tydic.com
     * @date 2015-6-8 下午12:17:14
     *
     */
    public static enum OperateType {

        WAIT("1","待审核"),PASS("2","审核通过"),
        NO_PASS("3","审核不通过"),REAUDIT("4","重新审核");

        private OperateType(String code, String name){
            this.code=code;
            this.name=name;
        }

        private String code;

        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
