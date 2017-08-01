package com.tydic.dbs.commons.enums;

/**
 * Created by long on 2016/7/18.
 */
public enum  Ram {
    RAM_16(16,"16G"),RAM_32(32,"32G"),RAM_64(64,"64G"),RAM_128(128,"128G"),RAM_256(256,"256G"),RAM_512(512,"512G");

    private  Integer code;
    private  String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    Ram(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }
}
