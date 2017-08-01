package com.tydic.dbs.commons.enums;

/**
 * Created by long on 2016/7/18.
 */
public enum  Role {
    ROLE_MODEL("1","数据建模角色"),ROLE_PROCESS("2","数据加工角色"),ROLE_CHECK("3","数据审核角色");
    private  String code;
    private  String desc;

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    Role(String code,String desc){
        this.code=code;
        this.desc=desc;
    }
}
