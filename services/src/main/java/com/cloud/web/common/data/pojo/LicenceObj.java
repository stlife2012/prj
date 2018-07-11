package com.cloud.web.common.data.pojo;

public class LicenceObj {

    private String uuid;

    private String name;

    private String remark;

    private Long num;

    private Long usedNum;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Long usedNum) {
        this.usedNum = usedNum;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    private Long createTime;

    public String getSecKey() {
        return secKey;
    }

    public void setSecKey(String secKey) {
        this.secKey = secKey;
    }

    private String secKey;

    public LicenceObj(){

    }
    public LicenceObj(String uuid, String name, Long num,String remark,String secKey){
        this.uuid = uuid;
        this.name = name;
        this.secKey = secKey;
        this.num = num;
        this.remark = remark;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
