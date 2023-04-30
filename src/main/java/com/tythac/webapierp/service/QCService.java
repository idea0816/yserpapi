package com.tythac.webapierp.service;

import com.tythac.webapierp.model.*;

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
 * 取得品檢日報告-Chart
 * 取得品檢日報告-Sheet
 * 取得品檢週報告
 */
public interface QCService {
    // 取得品檢不良原因
    List<QCBLYY> getQcbllys(String extra);
    // 取得檢驗不良數量資料-QCR
    List<QCR> getQCRs(String extra);
    // 寫入檢驗不良數量資料
    void insQCRs(Map<String, Object> insQCRs);
    // 取得品檢日報告-Chart
    List<QCRD> dayReport(String depName, String dateTime);
    // 取得品檢日報告-Sheet
    List<ZqcReportDetail> dayReportSheet(String depName, String dateTime);
    // 取得品檢週報告
    List<ZqcReport> weekReport(String weekFirst, String weekLast);
}
