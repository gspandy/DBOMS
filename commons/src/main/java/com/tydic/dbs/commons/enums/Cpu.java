package com.tydic.dbs.commons.enums;

/**
 * Created by long on 2016/7/18.
 */
public enum  Cpu {
    CPU_2(2,"2核"),CPU_4(4,"4核"),CPU_8(8,"8核"),CPU_16(16,"16核"),CPU_32(32,"32核"),CPU_64(64,"64核");

    private  Integer code;
    private  String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    Cpu(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }
}
