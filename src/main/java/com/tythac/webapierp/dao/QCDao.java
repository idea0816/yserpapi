package com.tythac.webapierp.dao;

import com.tythac.webapierp.model.QCBLYY;
import com.tythac.webapierp.model.QCR;
import com.tythac.webapierp.model.QCRD;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/10/19
 * @Description BC品原因
 *
 * 取得品檢不良原因-QCBLYY
 * 取得 QCR-ProNO
 * 取得檢驗不良數量資料-QCR
 * 取得檢驗不良數量資料-QCRD
 * 寫入檢驗不良數量資料-QCR
 * 寫入檢驗不良數量資料-QCRD
 */
public interface QCDao {
    // 取得品檢不良原因
    List<QCBLYY> getQcbllys(String extraSQL);
    // 取得 QCR-ProNO
    List<String> getVersion(String SCDate);
    // 取得檢驗不良數量資料-QCR
    List<QCR> getQCRs(String extraSQL);
    // 寫入檢驗不良數量資料-QCR
    void insQCR(QCR insQCR);
    // 寫入檢驗不良數量資料-QCRD
    void insQCRD(QCRD insQCRD);
}
