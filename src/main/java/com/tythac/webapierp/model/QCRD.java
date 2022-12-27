package com.tythac.webapierp.model;

/**
 * @author CXY
 * @version Create Time: 2022/10/28
 * @Description 品管現場原因資料明細
 */
public class QCRD {
    private String ProNo;   // 流水編號
    private String YYBH;    // Defect理由編號
    private Integer Qty;    // 數量
    private String USERID;
    private String USERDATE;

    public String getProNo() {
        return ProNo;
    }

    public void setProNo(String proNo) {
        ProNo = proNo;
    }

    public String getYYBH() {
        return YYBH;
    }

    public void setYYBH(String YYBH) {
        this.YYBH = YYBH;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
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
