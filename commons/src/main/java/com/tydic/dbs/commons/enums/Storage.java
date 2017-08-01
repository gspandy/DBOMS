package com.tydic.dbs.commons.enums;

/**
 * Created by long on 2016/7/18.
 */
public enum  Storage {
    STORAGE_1(1,"1T"),STORAGE_2(2,"2T"),STORAGE_3(3,"3T"),STORAGE_4(4,"4T"),STORAGE_5(5,"5T"),STORAGE_6(6,"6T");
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

    Storage(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }
}
