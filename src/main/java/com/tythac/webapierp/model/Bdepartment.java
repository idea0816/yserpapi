package com.tythac.webapierp.model;

/**
 * @author CXY
 * @version Create Time: 2022/10/11
 * @Description 鞋廠部門資料(部份)
 */
public class Bdepartment {
    private String ID;  // 編號
    private String DepName; // 部門名稱
    private String DepMemo; // 部門說明

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDepName() {
        return DepName;
    }

    public void setDepName(String depName) {
        DepName = depName;
    }

    public String getDepMemo() {
        return DepMemo;
    }

    public void setDepMemo(String depMemo) {
        DepMemo = depMemo;
    }
}
