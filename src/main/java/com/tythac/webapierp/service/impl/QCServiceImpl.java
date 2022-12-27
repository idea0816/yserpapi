package com.tythac.webapierp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tythac.webapierp.dao.QCDao;
import com.tythac.webapierp.model.QCBLYY;
import com.tythac.webapierp.model.QCR;
import com.tythac.webapierp.model.QCRD;
import com.tythac.webapierp.service.QCService;
import com.tythac.webapierp.service.timeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/10/19
 * @Description BC品原因
 * <p>
 * 取得品檢不良原因-QCBLYY
 * 取得檢驗不良數量資料-QCR
 * 寫入檢驗不良數量資料-QCR, QCRD
 */
@Component
public class QCServiceImpl implements QCService {
    @Resource
    private QCDao qcDao;
    @Resource
    private timeService timeService;


    // 取得品檢不良原因
    @Override
    public List<QCBLYY> getQcbllys(String extra) {
        String extraSQL = "";
        switch (extra) {
            case "QC1":
                extraSQL = " WHERE YYBH LIKE 'Q1%' ";
                break;
            case "QC2":
                extraSQL = " WHERE YYBH LIKE 'Q2%' ";
                break;
            case "QC3":
                extraSQL = " WHERE YYBH LIKE 'Q3%' ";
                break;
            case "TQC":
                extraSQL = " WHERE YYBH LIKE 'TQ%' ";
                break;
            case "ALL":
                extraSQL = "ALL";
                break;
        }
        return qcDao.getQcbllys(extraSQL);
    }

    // 取得檢驗不良數量資料-QCR
    @Override
    public List<QCR> getQCRs(String extra) {
        String extraSQL = "";
        switch(extra){
            case "Today":
                String Today = timeService.nowWeb();
                extraSQL = " WHERE CONVERT(VARCHAR, SCDate, 126) LIKE '"+Today+"%'";
                break;
            case "ALL":
                extraSQL = "ALL";
                break;
        }
        return qcDao.getQCRs(extraSQL);
    }

    // 寫入檢驗不良數量資料
    @Override
    public void insQCRs(Map<String, Object> insQCRs) {
        List<QCR> insQCR;
        List<QCRD> insQCRD;
        // 寫入前確認是否已有資料 還沒確認
        // Map to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //Convert Map to JSON
            String jsonQCR = objectMapper.writeValueAsString(insQCRs.get("insQCR"));
            String jsonQCRD = objectMapper.writeValueAsString(insQCRs.get("insQCRD"));
            // JSON to List<?>
            insQCR = objectMapper.readValue(jsonQCR, new TypeReference<>() {
            });
            insQCRD = objectMapper.readValue(jsonQCRD, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // Get ProNo
        String scDate = insQCR.get(0).getSCDate();
        String ProNo;
        List<String> getVersion = qcDao.getVersion(scDate.replace("-", "") + "%");
        if (getVersion.size() == 0) {
            ProNo = scDate.replace("-", "") + "01";
        } else {
            ProNo = scDate.replace("-", "") + String.format("%02d", Integer.parseInt(getVersion.get(0).substring(8, 10)) + 1);
        }
        // Get Date
        String SCDate;
        if(insQCR.get(0).getSCDate().equals(timeService.nowWeb())){
            SCDate = timeService.nowWebT();
        }else{
            SCDate = insQCR.get(0).getSCDate();
        }
        // Write to SQL
        for (QCR value : insQCR) {
            QCR insData = new QCR();
            insData.setProNo(ProNo);
            insData.setSCDate(SCDate);
            insData.setSJXH(value.getSJXH());
            insData.setGSBH(value.getGSBH());
            insData.setDepNo(value.getDepNo());
            insData.setGXLB(value.getGXLB());
            insData.setSCBH(value.getSCBH());
            insData.setCC(value.getCC());
            insData.setUSERID(value.getUSERID());
            insData.setUSERDATE(timeService.nowWebT());
            qcDao.insQCR(insData);
        }
        for (QCRD value : insQCRD) {
            QCRD insData = new QCRD();
            insData.setProNo(ProNo);
            insData.setYYBH(value.getYYBH());
            insData.setQty(value.getQty());
            insData.setUSERID(value.getUSERID());
            insData.setUSERDATE(timeService.nowWebT());
            qcDao.insQCRD(insData);
        }
    }

    // 計算今天的不良資料
    @Override
    public String test() {
        getQCRs("Today");
        return null;
    }
}
