package com.tythac.webapierp.model;

/**
 * @author CXY
 * @version Create Time: 2022/10/28
 * @Description 品管現場原因資料
 */
public class QCR {
    private String ProNo;   // 流水編號
    private String SCDate;  // 生產日期
    private String SJXH;    // 工時區間(日班、晚班、夜班)
    private String GSBH;    // 廠別
    private String DepNo;   // 部門別
    private String GXLB;    // 工段
    private String SCBH;    // 生產訂單
    private String CC;  // 尺寸
    private String USERID;
    private String USERDATE;

    public String getProNo() {
        return ProNo;
    }

    public void setProNo(String proNo) {
        ProNo = proNo;
    }

    public String getSCDate() {
        return SCDate;
    }

    public void setSCDate(String SCDate) {
        this.SCDate = SCDate;
    }

    public String getSJXH() {
        return SJXH;
    }

    public void setSJXH(String SJXH) {
        this.SJXH = SJXH;
    }

    public String getGSBH() {
        return GSBH;
    }

    public void setGSBH(String GSBH) {
        this.GSBH = GSBH;
    }

    public String getDepNo() {
        return DepNo;
    }

    public void setDepNo(String depNo) {
        DepNo = depNo;
    }

    public String getGXLB() {
        return GXLB;
    }

    public void setGXLB(String GXLB) {
        this.GXLB = GXLB;
    }

    public String getSCBH() {
        return SCBH;
    }

    public void setSCBH(String SCBH) {
        this.SCBH = SCBH;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getUSERDATE() {
        return USERDATE;
    }

    public void setUSERDATE(String USERDATE) {
        this.USERDATE = USERDATE;
    }
}
