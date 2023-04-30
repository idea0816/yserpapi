package com.tythac.webapierp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tythac.webapierp.dao.BdepartmentDao;
import com.tythac.webapierp.dao.QCDao;
import com.tythac.webapierp.dao.ZreportDao;
import com.tythac.webapierp.model.*;
import com.tythac.webapierp.service.QCService;
import com.tythac.webapierp.service.timeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author CXY
 * @version Create Time: 2022/10/19
 * @Description BC品原因
 * <p>
 * 取得品檢不良原因-QCBLYY
 * 取得檢驗不良數量資料-QCR
 * 寫入檢驗不良數量資料-QCR, QCRD
 * 取得品檢日報告-Chart
 * 取得品檢日報告-Sheet
 * 取得品檢週報告
 */
@Component
public class QCServiceImpl implements QCService {
    @Resource
    private QCDao qcDao;
    @Resource
    private timeService timeService;
    @Resource
    private ZreportDao zreportDao;
    @Resource
    private BdepartmentDao bdepartmentDao;

    // 判斷日期是否已超過 15 天
    int dayDiff = 0;
    List<Zreport> getSMDDSSs = null;


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
        switch (extra) {
            case "Today":
                String Today = timeService.nowWeb();
                extraSQL = " WHERE CONVERT(VARCHAR, SCDate, 126) LIKE '" + Today + "%'";
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
        String getVersion = qcDao.getVersion(scDate.replace("-", "") + "%");
        if (getVersion.equals("")) {
            ProNo = scDate.replace("-", "") + "01";
        } else {
            ProNo = scDate.replace("-", "") + String.format("%02d", Integer.parseInt(getVersion.substring(8, 10)) + 1);
        }
        // Get Date
        String SCDate;
        if (insQCR.get(0).getSCDate().equals(timeService.nowWeb())) {
            SCDate = timeService.nowWebT();
        } else {
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

    // 取得品檢日報告-Chart
    @Override
    public List<QCRD> dayReport(String depName, String dateTime) {
        // 取得部門 ID
        String extraSQL = " WHERE DepName = '" + depName + "' AND GSBH = 'VDH' ";
        List<Bdepartment> getBdepartments = bdepartmentDao.getDeps(extraSQL);
        return qcDao.getQCRDs(getBdepartments.get(0).getID(), dateTime);
    }

    // 取得品檢日報告-Sheet
    @Override
    public List<ZqcReportDetail> dayReportSheet(String depName, String dateTime) {
        // 取得部門 ID
        String extraSQL = " WHERE DepName = '" + depName + "' AND GSBH = 'VDH' ";
        List<Bdepartment> getBdepartments = bdepartmentDao.getDeps(extraSQL);
        // 取得時間段
        // List<String> getTimePeriods = qcDao.getTimePeriod(dateTime + "%");
        // 取得品檢(成型-日明細表格)

        return zreportDao.getQCReportDetails(getBdepartments.get(0).getID(), dateTime + "%");
    }

    // 取得品檢週報告
    @Override
    public List<ZqcReport> weekReport(String weekFirst, String weekLast) {
        // 回傳Web
        List<ZqcReport> getZqcReports = new ArrayList<>();

        // 設置百分比(個位數為 0 時、要注意佔位符)
        DecimalFormat decimalFormat = new DecimalFormat("##0.00%");
        // 取得週間所有日期
        ArrayList<String> getDate = new ArrayList<>();
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date day1 = new SimpleDateFormat("yyyy-MM-dd").parse(weekFirst);
            Date day2 = new SimpleDateFormat("yyyy-MM-dd").parse(weekLast);
            Calendar dd = Calendar.getInstance();
            dd.setTime(day1);
            getDate.add(format.format(dd.getTime()));
            while (dd.getTime().before(day2)) {
                dd.add(Calendar.DATE, 1);
                getDate.add(format.format(dd.getTime()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 取得日期區間內有幾個單位
        List<String> getDepNoCounts = qcDao.getDepNo(weekFirst, weekLast);
        for (String getDepNoCount : getDepNoCounts) {
            int count = 0;
            ZqcReport getZqcReport = new ZqcReport();
            // 遍歷週間所有日期
            for (String value : getDate) {
                // 取得線上品檢不良數量(日)
                List<Zreport> getQCRDs = zreportDao.getQCRDs(value + "%", getDepNoCount);
                // 取得部門 ID
                String extraSQL = " WHERE ID = '" + getDepNoCount + "' AND GSBH = 'VDH' ";
                List<Bdepartment> getBdepartments = bdepartmentDao.getDeps(extraSQL);
                getZqcReport.setDepNo(getBdepartments.get(0).getDepName());

                String countString = String.valueOf(count);
                if (getQCRDs.size() == 0) {
                    switch (countString) {
                        case "0":
                            getZqcReport.setMon("0.00%");
                            break;
                        case "1":
                            getZqcReport.setTue("0.00%");
                            break;
                        case "2":
                            getZqcReport.setWed("0.00%");
                            break;
                        case "3":
                            getZqcReport.setThu("0.00%");
                            break;
                        case "4":
                            getZqcReport.setFri("0.00%");
                            break;
                        case "5":
                            getZqcReport.setSat("0.00%");
                            break;
                    }
                } else {
                    // 取得線上掃描數量(成型、週)、超過 15 天的資料會轉入 SMZLOld、所以要另外判斷
                    // 判斷日期是否已超過 15 天
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = format.parse(timeService.nowWeb());
                        Date date2 = format.parse(value);
                        dayDiff = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (dayDiff < 15) {
                        getSMDDSSs = zreportDao.getSMDDSSs(getDepNoCount, value + "%", "A");
                    } else {
                        getSMDDSSs = zreportDao.getSMDDSSOlds(getDepNoCount, value + "%", "A");
                    }
                    double d1 = getQCRDs.get(0).getQty() * 1.0;
                    double d2 = getSMDDSSs.get(0).getQty() * 1.0;
                    String percent = decimalFormat.format(d1 / d2);
                    switch (countString) {
                        case "0":
                            getZqcReport.setMon(percent);
                            break;
                        case "1":
                            getZqcReport.setTue(percent);
                            break;
                        case "2":
                            getZqcReport.setWed(percent);
                            break;
                        case "3":
                            getZqcReport.setThu(percent);
                            break;
                        case "4":
                            getZqcReport.setFri(percent);
                            break;
                        case "5":
                            getZqcReport.setSat(percent);
                            break;
                    }

                }
                count += 1;
            }
            getZqcReports.add(getZqcReport);
        }
        return getZqcReports;
    }
}
