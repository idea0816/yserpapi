package com.tythac.webapierp.dao;

import com.tythac.webapierp.model.ZqcReportDetail;
import com.tythac.webapierp.model.Zreport;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2023/1/12
 * @Description Scan Data for Report
 *
 * 線上品檢資料(成型-日總合)
 * 線上品檢資料(成型-日明細表格)
 * 線上掃描資料
 * 線上掃描資料-Old
 */
public interface ZreportDao {
    // 線上品檢資料(成型線日總合)
    List<Zreport> getQCRDs(String date, String DepNo);
    // 線上品檢資料(成型-日明細表格)
    List<ZqcReportDetail> getQCReportDetails(String DepNo, String date);
    // 線上掃描資料
    List<Zreport> getSMDDSSs(String DepNo, String date, String GXLB);
    // 線上掃描資料-Old
    List<Zreport> getSMDDSSOlds(String DepNo, String date, String GXLB);
}
