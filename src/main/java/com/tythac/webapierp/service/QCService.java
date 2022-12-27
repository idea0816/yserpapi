package com.tythac.webapierp.service;

import com.tythac.webapierp.model.QCBLYY;
import com.tythac.webapierp.model.QCR;

import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/10/19
 * @Description BC品原因
 *
 * 取得品檢不良原因-QCBLYY
 * 取得檢驗不良數量資料-QCR
 * 寫入檢驗不良數量資料-QCR, QCRD
 */
public interface QCService {
    // 取得品檢不良原因
    List<QCBLYY> getQcbllys(String extra);
    // 取得檢驗不良數量資料-QCR
    List<QCR> getQCRs(String extra);
    // 寫入檢驗不良數量資料
    void insQCRs(Map<String, Object> insQCRs);
    // Test
    String test();
}
