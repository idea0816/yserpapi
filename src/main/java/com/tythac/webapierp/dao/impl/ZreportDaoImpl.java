package com.tythac.webapierp.dao.impl;

import com.tythac.webapierp.dao.ZreportDao;
import com.tythac.webapierp.mapper.ZqcReportDetailRowMapper;
import com.tythac.webapierp.mapper.ZreportRowMapper;
import com.tythac.webapierp.model.ZqcReportDetail;
import com.tythac.webapierp.model.Zreport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2023/1/12
 * @Description Scan Data for Report
 * <p>
 * 線上品檢資料(成型-日總合)
 * 線上品檢資料(成型-日明細表格)
 * 線上掃描資料
 * 線上掃描資料-SMZLOld
 */
@Component
public class ZreportDaoImpl implements ZreportDao {
    @Resource
    private NamedParameterJdbcTemplate lyserpJdbcTemplate;
    Map<String, Object> map;

    // 線上品檢資料(成型線-日總合)
    @Override
    public List<Zreport> getQCRDs(String date, String DepNo) {
        // CONVERT(VARCHAR, 欄位名稱, 23) 顯示 2023-01-01
        String sqlGetQCRDs = "SELECT QCR.DepNo,CONVERT(VARCHAR, QCR.SCDate, 23) DateTime, SUM(QCRD.Qty) AS Qty " +
                "FROM QCRD " +
                "LEFT JOIN QCR ON QCRD.ProNo = QCR.ProNo " +
                "WHERE CONVERT(VARCHAR, QCR.SCDate, 23) LIKE :date AND QCR.DepNo = :DepNo " +
                "GROUP BY QCR.DepNo, CONVERT(VARCHAR, QCR.SCDate, 23) ";
        map = new HashMap<>();
        map.put("date", date);
        map.put("DepNo", DepNo);
        List<Zreport> getQCRDs = lyserpJdbcTemplate.query(sqlGetQCRDs, map, new ZreportRowMapper());
        if (getQCRDs.size() > 0) {
            return getQCRDs;
        } else {
            return Collections.emptyList();
        }
    }

    // 線上品檢資料(成型-日明細表格)
    @Override
    public List<ZqcReportDetail> getQCReportDetails(String DepNo, String date) {
        String sqlGetQCReportDetails = "SELECT  isnull(QCC.YYBH,'') AS YYBH, isnull(QCC.ZWSM, '') AS ZWSM, QCC.Total " +
                ",isnull(SUM(CASE WHEN NHour = '7' THEN Qty END), null) AS 'h7' " +
                ",isnull(SUM(CASE WHEN NHour = '8' THEN Qty END), null) AS 'h8' " +
                ",isnull(SUM(CASE WHEN NHour = '9' THEN Qty END), null) AS 'h9' " +
                ",isnull(SUM(CASE WHEN NHour = '10' THEN Qty END), null) AS 'h10' " +
                ",isnull(SUM(CASE WHEN NHour = '11' THEN Qty END), null) AS 'h11' " +
                ",isnull(SUM(CASE WHEN NHour = '12' THEN Qty END), null) AS 'h12' " +
                ",isnull(SUM(CASE WHEN NHour = '13' THEN Qty END), null) AS 'h13' " +
                ",isnull(SUM(CASE WHEN NHour = '14' THEN Qty END), null) AS 'h14' " +
                ",isnull(SUM(CASE WHEN NHour = '15' THEN Qty END), null) AS 'h15' " +
                ",isnull(SUM(CASE WHEN NHour = '16' THEN Qty END), null) AS 'h16' " +
                ",isnull(SUM(CASE WHEN NHour = '17' THEN Qty END), null) AS 'h17' " +
                "FROM (SELECT QCR.Depno, BDepartment.DepName, QCR.GXLB, QCRD.YYBH ,QCBLYY.ZWSM ,QCT.Qty AS Total,SUM(QCRD.Qty) AS Qty, datepart(hh,QCR.USERDATE) AS NHour " +
                "FROM QCRD " +
                "LEFT JOIN QCR ON QCR.ProNo = QCRD.ProNo " +
                "LEFT JOIN QCBLYY ON QCBLYY.YYBH = QCRD.YYBH AND QCR.GSBH = QCBLYY.GSBH " +
                "LEFT JOIN Bdepartment ON Bdepartment.ID = QCR.DepNo " +
                "LEFT JOIN (SELECT QCR.DepNo, QCRD.YYBH, SUM(QCRD.Qty) AS Qty " +
                "FROM QCRD " +
                "LEFT JOIN QCR ON QCR.ProNo = QCRD.ProNo " +
                "LEFT JOIN QCBLYY ON QCBLYY.YYBH = QCRD.YYBH AND QCR.GSBH = QCBLYY.GSBH " +
                "WHERE CONVERT(VARCHAR, QCR.USERDATE, 120) LIKE :date " +
                "AND QCR.GSBH ='VDH' AND QCR.GXLB = 'A' " +
                "GROUP BY QCR.Depno, QCRD.YYBH) AS QCT ON QCR.DepNo = QCT.DepNo AND QCBLYY.YYBH = QCT.YYBH " +
                "WHERE CONVERT(VARCHAR, QCR.USERDATE, 120) LIKE :date " +
                "AND BDepartment.DepName LIKE '%%' AND QCR.GSBH ='VDH' AND (  QCR.GXLB = 'A') " +
                "GROUP BY QCR.DepNo, BDepartment.DepName, QCR.GXLB, QCRD.YYBH ,QCBLYY.ZWSM, QCT.Qty, datepart(hh,QCR.USERDATE) " +
                "UNION ALL " +
                "SELECT QCR.DepNo, BDepartment.DepName, QCR.GXLB, '合計' AS YYBH, 'Hourly_Total' AS YWSM, QCT.Qty AS Total, SUM(QCRD.Qty) AS Qty, datepart(hh,QCR.USERDATE) AS NHour " +
                "FROM QCRD " +
                "LEFT JOIN QCR ON QCR.ProNo= QCRD.ProNo " +
                "LEFT JOIN QCBLYY ON QCBLYY.YYBH = QCRD.YYBH AND QCR.GSBH = QCBLYY.GSBH " +
                "LEFT JOIN BDepartment ON BDepartment.ID = QCR.DepNo " +
                "LEFT JOIN (SELECT QCR.DepNo, QCR.GXLB, SUM(QCRD.Qty) AS Qty FROM QCRD " +
                "LEFT JOIN QCR ON QCR.ProNo = QCRD.ProNo " +
                "LEFT JOIN QCBLYY ON QCBLYY.YYBH = QCRD.YYBH AND QCR.GSBH = QCBLYY.GSBH " +
                "WHERE QCR.DepNo = :DepNo AND  CONVERT(VARCHAR, QCR.USERDATE, 120) LIKE :date AND QCR.GSBH ='VDH' AND QCR.GXLB = 'A' " +
                "GROUP BY QCR.DepNo, QCR.GXLB) AS QCT " +
                "ON QCR.DepNo = QCT.DepNo AND QCR.GXLB = QCT.GXLB " +
                "WHERE CONVERT(VARCHAR, QCR.USERDATE, 120) LIKE :date AND BDepartment.DepName LIKE '%%' AND QCR.GSBH ='VDH' AND QCR.GXLB = 'A' " +
                "GROUP BY QCR.Depno, BDepartment.DepName, QCT.Qty, QCR.GXLB, datepart(hh, QCR.USERDATE) " +
                ") AS QCC " +
                "GROUP BY QCC.DepNo, QCC.DepName, QCC.GXLB, QCC.YYBH, QCC.ZWSM, QCC.Total " +
                "ORDER BY QCC.DepName, QCC.DepNo, QCC.GXLB, QCC.YYBH ";
        map = new HashMap<>();
        map.put("DepNo", DepNo);
        map.put("date", date);
        List<ZqcReportDetail> getQCReportDetails = lyserpJdbcTemplate.query(sqlGetQCReportDetails, map, new ZqcReportDetailRowMapper());
        if (getQCReportDetails.size() > 0) {
            return getQCReportDetails;
        } else {
            return Collections.emptyList();
        }
    }

    // 線上掃描資料
    @Override
    public List<Zreport> getSMDDSSs(String DepNo, String date, String GXLB) {
        String sqlGetSMDDSSs = "SELECT SMZL.DepNO, CONVERT(VARCHAR, SMZL.ScanDate, 23) DateTime, SUM(SMZL.CTS) AS Qty " +
                "FROM SMDDSS " +
                "LEFT JOIN SMZL ON SMDDSS.CODEBAR = SMZL.CODEBAR " +
                "WHERE SMDDSS.GXLB = :GXLB AND CONVERT(VARCHAR, SMZL.ScanDate, 23) LIKE :date AND SMZL.DepNO = :DepNo " +
                "GROUP BY SMZL.DepNO,CONVERT(VARCHAR, SMZL.ScanDate, 23) ";
        map = new HashMap<>();
        map.put("DepNo", DepNo);
        map.put("date", date);
        map.put("GXLB", GXLB);
        List<Zreport> getSMDDSSs = lyserpJdbcTemplate.query(sqlGetSMDDSSs, map, new ZreportRowMapper());
        if (getSMDDSSs.size() > 0) {
            return getSMDDSSs;
        } else {
            return Collections.emptyList();
        }
    }

    // 線上掃描資料-SMZLOld
    @Override
    public List<Zreport> getSMDDSSOlds(String DepNo, String date, String GXLB) {
        String sqlGetSMDDSSs = "SELECT SMZLOld.DepNO, CONVERT(VARCHAR, SMZLOld.ScanDate, 23) DateTime, SUM(SMZLOld.CTS) AS Qty " +
                "FROM SMDDSS " +
                "LEFT JOIN SMZLOld ON SMDDSS.CODEBAR = SMZLOld.CODEBAR " +
                "WHERE SMDDSS.GXLB = :GXLB AND CONVERT(VARCHAR, SMZLOld.ScanDate, 23) LIKE :date AND SMZLOld.DepNO = :DepNo " +
                "GROUP BY SMZLOld.DepNO,CONVERT(VARCHAR, SMZLOld.ScanDate, 23) ";
        map = new HashMap<>();
        map.put("DepNo", DepNo);
        map.put("date", date);
        map.put("GXLB", GXLB);
        List<Zreport> getSMDDSSs = lyserpJdbcTemplate.query(sqlGetSMDDSSs, map, new ZreportRowMapper());
        if (getSMDDSSs.size() > 0) {
            return getSMDDSSs;
        } else {
            return Collections.emptyList();
        }
    }
}
