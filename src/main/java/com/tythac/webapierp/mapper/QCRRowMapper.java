package com.tythac.webapierp.mapper;

import com.tythac.webapierp.model.QCR;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/10/28
 * @Description 品管現場原因資料
 */
public class QCRRowMapper implements RowMapper<QCR> {
    @Override
    public QCR mapRow(ResultSet rs, int rowNum) throws SQLException {
        QCR qcr = new QCR();
        qcr.setProNo(rs.getString("ProNo"));   // 流水編號
        qcr.setSCDate(rs.getString("SCDate"));  // 生產日期
        qcr.setSJXH(rs.getString("SJXH"));    // 工時區間(日班、晚班、夜班)
        qcr.setGSBH(rs.getString("GSBH"));    // 廠別
        qcr.setDepNo(rs.getString("DepNo"));   // 部門別
        qcr.setGXLB(rs.getString("GXLB"));    // 工段
        qcr.setSCBH(rs.getString("SCBH"));    // 生產訂單
        qcr.setCC(rs.getString("CC"));  // 尺寸
        qcr.setUSERID(rs.getString("USERID"));
        qcr.setUSERDATE(rs.getString("USERDATE"));
        return qcr;
    }
}
