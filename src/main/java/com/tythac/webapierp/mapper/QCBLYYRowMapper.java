package com.tythac.webapierp.mapper;

import com.tythac.webapierp.model.QCBLYY;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/10/19
 * @Description BC品原因
 */
public class QCBLYYRowMapper implements RowMapper<QCBLYY> {
    @Override
    public QCBLYY mapRow(ResultSet rs, int rowNum) throws SQLException {
        QCBLYY qcblyy = new QCBLYY();
        qcblyy.setGSBH(rs.getString("GSBH"));    // 廠別
        qcblyy.setYYBH(rs.getString("YYBH"));    // 編號
        qcblyy.setDFL(rs.getString("DFL")); // 大分類
        qcblyy.setYWSM(rs.getString("YWSM"));    // 英文名稱
        qcblyy.setZWSM(rs.getString("ZWSM"));    // 中文名稱
        qcblyy.setAlarmL(rs.getInt("AlarmL")); // 發生幾次產生系統發處警告
        qcblyy.setStopL(rs.getInt("StopL"));  // 發生幾次產線系統停止
        qcblyy.setYN(rs.getString("YN"));  // 停用	YN=2啟用, 3停用
        return qcblyy;
    }
}
