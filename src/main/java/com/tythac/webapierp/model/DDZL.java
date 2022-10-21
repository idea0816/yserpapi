package com.tythac.webapierp.model;

/**
 * @author CXY
 * @version Create Time: 2022/10/11
 * @Description 訂單基本資料(部份)
 */
public class DDZL {
    private String DDBH;    // 訂單編號
    private String GSBH;    // 公司編號
    private String XieXing; // 鞋型
    private String SheHao;  // 色號
    private String ARTICLE; // ARTICLE
    private String CCGB;    // 尺寸國別
    private String KHBH;    // 客戶代號
    private String KHPO;    // 客戶PO
    private String DDRQ;    // 接單日期
    private String ShipDate;    // 指定交期
    private Integer Pairs;  // 雙數

    public String getDDBH() {
        return DDBH;
    }

    public void setDDBH(String DDBH) {
        this.DDBH = DDBH;
    }

    public String getGSBH() {
        return GSBH;
    }

    public void setGSBH(String GSBH) {
        this.GSBH = GSBH;
    }

    public String getXieXing() {
        return XieXing;
    }

    public void setXieXing(String xieXing) {
        XieXing = xieXing;
    }

    public String getSheHao() {
        return SheHao;
    }

    public void setSheHao(String sheHao) {
        SheHao = sheHao;
    }

    public String getARTICLE() {
        return ARTICLE;
    }

    public void setARTICLE(String ARTICLE) {
        this.ARTICLE = ARTICLE;
    }

    public String getCCGB() {
        return CCGB;
    }

    public void setCCGB(String CCGB) {
        this.CCGB = CCGB;
    }

    public String getKHBH() {
        return KHBH;
    }

    public void setKHBH(String KHBH) {
        this.KHBH = KHBH;
    }

    public String getKHPO() {
        return KHPO;
    }

    public void setKHPO(String KHPO) {
        this.KHPO = KHPO;
    }

    public String getDDRQ() {
        return DDRQ;
    }

    public void setDDRQ(String DDRQ) {
        this.DDRQ = DDRQ;
    }

    public String getShipDate() {
        return ShipDate;
    }

    public void setShipDate(String shipDate) {
        ShipDate = shipDate;
    }

    public Integer getPairs() {
        return Pairs;
    }

    public void setPairs(Integer pairs) {
        Pairs = pairs;
    }
}
