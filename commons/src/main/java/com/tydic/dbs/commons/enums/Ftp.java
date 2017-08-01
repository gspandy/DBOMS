package com.tydic.dbs.commons.enums;

/**
 * Created by long on 2016/7/18.
 */
public enum  Ftp {
    FTP_300(300,"300G"),FTP_500(500,"500G"),FTP_800(800,"800G"),FTP_1024(1024,"1T"),FTP_2048(2048,"2T"),FTP_4096(4096,"3T");
    private Integer code;
    private String desc;

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

    Ftp(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }
}
