package com.tythac.webapierp.model;

/**
 * @author CXY
 * @version Create Time: 2023/1/12
 * @Description For WeekReport
 */
public class Zreport {
    private String DepNo;  // 部門編號
    private String DateTime; // 掃描時間
    private Integer Qty; // 掃描數字

    public String getDepNo() {
        return DepNo;
    }

    public void setDepNo(String depNo) {
        DepNo = depNo;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }
}
