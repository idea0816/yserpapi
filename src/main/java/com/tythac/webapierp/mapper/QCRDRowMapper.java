package com.tythac.webapierp.mapper;

import com.tythac.webapierp.model.QCRD;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/10/28
 * @Description 品管現場原因資料明細
 */
public class QCRDRowMapper implements RowMapper<QCRD> {
    @Override
    public QCRD mapRow(ResultSet rs, int rowNum) throws SQLException {
        QCRD qcrd = new QCRD();
        qcrd.setProNo(rs.getString("ProNo"));   // 流水編號
        qcrd.setYYBH(rs.getString("YYBH"));    // Defect理由編號
        qcrd.setQty(rs.getInt("Qty"));    // 數量
        qcrd.setUSERID(rs.getString("USERID"));
        qcrd.setUSERDATE(rs.getString("USERDATE"));
        return qcrd;
    }
}
