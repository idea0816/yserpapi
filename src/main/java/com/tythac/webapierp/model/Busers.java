package com.tythac.webapierp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;

/**
 * @author CXY
 * @version Create Time: 2022/11/4
 * @Description 帳號資料
 */
public class Busers {
    @NotBlank
    private String USERID;  // 用戶編號
    private String USERNAME;    // 用戶姓名
    @NotBlank
    @JsonIgnore // 隱藏回傳值
    private String PWD; // 密碼
    private String EMAIL;   // E-MAIL ADDRESS
    private String LASTDATETIME;    // 最後登入時間
    private String YN;  // 啟用
    private String passwordchend;   // 密碼更換時間
    private String fromIP;  // 來源IP
    private String depid;   // 單位代號
    private String Engname; // 英文名
    private String Report;
    private String SupervisorID;

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getLASTDATETIME() {
        return LASTDATETIME;
    }

    public void setLASTDATETIME(String LASTDATETIME) {
        this.LASTDATETIME = LASTDATETIME;
    }

    public String getYN() {
        return YN;
    }

    public void setYN(String YN) {
        this.YN = YN;
    }

    public String getPasswordchend() {
        return passwordchend;
    }

    public void setPasswordchend(String passwordchend) {
        this.passwordchend = passwordchend;
    }

    public String getFromIP() {
        return fromIP;
    }

    public void setFromIP(String fromIP) {
        this.fromIP = fromIP;
    }

    public String getDepid() {
        return depid;
    }

    public void setDepid(String depid) {
        this.depid = depid;
    }

    public String getEngname() {
        return Engname;
    }

    public void setEngname(String engname) {
        Engname = engname;
    }

    public String getReport() {
        return Report;
    }

    public void setReport(String report) {
        Report = report;
    }

    public String getSupervisorID() {
        return SupervisorID;
    }

    public void setSupervisorID(String supervisorID) {
        SupervisorID = supervisorID;
    }
}
