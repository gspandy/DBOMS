package com.tydic.dbs.buyer.vo;

import com.tydic.commons.utils.BaseVO;

import java.util.List;

/**
 * 数据权限字典值
 * Created by long on 2016/7/26.
 */
public class DataPemissionDict extends BaseVO implements java.io.Serializable{
    private String code;
    private String name;
    private String reOrder;
    private String parentCode;

    private String childFlag="false";//是否存在子菜单，默认是false；
    private List<DataPemissionDict> childList;
    public  DataPemissionDict(){

    }

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

    public String getReOrder() {
        return reOrder;
    }
    public void setReOrder(String reOrder) {
        this.reOrder = reOrder;
    }

    public String getParentCode() {
        return parentCode;
    }
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public List<DataPemissionDict> getChildList() {
        return childList;
    }

    public void setChildList(List<DataPemissionDict> childList) {
        this.childList = childList;
    }

    public void setChildFlag(String childFlag) {
        this.childFlag = childFlag;
    }

    public String getChildFlag() {
        return childFlag;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
        buffer.append("code").append("='").append(getCode()).append("' ");
        buffer.append("name").append("='").append(getName()).append("' ");
        buffer.append("reOrder").append("='").append(getReOrder()).append("' ");
        buffer.append("parentCode").append("='").append(getParentCode()).append("' ");
        buffer.append("]");
        return buffer.toString();
    }

    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( (other == null ) ) return false;
        if ( !(other instanceof DataPemissionDict) ) return false;
        DataPemissionDict  castOther = ( DataPemissionDict) other;
        return
                ( (this.getCode()==castOther.getCode()) ||( this.getCode()!=null && castOther.getCode()!=null && this.getCode().equals(castOther.getCode()) ) )
                        &&( (this.getName()==castOther.getName()) ||( this.getName()!=null && castOther.getName()!=null && this.getName().equals(castOther.getName()) ) )
                        &&( (this.getReOrder()==castOther.getReOrder()) ||( this.getReOrder()!=null && castOther.getReOrder()!=null && this.getReOrder().equals(castOther.getReOrder()) ) )
                        &&( (this.getParentCode()==castOther.getParentCode()) ||( this.getParentCode()!=null && castOther.getParentCode()!=null && this.getParentCode().equals(castOther.getParentCode()) ) )
                ;
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + (getCode() == null ? 0 : this.getCode().hashCode() );
        result = 37 * result + (getName() == null ? 0 : this.getName().hashCode() );
        result = 37 * result + (getReOrder() == null ? 0 : this.getReOrder().hashCode() );
        result = 37 * result + (getParentCode() == null ? 0 : this.getParentCode().hashCode() );
        return result;
    }
}
