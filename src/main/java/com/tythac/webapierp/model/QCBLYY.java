package com.tythac.webapierp.model;

/**
 * @author CXY
 * @version Create Time: 2022/10/19
 * @Description BC品原因
 */
public class QCBLYY {
    private String GSBH;    // 廠別
    private String YYBH;    // 編號
    private String DFL; // 大分類
    private String YWSM;    // 英文名稱
    private String ZWSM;    // 中文名稱
    private Integer AlarmL; // 發生幾次產生系統發處警告
    private Integer StopL;  // 發生幾次產線系統停止
    private String YN;  // 停用	YN=2啟用, 3停用

    public String getGSBH() {
        return GSBH;
    }

    public void setGSBH(String GSBH) {
        this.GSBH = GSBH;
    }

    public String getYYBH() {
        return YYBH;
    }

    public void setYYBH(String YYBH) {
        this.YYBH = YYBH;
    }

    public String getDFL() {
        return DFL;
    }

    public void setDFL(String DFL) {
        this.DFL = DFL;
    }

    public String getYWSM() {
        return YWSM;
    }

    public void setYWSM(String YWSM) {
        this.YWSM = YWSM;
    }

    public String getZWSM() {
        return ZWSM;
    }

    public void setZWSM(String ZWSM) {
        this.ZWSM = ZWSM;
    }

    public Integer getAlarmL() {
        return AlarmL;
    }

    public void setAlarmL(Integer alarmL) {
        AlarmL = alarmL;
    }

    public Integer getStopL() {
        return StopL;
    }

    public void setStopL(Integer stopL) {
        StopL = stopL;
    }

    public String getYN() {
        return YN;
    }

    public void setYN(String YN) {
        this.YN = YN;
    }
}
