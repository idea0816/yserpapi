package com.tythac.webapierp.dao.impl;

import com.tythac.webapierp.dao.QCDao;
import com.tythac.webapierp.mapper.QCBLYYRowMapper;
import com.tythac.webapierp.mapper.QCRRowMapper;
import com.tythac.webapierp.model.QCBLYY;
import com.tythac.webapierp.model.QCR;
import com.tythac.webapierp.model.QCRD;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/10/19
 * @Description BC品原因
 * <p>
 * 取得品檢不良原因-QCBLYY
 * 取得 QCR-ProNO
 * 取得檢驗不良數量資料-QCR
 * 取得檢驗不良數量資料-QCRD
 * 寫入檢驗不良數量資料-QCR
 * 寫入檢驗不良數量資料-QCRD
 */
@Component
public class QCDaoImpl implements QCDao {
    @Resource
    private NamedParameterJdbcTemplate lyserpJdbcTemplate;
    Map<String, Object> map;

    // 取得品檢不良原因
    @Override
    public List<QCBLYY> getQcbllys(String extraSQL) {
        String sqlGetBlyys = "SELECT GSBH, YYBH, DFL, YWSM, ZWSM, AlarmL, StopL, YN " +
                "FROM QCBLYY ";
        if (!extraSQL.equals("ALL")) {
            sqlGetBlyys = sqlGetBlyys + extraSQL;
        }
        List<QCBLYY> getBlyys = lyserpJdbcTemplate.query(sqlGetBlyys, map, new QCBLYYRowMapper());
        if (getBlyys.size() > 0) {
            return getBlyys;
        } else {
            return Collections.emptyList();
        }
    }

    // 取得 QCR-ProNO
    @Override
    public List<String> getVersion(String SCDate) {
        String selGetVersion = "SELECT TOP 1 ProNo FROM QCR " +
                "WHERE ProNo LIKE :SCDate " +
                "ORDER BY ProNo DESC";
        map = new HashMap<>();
        map.put("SCDate", SCDate);
        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("ProNo");
        return lyserpJdbcTemplate.query(selGetVersion, map, rowMapper);
    }

    // 取得檢驗不良數量資料-QCR
    @Override
    public List<QCR> getQCRs(String extraSQL) {
        String sqlGetQCRs = "SELECT ProNo, SCDate, SJXH, GSBH, DepNo, GXLB, SCBH, CC, USERID, USERDATE FROM QCR ";
        if (!extraSQL.equals("ALL")) {
            sqlGetQCRs = sqlGetQCRs + extraSQL;
        }
        List<QCR> getQCRs = lyserpJdbcTemplate.query(sqlGetQCRs, map, new QCRRowMapper());
        if (getQCRs.size() > 0) {
            return getQCRs;
        } else {
            return Collections.emptyList();
        }
    }

    // 寫入檢驗不良數量資料-QCR
    @Override
    public void insQCR(QCR insQCR) {
        String sqlinsQCR = "INSERT INTO QCR " +
                "VALUES (:ProNo, :SCDate, :SJXH, :GSBH, :DepNo, :GXLB, :SCBH, :CC, :USERID, :USERDATE) ";
        map = new HashMap<>();
        map.put("ProNo", insQCR.getProNo());
        map.put("SCDate", insQCR.getSCDate());
        map.put("SJXH", insQCR.getSJXH());
        map.put("GSBH", insQCR.getGSBH());
        map.put("DepNo", insQCR.getDepNo());
        map.put("GXLB", insQCR.getGXLB());
        map.put("SCBH", insQCR.getSCBH());
        map.put("CC", insQCR.getCC());
        map.put("USERID", insQCR.getUSERID());
        map.put("USERDATE", insQCR.getUSERDATE());

        lyserpJdbcTemplate.update(sqlinsQCR, map);
    }

    // 寫入檢驗不良數量資料-QCRD
    @Override
    public void insQCRD(QCRD insQCRD) {
        String sqlinsQCRD = "INSERT INTO QCRD " +
                "VALUES (:ProNo, :YYBH, :Qty, :USERID, :USERDATE) ";
        map = new HashMap<>();
        map.put("ProNo", insQCRD.getProNo());
        map.put("YYBH", insQCRD.getYYBH());
        map.put("Qty", insQCRD.getQty());
        map.put("USERID", insQCRD.getUSERID());
        map.put("USERDATE", insQCRD.getUSERDATE());

        lyserpJdbcTemplate.update(sqlinsQCRD, map);
    }
}
